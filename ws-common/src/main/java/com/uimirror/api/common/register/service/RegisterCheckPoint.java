/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.register.service
 * @createdOn 27-Mar-2014 11:51:49 AM
 * @modifiedby Jayaram
 * @modifiedon 27-Mar-2014 11:51:49 AM
 * ***********************************************************************
 */
package com.uimirror.api.common.register.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.uimirror.api.common.register.dao.TempUserDao;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.util.DateTimeFactory;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : This will be main gate keeper for 
 * Registration user validation.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 27-Mar-2014 11:51:49 AM
 * @modifiedby Jayaram
 * @modifiedon 27-Mar-2014 11:51:49 AM
 * ***********************************************************************
 */
public class RegisterCheckPoint {

	protected static final Logger LOG = LoggerFactory.getLogger(RegisterCheckPoint.class);
	
	@Autowired
	private TempUserDao tempUserDao;
	
//	@Autowired
//	private UserDao userDao;
	
	/**
	 * <p>Checks if user is already registered or not.
	 * <p>If user is registered but not verified his account in last 24 hrs,
	 * then that user will be deleted and this user will be created.
	 * @param email
	 * @param hours
	 * @return
	 */
	//TODO Open issue user save duplicate avoid
	public boolean isEmailExists(final String email, final int hours){
		LOG.info("[START]- Checking email existance");
		
		//Step 1- First check if the email id specified is not empty
		Assert.hasText(email);
		boolean userExistsFlg = Boolean.FALSE;

		//Step 2- Get user from Temp collection
		Map<String, Object> tempUser = tempUserDao.getUserByEmail(email);
		
		if(tempUser == null){
			//Check for permanent user//TODO check where you can get email of user
			Map<String, Object> user = null;//userProfileDao.getUserByEmail(email);
			userExistsFlg = user == null ? Boolean.FALSE : Boolean.TRUE;
		}else{
			
			String createdOn = (String) tempUser.get(CommonConstants.FL_ON);
			String tempId = (String) tempUser.get(CommonConstants._ID);
			Assert.hasText(createdOn);
			Assert.hasText(tempId);
			if(DateTimeFactory.getHourDifferance(createdOn) < hours){
				userExistsFlg = Boolean.TRUE;
			}else{
				//Delete the entry from the DataBase
				tempUserDao.deleteUser(tempId);
				userExistsFlg = Boolean.FALSE;
			}
		}
		LOG.info("[END]- Checking email existance");
		return userExistsFlg;
	}
}
