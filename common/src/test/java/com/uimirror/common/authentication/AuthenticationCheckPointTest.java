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
import com.uimirror.common.authentication.service.AuthenticationCheckPoint;
import com.uimirror.common.base.BaseTest;
import com.uimirror.common.base.JUnit4ClassRunner;
import com.uimirror.common.util.DateTimeFactory;

@RunWith(JUnit4ClassRunner.class)
public class AuthenticationCheckPointTest extends BaseTest{
	
	protected static final Logger LOG = LoggerFactory.getLogger(AuthenticationCheckPointTest.class);
	private List<Map<String, Object>> validData;
	private List<Map<String, Object>> validSessionData;
	
	@Autowired
	private AuthenticationDao authenticationDao;
	
	@Autowired
	private AuthenticationCheckPoint authenticationCheckPoint;
	
	@SuppressWarnings("unchecked")
	@Before
    public void setUp() throws Exception {
    	validData = (List<Map<String, Object>>) LoadExternalJson.loadData("authentication/check_credentials_data_set_1.txt", List.class);
    	validSessionData = (List<Map<String, Object>>) LoadExternalJson.loadData("authentication/check_session_data_set_1.txt", List.class);
    }
	
	@Test
	public void testCredentials() {
		for(Map<String, Object> map : validData){
			Mockito.when(authenticationDao.getNextSessionId()).thenReturn((String)map.get("sessionid"));
			Map<String, Object> user = new HashMap<String, Object>();
			user.put(CommonConstants._ID, (String)map.get("profileid"));
			Mockito.when(authenticationDao.getUserByCredentials((String)map.get("userId"), (String)map.get("password"), (String)map.get("uidtype"))).thenReturn(user);
			
			Map<String, Object> res = authenticationCheckPoint.checkCredentials((String)map.get("userId"), (String)map.get("password"), (String)map.get("uidtype"), (String)map.get("keepMeLogin"), (String)map.get("device"), (String)map.get("ip"));
			Assert.assertNotNull(res);
			LOG.info("Authentication response {}",res);
		}
	}
	
	@Test
	public void testLoginSession(){
		for(Map<String, Object> map : validSessionData){
			
			Map<String, Object> user = new HashMap<String, Object>();
			user.put(CommonConstants.PRF_ID, (String)map.get("profileid"));
			user.put(CommonConstants.AUTH_TOKEN, (String)map.get("token"));
			user.put(CommonConstants.AUTH_TOKEN_EXPIRES_ON, DateTimeFactory.getDateAfterSomeDays(1));
			user.put(CommonConstants.IP, (String)map.get("ip"));
			user.put(CommonConstants.FL_ON, DateTimeFactory.getCurrentDateTimeInString());
			user.put(CommonConstants.AUTH_TOKEN_INTERVAL, (String)map.get("interval"));
			
			Mockito.when(authenticationDao.getSessionByAuthId((String)map.get("authId"))).thenReturn(user);
			Mockito.when(authenticationDao.getNextSessionId()).thenReturn((String)map.get("nextsessionid"));
			
			Map<String, Object> res = authenticationCheckPoint.checkSession((String)map.get("authId"), (String)map.get("prevAuthId"), (String)map.get("token"), (String)map.get("prevToken"), (String)map.get("profileid"),(String)map.get("device"), (String)map.get("ip"));
			Assert.assertNotNull(res);
			LOG.info("Authentication Session response {}",res);
		}
	}

}
