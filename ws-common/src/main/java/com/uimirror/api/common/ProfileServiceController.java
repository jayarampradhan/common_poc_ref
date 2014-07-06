/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common
 * @createdOn 20-Mar-2014 1:37:05 AM
 * @modifiedby Jayaram
 * @modifiedon 20-Mar-2014 1:37:05 AM
 * ***********************************************************************
 */
package com.uimirror.api.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.uimirror.common.annotation.ProfileExecution;
import com.uimirror.common.authentication.annotation.AuthenticationRequired;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : will read all the profile related 
 * information of the user.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 20-Mar-2014 1:37:05 AM
 * @modifiedby Jayaram
 * @modifiedon 20-Mar-2014 1:37:05 AM
 * ***********************************************************************
 */
@Component
public class ProfileServiceController {

	protected static final Logger LOG = LoggerFactory.getLogger(ProfileServiceController.class);
	
	/**
	 * <p>This will get the short summary of the user profile.
	 * <p>This will first check the user privacy.
	 * @param form
	 * @return
	 */
	@AuthenticationRequired
	@ProfileExecution
	public Map<String, Object> getShortSummery(Map<String, Object> request){
		LOG.info("[START]-Get User Profile short summery is intiated.");
		LOG.info("[END]-Get User Profile short summery is intiated.");
		return null;
	}
	
	/**
	 * <p>This will get all the details of the user.
	 * <p>This will first check the user privacy.
	 * @param form
	 * @return
	 */
	@AuthenticationRequired
	@ProfileExecution
	public Map<String, Object> getProfile(Map<String, Object> request){
		LOG.info("[START]-Get User Profile details is intiated.");
		LOG.info("[END]-Get User Profile details is intiated.");
		return null;
	}
}
