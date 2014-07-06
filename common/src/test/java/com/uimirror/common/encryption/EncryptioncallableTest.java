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
import com.uimirror.common.annotation.ProfileExecution;
import com.uimirror.common.base.BaseTest;
import com.uimirror.common.base.JUnit4ClassRunner;
import com.uimirror.common.encryption.bean.DecryptMessage;
import com.uimirror.common.encryption.bean.EncryptMessage;
import com.uimirror.common.encryption.callable.DecryptMessageCallable;
import com.uimirror.common.encryption.callable.EncryptMessageCallable;
import com.uimirror.common.thread.CompletationFixedPoolService;
import com.uimirror.common.util.StringUtility;

@RunWith(JUnit4ClassRunner.class)
public class EncryptioncallableTest extends BaseTest{
	
	private List<Map<String, Object>> validData;
	private List<Map<String, Object>> validDecryptData;
	protected static Logger LOG = LoggerFactory.getLogger(EncryptioncallableTest.class);

    @SuppressWarnings("unchecked")
	@Before
    public void setUp() throws Exception {
    	validData = (List<Map<String, Object>>) LoadExternalJson.loadData("encryption/en_valid_data_set_2.txt", List.class);
    	validDecryptData = (List<Map<String, Object>>) LoadExternalJson.loadData("encryption/de_valid_data_set_1.txt", List.class);
    }

    @Test
    @ProfileExecution
    public void encryptCallableTest() throws InterruptedException, ExecutionException {
    	List<Callable<Object>> jobs = new ArrayList<Callable<Object>>(10);
    	
    	for(Map<String, Object> map : validData){
			EncryptMessageCallable callable = null;
			if(StringUtility.checkEmptyString((String)map.get("key"))){
				callable = new EncryptMessageCallable((String)map.get("plain"), "test");
			}else{
				callable = new EncryptMessageCallable((String)map.get("plain"), (String)map.get("key"), "test");
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
    
    @Test
    public void decryptCallableTest() throws InterruptedException, ExecutionException {
    	List<Callable<Object>> jobs = new ArrayList<Callable<Object>>(10);
    	
    	for(Map<String, Object> map : validDecryptData){
    		DecryptMessageCallable callable = null;
			if(StringUtility.checkEmptyString((String)map.get("key"))){
				callable = new DecryptMessageCallable((String)map.get("emsg"), "test");
			}else{
				callable = new DecryptMessageCallable((String)map.get("emsg"), (String)map.get("key"), "test");
			}
			jobs.add(callable);
		}
    	
    	CompletationFixedPoolService cPoolService = CompletationFixedPoolService.getInstance(jobs.size());
    	cPoolService.submitTask(jobs);
    	
    	//Get Results
    	Object[] results = cPoolService.getResults();
    	Object result = null;
    	DecryptMessage deMessage = null;
		for(int i = 0; i < jobs.size() ; i++){
			result =  (results != null && results.length >= i) ?results[i]:null;
			deMessage = (DecryptMessage)result;
			LOG.info("Decrypted Message -->{} for the encrypted text {}", deMessage.getOriginalId(), deMessage.getEncryptdMsg());
			Assert.assertNotNull(result);
		}
	
    }

}
