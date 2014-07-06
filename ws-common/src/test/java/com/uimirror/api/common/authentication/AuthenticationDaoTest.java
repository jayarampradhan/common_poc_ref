package com.uimirror.api.common.authentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.uimirror.api.common.base.BaseTest;
import com.uimirror.api.common.base.JUnit4ClassRunner;
import com.uimirror.common.authentication.dao.AuthenticationDao;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Test case for all the authentication 
 * Related dao operation.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 24-Mar-2014 3:27:54 PM
 * @modifiedby Jayaram
 * @modifiedon 24-Mar-2014 3:27:54 PM
 * ***********************************************************************
 */
@RunWith(JUnit4ClassRunner.class)
public class AuthenticationDaoTest extends BaseTest{
	
	@Autowired
	private AuthenticationDao authenticationDao;

	@Test
	public void testAdduser() {
		for(Map<String, Object> map : prepareTestDataForAddUser()){
			authenticationDao.addUser(map);
		}
		Assert.assertTrue(true);
	}
	
	@Test
	public void testModifyPassword(){
		for(Map<String, Object> map : prepareTestDataForModifyPassword()){
			authenticationDao.modifyCurrentPassword(map, "12");
		}
		Assert.assertTrue(true);
	}
	@Test
	public void testCreatingNewSession(){
		for(Map<String, Object> map : preareLoginSessionData()){
			authenticationDao.createLoginSession(map);
		}
		Assert.assertTrue(true);
		
	}
	
	@Test
	public void testGetLoginSessionByLoginId(){
		Assert.assertNotNull(authenticationDao.getSessionByAuthId("4"));
	}
	
	@Test
	public void testUdateSessionToken(){
		for(Map<String, Object> map : preareLoginSessionUpdateTokenData()){
			authenticationDao.updateLoginSession(map, "1");
		}
	}
	
	
	/**
	 * <p>This will create test data for the adding user into authentication.
	 * @return
	 */
	private List<Map<String, Object>> prepareTestDataForModifyPassword(){
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>(5);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("password", "xyztes");
		map.put("changedon", "24-03-2014");

		ls.add(map);

		return ls;
	}
	
	/**
	 * <p>This will create test data for the adding user into authentication.
	 * @return
	 */
	private List<Map<String, Object>> prepareTestDataForAddUser(){
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>(5);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("_id", "14");
		map.put("uid", buildUserId("jayaramimca@gmail.com"));
		map.put("password", "12345");
		
		ls.add(map);

		return ls;
	}
	
	private Map<String, Object> buildUserId(String email){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		return map;
	}
	
	private List<Map<String, Object>> preareLoginSessionData(){
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uprofileid", "123");
		map.put("token", "456");
		map.put("device", "Test");
		map.put("ip", "127.0.0.1");
		map.put("on", "24-03-2014");
		map.put("expiry", "24-03-2014");
		ls.add(map);
		return ls;
	}
	
	private List<Map<String, Object>> preareLoginSessionUpdateTokenData(){
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", "123");
		map.put("expiry", "24-03-2014");
		ls.add(map);
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("token", "768");
		map1.put("expiry", "24-03-2014");
		ls.add(map1);
		return ls;
	}

}
