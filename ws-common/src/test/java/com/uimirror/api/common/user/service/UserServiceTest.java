package com.uimirror.api.common.user.service;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.uimirror.api.common.base.BaseTest;
import com.uimirror.api.common.base.JUnit4ClassRunner;
import com.uimirror.api.common.exception.CommonApiSystemException;
import com.uimirror.api.common.user.dao.UserDao;

@RunWith(JUnit4ClassRunner.class)
public class UserServiceTest extends BaseTest{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;

	@Test
	public void testModifyUserById() throws CommonApiSystemException {
		//TODO populate this map
		Map<String, Object> request = new HashMap<String, Object>();
		Mockito.spy(userDao);
		userService.modifyUserById(request);
		fail("Not yet implemented");
	}

}
