/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.register.service
 * @createdOn 30-Mar-2014 1:39:43 AM
 * @modifiedby Jayaram
 * @modifiedon 30-Mar-2014 1:39:43 AM
 * ***********************************************************************
 */
package com.uimirror.api.common.register.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.uimirror.api.common.register.dao.TempUserDao;
import com.uimirror.common.CommonConstants;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : This will be main gate keeper for 
 * Re-send token email user validation.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 30-Mar-2014 1:39:43 AM
 * @modifiedby Jayaram
 * @modifiedon 30-Mar-2014 1:39:43 AM
 * ***********************************************************************
 */
public class ResendTokenEmailCheckPoint {
	
	protected static final Logger LOG = LoggerFactory.getLogger(ResendTokenEmailCheckPoint.class);
	
	@Autowired
	private TempUserDao tempUserDao;
	
	public Map<String, Object> isValidResendRequest(final String profileId, final String email){
		LOG.info("[START]- Validating user details for resend email token");
		
		//Step 1- Check parameters first, it shouldn't be null
		Assert.hasText(profileId, "Profile Id can't be empty");
		Assert.hasText(email, "Email can't be empty");
		
		//Step 2- Get user from DB
		Map<String, Object> user = tempUserDao.getUserSpecificFields(buildEMailSelectFiledMap(), profileId);
		
		
		LOG.info("[END]- Validating user details for resend email token");
		//Step 3- Match User email is correct or not
		if(user != null && email.equals(user.get(CommonConstants.EMAIL))){
			return user;
		}else{
			return null;
		}
		
	}
	
	/**
	 * <p>builds map for the projection fields so that it will get only email fields.
	 * @return
	 */
	private Map<String, Object> buildEMailSelectFiledMap(){
		Map<String, Object> fields = new HashMap<String, Object>(1);
		fields.put(CommonConstants.EMAIL, 1);
		fields.put(CommonConstants.FL_FIRST_NAME, 1);
		return fields;
	}

}
