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

package com.uimirror.api.common.register;

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
import com.uimirror.api.common.bean.RegisterBean;
import com.uimirror.api.common.register.service.RegisterCheckPoint;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.annotation.ProfileExecution;
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
public class RegisterBeanTest extends BaseTest{

	protected static final Logger LOG = LoggerFactory.getLogger(RegisterBeanTest.class);
	static String TEST_DATA = "testdata";
    @Autowired 
    private Validator validator;
    
    @Autowired
    private RegisterCheckPoint registerCheckPoint;
    
    private Map<String, Object> validData;
    private Map<String, Object> invalidFirstnameData;
    private Map<String, Object> invalidLastnameData;
    private Map<String, Object> invalidEmailData;
    private Map<String, Object> invalidPwdData;
    private Map<String, Object> invalidDOBData;
    private Map<String, Object> invalidSexData;
    
    @SuppressWarnings("unchecked")
	@Before
    public void setUp() throws Exception {
    	validData = (Map<String, Object>) LoadExternalJson.loadData("register/valid_data_set_1.txt", Map.class);
    	invalidFirstnameData = (Map<String, Object>) LoadExternalJson.loadData("register/invalid_data_set_1.txt", Map.class);
    	invalidLastnameData = (Map<String, Object>) LoadExternalJson.loadData("register/invalid_data_set_2.txt", Map.class);
    	invalidEmailData = (Map<String, Object>) LoadExternalJson.loadData("register/invalid_data_set_3.txt", Map.class);
    	invalidPwdData = (Map<String, Object>) LoadExternalJson.loadData("register/invalid_data_set_4.txt", Map.class);
    	invalidDOBData = (Map<String, Object>) LoadExternalJson.loadData("register/invalid_data_set_5.txt", Map.class);
    	invalidSexData = (Map<String, Object>) LoadExternalJson.loadData("register/invalid_data_set_6.txt", Map.class);
    }
    
    /**
     *<p>This will test the valid form data
     *@author Gourang
     *@param
     *@return void
     */
    @Test
    @ProfileExecution
    public void testValidForm() {
    	StopWatch stopWatch = new StopWatch("User Registeration Form Validation");
    	stopWatch.start();
    	RegisterBean reg = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) validData.get(TEST_DATA);
    	for(Map<String, Object> map : dataSets){
    		Mockito.when(registerCheckPoint.isEmailExists((String)map.get(CommonConstants.EMAIL), 24)).thenReturn(false);
    		reg = new RegisterBean(map);
    		Set<ConstraintViolation<RegisterBean>> violations = validator.validate(reg);
    		Assert.assertNotNull(violations);
    		Assert.assertEquals(0, violations.size());
    	}
    	stopWatch.stop();
		LOG.debug("Register Form Validation for {} records {}", dataSets.size() ,StringUtility.formatStopSwatchSummery("User Registeration Form Validation", stopWatch));
    }

    /**
     *<p>This will test if the first name entered is valid or not
     *@author Gourang
     *@param
     *@return void
     */
    @Test
    @ProfileExecution
    public void testInvalidFName() {
    	RegisterBean rg = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidFirstnameData.get(TEST_DATA);
    	for(Map<String, Object> map : dataSets){
    		rg = new RegisterBean(map);
    		Set<ConstraintViolation<RegisterBean>> violations = validator.validate(rg);
    		Assert.assertNotNull(violations);
    		for(ConstraintViolation<RegisterBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("fname", node.getName());
    				break;
    			}
    		}
    	}
    	
    }

    /**
     *<p>This will test if the last name entered is valid or not
     *@author Gourang
     *@param
     *@return void
     */
    @Test
    @ProfileExecution
    public void testInvalidLName() {
    	RegisterBean rg = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidLastnameData.get(TEST_DATA);
    	for(Map<String, Object> map : dataSets){
    		rg = new RegisterBean(map);
    		Set<ConstraintViolation<RegisterBean>> violations = validator.validate(rg);
    		Assert.assertNotNull(violations);
    		for(ConstraintViolation<RegisterBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("lname", node.getName());
    				break;
    			}
    		}
    	}
    }
    
    /**
     *<p>This will test if the email entered is valid or not
     *@author Gourang
     *@param
     *@return void
     */
    @Test
    @ProfileExecution
    public void testInvalidEmail() {
    	RegisterBean rg = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidEmailData.get(TEST_DATA);
    	for(Map<String, Object> map : dataSets){
    		rg = new RegisterBean(map);
    		Set<ConstraintViolation<RegisterBean>> violations = validator.validate(rg);
    		Assert.assertNotNull(violations);
    		for(ConstraintViolation<RegisterBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("email", node.getName());
    				break;
    			}
    		}
    	}
    }
        
    /**
     *<p>This will test if the password entered is correct or not
     *@author Gourang
     *@param
     *@return void
     */
    @Test
    @ProfileExecution
    public void testInvalidPwd() {
    	RegisterBean rg = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidPwdData.get(TEST_DATA);
    	for(Map<String, Object> map : dataSets){
    		rg = new RegisterBean(map);
    		Set<ConstraintViolation<RegisterBean>> violations = validator.validate(rg);
    		Assert.assertNotNull(violations);
    		for(ConstraintViolation<RegisterBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("password", node.getName());
    				break;
    			}
    		}
    	}
    }
   
    /**
     *<p>This will test if the date of birth entered is valid or not
     *@author Gourang
     *@param
     *@return void
     */
    @Test
    @ProfileExecution
    public void testInvalidDoB() {
    	RegisterBean rg = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidDOBData.get(TEST_DATA);
    	for(Map<String, Object> map : dataSets){
    		rg = new RegisterBean(map);
    		Set<ConstraintViolation<RegisterBean>> violations = validator.validate(rg);
    		Assert.assertNotNull(violations);
    		for(ConstraintViolation<RegisterBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("date of birth", node.getName());
    				break;
    			}
    		}
    	}
    }

    /**
     *<p>This will test if the sex is correct or not
     *@author Gourang
     *@param
     *@return void
     */
    @Test
    @ProfileExecution
    public void testInvalidSex() {
    	RegisterBean rg = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidSexData.get(TEST_DATA);
    	for(Map<String, Object> map : dataSets){
    		rg = new RegisterBean(map);
    		Set<ConstraintViolation<RegisterBean>> violations = validator.validate(rg);
    		Assert.assertNotNull(violations);
    		for(ConstraintViolation<RegisterBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("sex", node.getName());
    				break;
    			}
    		}
    	}
    }
}