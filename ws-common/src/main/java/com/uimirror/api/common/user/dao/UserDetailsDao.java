/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.user.dao
 * @createdOn 24-Mar-2014 1:32:33 PM
 * @modifiedby Jayaram
 * @modifiedon 24-Mar-2014 1:32:33 PM
 * ***********************************************************************
 */
package com.uimirror.api.common.user.dao;

import java.util.Map;

import com.mongodb.MongoException;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : holds the dao related activities for the 
 * user details collection.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 24-Mar-2014 1:32:33 PM
 * @modifiedby Jayaram
 * @modifiedon 24-Mar-2014 1:32:33 PM
 * ***********************************************************************
 */
public interface UserDetailsDao {

	/**
	 * <p>This will create user summary in the user summary collection.
	 * @param userDetailsMap
	 * @throws MongoException
	 */
	public void createUserDetails(Map<String, Object> userDetailsMap) throws MongoException;
	

}
