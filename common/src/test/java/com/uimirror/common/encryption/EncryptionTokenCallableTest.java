package com.uimirror.common.encryption;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uimirror.common.LoadExternalJson;
import com.uimirror.common.base.BaseTest;
import com.uimirror.common.base.JUnit4ClassRunner;
import com.uimirror.common.encryption.bean.EncryptMessage;
import com.uimirror.common.encryption.callable.EncryptTokenCallable;
import com.uimirror.common.thread.CompletationFixedPoolService;
import com.uimirror.common.util.StringUtility;

@RunWith(JUnit4ClassRunner.class)
public class EncryptionTokenCallableTest extends BaseTest{

	private List<Map<String, Object>> validData;
	protected static Logger LOG = LoggerFactory.getLogger(EncryptionTokenCallableTest.class);
	
	@SuppressWarnings("unchecked")
	@Before
    public void setUp() throws Exception {
    	validData = (List<Map<String, Object>>) LoadExternalJson.loadData("encryption/en_valid_data_set_3.txt", List.class);
    }
	
	@Test
	public void testTokenCallableSize() throws InterruptedException, ExecutionException {

		List<Callable<Object>> jobs = new ArrayList<Callable<Object>>(10);
    	
    	for(Map<String, Object> map : validData){
			EncryptTokenCallable callable = null;
			if(StringUtility.checkEmptyString((String)map.get("key"))){
				callable = new EncryptTokenCallable(Integer.parseInt((String)map.get("size")), "test");
			}else{
				callable = new EncryptTokenCallable(Integer.parseInt((String)map.get("size")), (String)map.get("key"), "test");
			}
			jobs.add(callable);
		}
    	
    	CompletationFixedPoolService cPoolService = CompletationFixedPoolService.getInstance(jobs.size());
    	cPoolService.submitTask(jobs);
    	
    	//Get Results
    	Object[] results = cPoolService.getResults();
    	Object result = null;
    	EncryptMessage eMessage = null;
		for(int i = 0; i < jobs.size() ; i++){
			result =  (results != null && results.length >= i) ?results[i]:null;
			eMessage = (EncryptMessage)result;
			LOG.info("Encrypted Message -->{} for the plain text {}", eMessage.getEncryptdMsg(), eMessage.getOriginalId());
			Assert.assertNotNull(result);
		}
	}

}
