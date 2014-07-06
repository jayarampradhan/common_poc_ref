/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.user.dao
 * @createdOn 20-Mar-2014 5:08:20 PM
 * @modifiedby Jayaram
 * @modifiedon 20-Mar-2014 5:08:20 PM
 * ***********************************************************************
 */
package com.uimirror.api.common.user.dao;

import java.util.Map;

import com.mongodb.MongoException;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : common repository for the user profile
 * get/update/delete.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 20-Mar-2014 5:08:20 PM
 * @modifiedby Jayaram
 * @modifiedon 20-Mar-2014 5:08:20 PM
 * ***********************************************************************
 */
public interface UserDao {
	
	/**
	 * <p>This will create user profile after verification process completed.
	 * <p>This will create user in user collection.
	 * @param map containing user needs to be created
	 * @throws MongoException
	 */
	public void createUserProfile(final Map<String, Object> userMap) throws MongoException;
	
	/**
	 * 
	 * <p>This will update user details imported from different source like facebook etc.
	 * @param importMap containing user details, needs to be updated.
	 * @throws MongoException
	 */
	public void importProfile(final Map<String, Object> importMap, final String profileId) throws MongoException;
	
	/**
	 * <p>This will get the next profile id from the sequence.
	 * @return
	 * @throws MongoException
	 */
	public String getNextprofileId() throws MongoException;
	
	/**
	 * <p>This will modify the user basic document with the list of update statements where query matches the document
	 * @param query specifies the criteria to find the document.
	 * @param updates specifies the fields to be updated
	 * @throws MongoException
	 */
	public void modifySingleUser(final Map<String, Object> query, final Map<String, Object> updates) throws MongoException;
	
	
}
