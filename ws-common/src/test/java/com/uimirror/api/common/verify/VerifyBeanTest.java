/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.register
 * @createdOn 10-Mar-20141:41:09 am
 * @modifiedby Jayaram
 * @modifiedon 10-Mar-20141:41:09 am
 * ***********************************************************************
 */

package com.uimirror.api.common.verify;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path.Node;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import com.uimirror.api.common.base.BaseTest;
import com.uimirror.api.common.base.JUnit4ClassRunner;
import com.uimirror.api.common.base.LoadExternalJson;
import com.uimirror.api.common.bean.VerifyBean;
import com.uimirror.api.common.verify.service.VerificationCheckPoint;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.util.StringUtility;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : test case for all the form related validations.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 10-Mar-20141:41:09 am
 * @modifiedby Jayaram
 * @modifiedon 10-Mar-20141:41:09 am
 * ***********************************************************************
 */
@RunWith(JUnit4ClassRunner.class)
public class VerifyBeanTest extends BaseTest{

	protected static final Logger LOG = LoggerFactory.getLogger(VerifyBeanTest.class);
	static String TEST_DATA = "testdata";
    @Autowired 
    private Validator validator;
    
    @Autowired
    private VerificationCheckPoint verificationCheckPoint;
    
    private Map<String, Object> validData;
    private Map<String, Object> invalidVrfRefData;
    private Map<String, Object> invalidEmailData;
    private Map<String, Object> invalidTemProfileId;
    private Map<String, Object> invalidToken;
    
    @SuppressWarnings("unchecked")
	@Before
    public void setUp() throws Exception {
    	validData = (Map<String, Object>) LoadExternalJson.loadData("verify/valid_data_set_1.txt", Map.class);
    	invalidVrfRefData = (Map<String, Object>) LoadExternalJson.loadData("verify/invalid_data_set_1.txt", Map.class);
    	invalidEmailData = (Map<String, Object>) LoadExternalJson.loadData("verify/invalid_data_set_2.txt", Map.class);
    	invalidTemProfileId = (Map<String, Object>) LoadExternalJson.loadData("verify/invalid_data_set_3.txt", Map.class);
    	invalidToken = (Map<String, Object>) LoadExternalJson.loadData("verify/invalid_data_set_4.txt", Map.class);
    	
    }
    
    /**
     * <p>This method will test the form data entered is valid or not
     * @author Gourang
     * @param 
     * @return void
     */
	@Test
	public void testValidForm() {
    	StopWatch stopWatch = new StopWatch("verifyValidForm");
    	stopWatch.start();
    	VerifyBean vrfy = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) validData.get(TEST_DATA);
    	for(Map<String, Object> map : dataSets){
    		String tokenType = (String)map.get(CommonConstants.VRF_REF);
    		if(CommonConstants.EMAIL.equals(tokenType)){
    			Mockito.when(verificationCheckPoint.tokenCheckPoint((String)map.get(CommonConstants.PRF_ID), (String)map.get(CommonConstants.EMAIL), (String)map.get(CommonConstants.VRF_TOKEN), CommonConstants.EMAIL)).thenReturn(new HashMap<String, Object>());
    		}else{
    			Mockito.when(verificationCheckPoint.tokenCheckPoint((String)map.get(CommonConstants.PRF_ID), (String)map.get(CommonConstants.EMAIL), (String)map.get(CommonConstants.VRF_TOKEN), CommonConstants.FORM)).thenReturn(new HashMap<String, Object>());
    		}
    		vrfy = new VerifyBean(map);
    		Set<ConstraintViolation<VerifyBean>> violations = validator.validate(vrfy);
    		Assert.assertNotNull(violations);
    		Assert.assertEquals(0, violations.size());
    	}
    	stopWatch.stop();
		LOG.debug("verify Form Validation for 5 records {}", dataSets.size() ,StringUtility.formatStopSwatchSummery("User verification Validation", stopWatch));
    }

	/**
     * <p>This method will test if the email entered is valid or not
     * @author Gourang
     * @param
     * @return void
     */
	@Test
    public void testInvalidEmail() {
		VerifyBean vrf = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidEmailData.get(TEST_DATA);
    	for(Map<String, Object> map : dataSets){
    		vrf = new VerifyBean(map);
    		Set<ConstraintViolation<VerifyBean>> violations = validator.validate(vrf);
    		Assert.assertNotNull(violations);
    		Assert.assertEquals(1, violations.size());
    		for(ConstraintViolation<VerifyBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("vrfTkn", node.getName());
    				break;
    			}
    		}
    	}
    }

	/**
     * <p>This method will test if the profile id entered is valid or not
     * @author Gourang
     * @param
     * @return void
     */
	@Test
    public void testInvalidProfileId() {
    	VerifyBean vrf = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidTemProfileId.get(TEST_DATA);
    	for(Map<String, Object> map : dataSets){
    		vrf = new VerifyBean(map);
    		Set<ConstraintViolation<VerifyBean>> violations = validator.validate(vrf);
    		Assert.assertNotNull(violations);
    		Assert.assertEquals(1, violations.size());
    		for(ConstraintViolation<VerifyBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("vrfTkn", node.getName());
    				break;
    			}
    		}
    	}
    }
    
	/**
     * <p>This method will test if the token entered is valid or not
     * @author Gourang
     * @param
     * @return void
     */
	@Test
    public void testInvalidToken(){
	 	VerifyBean vrf = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidToken.get(TEST_DATA);
    	for(Map<String, Object> map : dataSets){
    		vrf = new VerifyBean(map);
    		Set<ConstraintViolation<VerifyBean>> violations = validator.validate(vrf);
    		Assert.assertNotNull(violations);
    		Assert.assertEquals(1, violations.size());
    		for(ConstraintViolation<VerifyBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("vrfTkn", node.getName());
    				break;
    			}
    		}
    	}
     }
	/**
     * <p>This method will test if the verification reference is valid or not
     * @author Gourang
     * @param
     * @return void
     */
	@Test
    public void testInvalidRef() {
    	VerifyBean vrf = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidVrfRefData.get(TEST_DATA);
    	for(Map<String, Object> map : dataSets){
    		vrf = new VerifyBean(map);
    		Set<ConstraintViolation<VerifyBean>> violations = validator.validate(vrf);
    		Assert.assertNotNull(violations);
    		Assert.assertEquals(1, violations.size());
    		for(ConstraintViolation<VerifyBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("vrfTkn", node.getName());
    				break;
    			}
    		}
    	}
    }
}


