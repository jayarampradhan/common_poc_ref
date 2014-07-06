/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.verify.service
 * @createdOn 27-Mar-2014 5:12:46 PM
 * @modifiedby Jayaram
 * @modifiedon 27-Mar-2014 5:12:46 PM
 * ***********************************************************************
 */
package com.uimirror.api.common.verify.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.uimirror.api.common.register.dao.TempUserDao;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.util.StringUtility;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : This is the main check point for the 
 * verification process.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 27-Mar-2014 5:12:46 PM
 * @modifiedby Jayaram
 * @modifiedon 27-Mar-2014 5:12:46 PM
 * ***********************************************************************
 */
public class VerificationCheckPoint {
	
	protected static final Logger LOG = LoggerFactory.getLogger(VerificationCheckPoint.class);
	
	@Autowired
	private TempUserDao tempUserDao;
	
	/**
	 * <p>This will verify the token entered by the user is correct or not.
	 * @param profileId
	 * @param email
	 * @param token
	 * @param tokenType
	 * @return
	 */
	public Map<String, Object> tokenCheckPoint(final String profileId, final String email, final String token, final String tokenType){
		LOG.info("[START]- validating Form token entered by the user.");
		//Step 1- Validate if input is not null
		if(StringUtility.checkEmptyString(profileId, email, token)){
			return null;
		}
		try{
			//Step 2- Get user by profile id from Temp Table
			Map<String, Object> user = tempUserDao.getUser(profileId);
			Assert.notEmpty(user);
			return (isValidUser(user, email, token, tokenType)) ? user : null; 

		}catch(Exception e){
			LOG.error("[EXCEPTION]- validating user {}",e);
			return null;
		}finally{
			LOG.info("[END]- validating Form token entered by the user.");
		}
		
	}
	
	/**
	 * <p>This will check the retrieved user is not valid or not on the basics of email and token code.
	 * @param user
	 * @param email
	 * @param token
	 * @param tokenType
	 * @return
	 */
	private boolean isValidUser(Map<String, Object> user, final String email, final String token, final String tokenType){
		
		String emailTovalidate = (String)user.get(CommonConstants.EMAIL);
		
		String tokenToValidate = CommonConstants.EMPTY_STRING;
		
		if(CommonConstants.EMAIL.equals(tokenType)){
			tokenToValidate = (String)user.get(CommonConstants.FL_LN_TOKEN);
		}else{
			tokenToValidate = (String)user.get(CommonConstants.FL_FORM_TOKEN);
		}
		
		if(email.equals(emailTovalidate) && token.equals(tokenToValidate)){
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}

}
