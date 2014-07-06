package com.uimirror.common.authentication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.uimirror.common.CommonConstants;
import com.uimirror.common.LoadExternalJson;
import com.uimirror.common.authentication.dao.AuthenticationDao;
import com.uimirror.common.authentication.service.AuthenticationService;
import com.uimirror.common.base.BaseTest;
import com.uimirror.common.base.JUnit4ClassRunner;
import com.uimirror.common.exception.CommonSystemException;
import com.uimirror.common.meta.dao.DeviceDao;
import com.uimirror.common.util.DateTimeFactory;

@RunWith(JUnit4ClassRunner.class)
public class AuthenticationServiceTest extends BaseTest{
	protected static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceTest.class);
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private AuthenticationDao authenticationDao;
	@Autowired
	private DeviceDao deviceDao;
	
	private List<Map<String, Object>> validFormData;
	private List<Map<String, Object>> validSessionData;
	
	@SuppressWarnings("unchecked")
	@Before
    public void setUp() throws Exception {
		validFormData = (List<Map<String, Object>>) LoadExternalJson.loadData("authentication/service_valid_data_set_1.txt", List.class);
    	validSessionData = (List<Map<String, Object>>) LoadExternalJson.loadData("authentication/service_valid_session_data_set_1.txt", List.class);
    }

	@SuppressWarnings("unchecked")
	@Test
	public void testDoAuthenticate() throws CommonSystemException, InterruptedException {
		Mockito.doCallRealMethod().when(authenticationDao).createLoginSession(Mockito.anyMap());
		for(Map<String, Object> map : validFormData){
			
			Mockito.when(authenticationDao.getNextSessionId()).thenReturn((String)map.get("sessionid"));
			Map<String, Object> user = new HashMap<String, Object>();
			user.put(CommonConstants._ID, (String)map.get("profileid"));
			Mockito.when(authenticationDao.getUserByCredentials(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(user);
			Mockito.when(deviceDao.getDeviceById(Mockito.anyString())).thenReturn(new HashMap<String, Object>());
			//Mockito.doThrow(new IllegalAccessError()).when(authenticationDao.createLoginSession(Mockito.anyMap())).someVoidMethod();
			
			//Mockito.doNothing().when(authenticationDao).createLoginSession(Mockito.anyMap());
			AuthenticationBean auth = new AuthenticationBean((Map<String, Object>)map.get("request"));
			Map<String, Object> res = authenticationService.doAuthenticate(auth);
			LOG.info("Authentication Response--->{}",res);
			Assert.assertNotNull(res);
			Assert.assertEquals("200", (String)res.get(CommonConstants.RESPONSE_CODE));
			Thread.sleep(1000);
		}
	}
	
	@Test
	public void testDoSessionAuthenticate() throws CommonSystemException, InterruptedException {
		
		for(Map<String, Object> map : validSessionData){
			
			Mockito.when(authenticationDao.getNextSessionId()).thenReturn((String)map.get("nextsessionid"));
			
			Map<String, Object> user = new HashMap<String, Object>();
			user.put(CommonConstants.PRF_ID, (String)map.get("profileid"));
			user.put(CommonConstants.AUTH_TOKEN, (String)map.get("token"));
			user.put(CommonConstants.AUTH_TOKEN_EXPIRES_ON, DateTimeFactory.getDateAfterSomeDays(1));
			user.put(CommonConstants.IP, (String)map.get("ip"));
			user.put(CommonConstants.FL_ON, DateTimeFactory.getCurrentDateTimeInString());
			user.put(CommonConstants.AUTH_TOKEN_INTERVAL, (String)map.get("interval"));
			
			Mockito.when(authenticationDao.getSessionByAuthId(Mockito.anyString())).thenReturn(user);
			
			Mockito.when(deviceDao.getDeviceById(Mockito.anyString())).thenReturn(new HashMap<String, Object>());

			@SuppressWarnings("unchecked")
			AuthenticationBean auth = new AuthenticationBean((Map<String, Object>)map.get("request"));
			
			Map<String, Object> res = authenticationService.doAuthenticate(auth);
			LOG.info("Authentication Response--->{}",res);
			Assert.assertNotNull(res);
			Assert.assertEquals("200", (String)res.get(CommonConstants.RESPONSE_CODE));
			Thread.sleep(1000);
			
		}
	}
	
	

}
