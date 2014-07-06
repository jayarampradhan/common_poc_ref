package com.uimirror.api.common.authorization;

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
import com.uimirror.common.authorization.dao.AuthorizationDao;

@RunWith(JUnit4ClassRunner.class)
public class AuthorizationDaoTest extends BaseTest{
	
	@Autowired
	private AuthorizationDao authorizationDao;

	@Test
	public void testCreateAuthorization() {
		for(Map<String, Object> map : prepareCreateAutorizationTestData()){
			authorizationDao.createAuthorization(map);
		}
		Assert.assertTrue(true);
	}

	@Test
	public void testRevokeAuthorization() {
		Map<String, Object> obj1 = new HashMap<String, Object>(5);
		Map<String, Object> obj = new HashMap<String, Object>(5);
		obj.put("id", "11");
		obj.put("type", "group");
		obj.put("role", "AUTHOR");
		obj1.put("object", obj);
		authorizationDao.revokeAuthorization(obj1, "13");
	}

	@Test
	public void testAddAuthorization() {
		authorizationDao.addAuthorization(prepareObjectsForAddAutorization(),"13");
		Assert.assertTrue(true);	}


	private List<Map<String, Object>> prepareObjectsForAddAutorization() {
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>(5);
		Map<String, Object> obj = new HashMap<String, Object>(5);
		obj.put("id", "11");
		obj.put("type", "group");
		obj.put("role", "AUTHOR");
		ls.add(obj);
		return ls;
	}

	@Test
	public void testGetAccessMapByProfileId() {
		
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetSpecificAccessMapByProfileId() {
		//fail("Not yet implemented");
	}
	
	private List<Map<String, Object>> prepareCreateAutorizationTestData(){
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>(5);
		
		Map<String, Object> map1 = new HashMap<String, Object>(5);
		map1.put("_id", "22");
		map1.put("accounttype", "Basic");
		map1.put("object", prepareObjectsForRole());
		ls.add(map1);
		
		Map<String, Object> map2 = new HashMap<String, Object>(5);
		map2.put("_id", "23");
		map2.put("accounttype", "Basic");
		map2.put("object", prepareObjectsForRole());
		ls.add(map2);
		
		Map<String, Object> map3 = new HashMap<String, Object>(5);
		map3.put("_id", "24");
		map3.put("accounttype", "Basic");
		map3.put("object", prepareObjectsForRole());
		ls.add(map3);
		
		Map<String, Object> map4 = new HashMap<String, Object>(5);
		map4.put("_id", "25");
		map4.put("accounttype", "Basic");
		map4.put("object", prepareObjectsForRole());
		ls.add(map4);
		
		Map<String, Object> map5 = new HashMap<String, Object>(5);
		map5.put("_id", "26");
		map5.put("accounttype", "Basic");
		map5.put("object", prepareObjectsForRole());
		ls.add(map5);
		return ls;
	}
	
	private List<Map<String, Object>> prepareObjectsForRole(){
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>(5);
		Map<String, Object> obj = new HashMap<String, Object>(5);
		obj.put("id", "1");
		obj.put("type", "group");
		obj.put("role", "AUTHOR");
		ls.add(obj);
		
		Map<String, Object> obj1 = new HashMap<String, Object>(5);
		obj1.put("id", "2");
		obj1.put("type", "group");
		obj1.put("role", "MEMBER");
		obj1.put("addon", "14-03-2014");
		ls.add(obj1);
		
		Map<String, Object> obj2 = new HashMap<String, Object>(5);
		obj2.put("id", "3");
		obj2.put("type", "group");
		obj2.put("role", "ADMIN");
		obj2.put("addon", "14-03-2014");
		ls.add(obj2);
		
		return ls;
	}

}
