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
package com.uimirror.api.common.authentication;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path.Node;
import javax.validation.Validator;

import org.apache.commons.collections.map.HashedMap;
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
import com.uimirror.common.CommonConstants;
import com.uimirror.common.annotation.ProfileExecution;
import com.uimirror.common.authentication.AuthenticationBean;
import com.uimirror.common.authentication.service.AuthenticationCheckPoint;
import com.uimirror.common.util.StringUtility;
/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : test case for all the form related validations.
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 16-Mar-20141:41:09 am
 * @modifiedby Jayaram
 * @modifiedon 16-Mar-20141:41:09 am
 * ***********************************************************************
 */
@RunWith(JUnit4ClassRunner.class)
public class AuthenticationBeanTest extends BaseTest{

	protected static final Logger LOG = LoggerFactory.getLogger(AuthenticationBeanTest.class);
    @Autowired 
    private Validator validator;
    
    private Map<String, Object> invalidFormData;
    
    private Map<String, Object> invalidEmailData;
    
    private Map<String, Object> invalidPasswordData;
    
    private Map<String, Object> invalidClientData;
    
    private Map<String, Object> invalidCookieProfData;
    
    private Map<String, Object> invalidIpData;
    
    private Map<String, Object> invalidLoginIdData;
    
    private Map<String, Object> invalidLoginTokenData;
    
    private Map<String, Object> validData;
    
    @Autowired
    private AuthenticationCheckPoint authenticationCheckPoint;
    
    static String TEST_DATA = "testdata";
    
    @SuppressWarnings("unchecked")
	@Before
    public void setUp() throws Exception {
    	invalidFormData = (Map<String, Object>) LoadExternalJson.loadData("authentication/invalid_data_set_1.txt", Map.class);
    	invalidEmailData = (Map<String, Object>) LoadExternalJson.loadData("authentication/invalid_data_set_2.txt", Map.class);
    	invalidPasswordData = (Map<String, Object>) LoadExternalJson.loadData("authentication/invalid_data_set_3.txt", Map.class);
    	invalidClientData = (Map<String, Object>) LoadExternalJson.loadData("authentication/invalid_data_set_4.txt", Map.class);
    	invalidCookieProfData = (Map<String, Object>) LoadExternalJson.loadData("authentication/invalid_data_set_5.txt", Map.class);
    	invalidIpData = (Map<String, Object>) LoadExternalJson.loadData("authentication/invalid_data_set_6.txt", Map.class);
    	invalidLoginIdData = (Map<String, Object>) LoadExternalJson.loadData("authentication/invalid_data_set_7.txt", Map.class);
    	invalidLoginTokenData = (Map<String, Object>) LoadExternalJson.loadData("authentication/invalid_data_set_8.txt", Map.class);
    	validData = (Map<String, Object>) LoadExternalJson.loadData("authentication/valid_data_set_1.txt", Map.class);
    }

    
    
    /**
     * <p>This method will test if the form data received is valid or not
     * @author Gourang
     * @param void
     * @return void
     * @throws IOException
     */

    @Test
    @ProfileExecution
    public void testInvalidForm() throws IOException {
    	StopWatch stopWatch = new StopWatch("authenticationInvalidForm");
    	stopWatch.start();
    	AuthenticationBean auth = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidFormData.get(TEST_DATA);
    	for (Map<String, Object> authMap : dataSets){
    		auth = new AuthenticationBean(authMap);
    		Set<ConstraintViolation<AuthenticationBean>> violations = validator.validate(auth);
    		Assert.assertNotNull(violations);
    		for(ConstraintViolation<AuthenticationBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("user_id", node.getName());
    				break;
    			}
    		}
    	}
    	stopWatch.stop();
		LOG.debug("Login form Validation for {} records {}", dataSets.size(), StringUtility.formatStopSwatchSummery("authenticationInvalidForm", stopWatch));
    }
    
    @Test
    @ProfileExecution
    public void testInvalidCookieForm(){
    	/*StopWatch stopWatch = new StopWatch("testInvalidCookieForm");
    	  stopWatch.start();
    	 */
    }
    /**
     * <p>This method will test if the email entered is valid or not
     * @author Gourang
     * @param 
     * @return void
     * @throws 
     */
    @Test
    @ProfileExecution
    public void testInvalidEmail(){
    	StopWatch stopWatch = new StopWatch("emailValidator");
    	stopWatch.start();
    	AuthenticationBean auth = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidEmailData.get(TEST_DATA);
    	for(Map<String, Object> authMap : dataSets){
    		auth = new AuthenticationBean(authMap);
    		Set<ConstraintViolation<AuthenticationBean>> violations = validator.validate(auth);
    		Assert.assertNotNull(violations);
    		for(ConstraintViolation<AuthenticationBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("user_id", node.getName());
    				break;
    			}
    		}
    	}
    	stopWatch.stop();
    	LOG.debug("Email Validation for {} records {}",dataSets.size(), StringUtility.formatStopSwatchSummery("emailValidator", stopWatch));
    }

    /**
     * <p>This method will test if the password entered  is valid or not
     * @author Gourang
     * @param 
     * @return void
     */
	@Test
	@ProfileExecution
    public void testInvalidPassword(){
		StopWatch stopWatch = new StopWatch("passwordValidator");
    	stopWatch.start();
    	AuthenticationBean auth = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidPasswordData.get(TEST_DATA);
    	for(Map<String, Object> authMap : dataSets){
    		auth = new AuthenticationBean(authMap);
    		Set<ConstraintViolation<AuthenticationBean>> violations = validator.validate(auth);
    		Assert.assertNotNull(violations);
    		for(ConstraintViolation<AuthenticationBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("pwd", node.getName());
    				break;
    			}
    		}
    	}
    	stopWatch.stop();
    	LOG.debug("Password Validation for {} records {}",dataSets.size() ,StringUtility.formatStopSwatchSummery("passwordValidator", stopWatch));
    }
	/**
     * <p>This method will test if the client meta received is valid or not
     * @author Gourang
     * @param void
     * @return void
     */
    @Test
    @ProfileExecution
    public void testInvalidClientMeta(){
    	StopWatch stopWatch = new StopWatch("clientMetaValidator");
    	stopWatch.start();
    	AuthenticationBean auth = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidClientData.get(TEST_DATA);
    	for(Map<String, Object> authMap : dataSets){
    		auth = new AuthenticationBean(authMap);
    		Set<ConstraintViolation<AuthenticationBean>> violations = validator.validate(auth);
    		Assert.assertNotNull(violations);
    		for(ConstraintViolation<AuthenticationBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("email", node.getName());
    				break;
    			}
    		}
    	}
    	stopWatch.stop();
    	LOG.debug("Client Meta Validation for {} records {}",dataSets.size(), StringUtility.formatStopSwatchSummery("clientMetaValidator", stopWatch));
    }
    /**
     * <p>This method will validate the profile id set in the cookie
     * @author Gourang
     * @param void
     * @return void
     */
    @Test
    @ProfileExecution
    public void testInvalidCookieProfileId(){
    	StopWatch stopWatch = new StopWatch("cookieProfileIdValidator");
    	stopWatch.start();
    	AuthenticationBean auth = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidCookieProfData.get(TEST_DATA);
    	for(Map<String, Object> authMap : dataSets){
    		auth = new AuthenticationBean(authMap);
    		Set<ConstraintViolation<AuthenticationBean>> violations = validator.validate(auth);
    		Assert.assertNotNull(violations);
    		for(ConstraintViolation<AuthenticationBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("email", node.getName());
    				break;
    			}
    		}
    	}
    	stopWatch.stop();
    	LOG.debug("Cookie Profileid Validation for {} records {}",dataSets.size(), StringUtility.formatStopSwatchSummery("cookieProfileIdValidator", stopWatch));
    }

    /**
     * <p>This method will validate the ip set in the cookie
     * @author Gourang
     * @param void
     * @return void
     */
	@Test
	@ProfileExecution
    public void testInvalidIp(){
    	StopWatch stopWatch = new StopWatch("ipValidator");
    	stopWatch.start();
    	AuthenticationBean auth = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidIpData.get(TEST_DATA);
    	for(Map<String, Object> authMap : dataSets){
    		auth = new AuthenticationBean(authMap);
    		Set<ConstraintViolation<AuthenticationBean>> violations = validator.validate(auth);
    		Assert.assertNotNull(violations);
    		for(ConstraintViolation<AuthenticationBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("email", node.getName());
    				break;
    			}
    		}
    	}
    	stopWatch.stop();
    	LOG.debug("CookieIp Validation for {} records {}", dataSets.size(), StringUtility.formatStopSwatchSummery("ipValidator", stopWatch));
    }
	
	/**
     * <p>This method will validate the login id set in the cookie
     * @author Gourang
     * @param void
     * @return void
     */
	@Test
	@ProfileExecution
    public void testInvalidCookieLogInId(){
    	StopWatch stopWatch = new StopWatch("cookieloginIdValidator");
    	stopWatch.start();
    	AuthenticationBean auth = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidLoginIdData.get(TEST_DATA);
    	for(Map<String, Object> authMap : dataSets){
    		auth = new AuthenticationBean(authMap);
    		Set<ConstraintViolation<AuthenticationBean>> violations = validator.validate(auth);
    		Assert.assertNotNull(violations);
    		for(ConstraintViolation<AuthenticationBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("email", node.getName());
    				break;
    			}
    		}
    	}
    	stopWatch.stop();
    	LOG.debug("Cookie Validation for {} records {}", dataSets.size() ,StringUtility.formatStopSwatchSummery("cookieloginIdValidator", stopWatch));
    }

	/**
     * <p>This method will validate the token set in cookie
     * @author Gourang
     * @param void
     * @return void
     */
	@Test
	@ProfileExecution
    public void testInvalidCookieToken(){
		StopWatch stopWatch = new StopWatch("cookieValidator");
    	stopWatch.start();
    	AuthenticationBean auth = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidLoginTokenData.get(TEST_DATA);
    	for(Map<String, Object> authMap : dataSets){
    		auth = new AuthenticationBean(authMap);
    		Set<ConstraintViolation<AuthenticationBean>> violations = validator.validate(auth);
    		Assert.assertNotNull(violations);
    		for(ConstraintViolation<AuthenticationBean> violation : violations){
    			Iterator<Node> nodes = violation.getPropertyPath().iterator();
    			while(nodes.hasNext()){
    				Node node = nodes.next();
    				Assert.assertEquals("email", node.getName());
    				break;
    			}
    		}
    	}
    	stopWatch.stop();
    	LOG.debug("Cookie Validation for {} records {}",dataSets.size(), StringUtility.formatStopSwatchSummery("cookieValidator", stopWatch));
    }

	/**
     * <p>This method will test the valid form 
     * <p>Every data provided to this method are valid
     * @author Gourang
     * @param void
     * @return void
     */
	@Test
	@ProfileExecution
    public void testValidForm() {
    	StopWatch stopWatch = new StopWatch("authValidForm");
    	stopWatch.start();
    	
    	AuthenticationBean auth = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) validData.get(TEST_DATA);
    	for(Map<String, Object> authMap : dataSets){
    		if(CommonConstants.COOKIE.equals(authMap.get(CommonConstants.AUTH_TYPE))){
    			Mockito.when(authenticationCheckPoint.checkSession((String)authMap.get(CommonConstants.AUTH_ID), (String)authMap.get(CommonConstants.PRV_AUTH_ID), (String)authMap.get(CommonConstants.AUTH_TOKEN), (String)authMap.get(CommonConstants.PRV_AUTH_TOKEN), (String)authMap.get(CommonConstants.PRF_ID), (String)authMap.get(CommonConstants.FL_DEVICE), (String)authMap.get(CommonConstants.IP))).thenReturn(new HashedMap());
    		}else{
    			Mockito.when(authenticationCheckPoint.checkCredentials((String)authMap.get(CommonConstants.USER_ID), (String)authMap.get(CommonConstants.PASSWORD), (String)authMap.get(CommonConstants.USER_ID_TYPE), (String)authMap.get(CommonConstants.KEEP_ME_LGN), (String)authMap.get(CommonConstants.FL_DEVICE), (String)authMap.get(CommonConstants.IP))).thenReturn(new HashedMap());
    		}
    		auth = new AuthenticationBean(authMap);
    		Set<ConstraintViolation<AuthenticationBean>> violations = validator.validate(auth);
    		Assert.assertNotNull(violations);
    		Assert.assertEquals(0, violations.size());
    	}
    	stopWatch.stop();
		LOG.debug("Login Form Validation for {} records {}", dataSets.size(), StringUtility.formatStopSwatchSummery("authValidForm", stopWatch));
    }

}