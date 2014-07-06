/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.register.dao
 * @createdOn 16-Mar-2014 12:29:41 AM
 * @modifiedby Jayaram
 * @modifiedon 16-Mar-2014 12:29:41 AM
 * ***********************************************************************
 */
package com.uimirror.api.common.user.dao;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.uimirror.common.util.DateTimeFactory;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : will hold all the query required for 
 * User profile process.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 16-Mar-2014 12:29:41 AM
 * @modifiedby Jayaram
 * @modifiedon 16-Mar-2014 12:29:41 AM
 * ***********************************************************************
 */
public class UserProfileQueries {
	
	/**
	 * <p>This will build where clause query for finding user in temp
	 * collection by email and created date greater than 24 hrs from now in UTC format
	 * @param email
	 * @return
	 */
	public static DBObject getQueryForEmailExistanceInTemp(final String email, final int hours){
		return QueryBuilder.start(UserProfileQueryConstant.FL_EMAIL).is(email)
				.and(UserProfileQueryConstant.FL_CREATED_ON).greaterThanEquals(DateTimeFactory.getStringFromDate(DateTimeFactory.getCurrentDateTime().minusHours(hours)))
				.get();
	}
	

}
