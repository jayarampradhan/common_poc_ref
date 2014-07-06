/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.authentication.dao
 * @createdOn 24-Mar-2014 10:33:57 PM
 * @modifiedby Jayaram
 * @modifiedon 24-Mar-2014 10:33:57 PM
 * ***********************************************************************
 */
package com.uimirror.common.authentication.dao;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.uimirror.common.CommonConstants;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : contains utility method for authentication 
 * service.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 24-Mar-2014 10:33:57 PM
 * @modifiedby Jayaram
 * @modifiedon 24-Mar-2014 10:33:57 PM
 * ***********************************************************************
 */
public class AuthenticationDaoHelper {
	
	/**
	 * <p>Build the select query for getting user by user id and password.
	 * @param userId
	 * @param password
	 * @param userIdType
	 * @return
	 */
	public static DBObject getQueryForCredentialsByType(String userId, String password, String userIdType){
		//First set the password
		DBObject query = new BasicDBObject(CommonConstants.FL_PASSWORD, password);
		//create map for the user id filed and using switch decide which field it will be
		Map<String, Object> userid = new HashMap<String, Object>(1);
		switch (userIdType) {
		case CommonConstants.ID_EMAIl:
			userid.put(CommonConstants.FL_EMAIl, userId);
			break;
		case CommonConstants.ID_MOBILE:
			userid.put(CommonConstants.FL_MOBILE, userId);
			break;
		}
		//finally put the user id 
		query.put(CommonConstants.FL_USER_ID, userid);
		return query;
	}

}
