/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.user.dao
 * @createdOn 28-Mar-2014 1:17:40 AM
 * @modifiedby Jayaram
 * @modifiedon 28-Mar-2014 1:17:40 AM
 * ***********************************************************************
 */
package com.uimirror.api.common.user.dao;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DBCollection;
import com.mongodb.MongoException;
import com.uimirror.common.dao.util.CommonDaoHelper;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : implementation of {@link com.uimirror.api.common.user.dao.UserDetailsDao}.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 28-Mar-2014 1:17:40 AM
 * @modifiedby Jayaram
 * @modifiedon 28-Mar-2014 1:17:40 AM
 * ***********************************************************************
 */
public class UserDetailsDaoImpl implements UserDetailsDao {
	
	protected static final Logger LOG = LoggerFactory.getLogger(UserDetailsDaoImpl.class);
	
	@Autowired
	private DBCollection userDetailsCollection;

	/* (non-Javadoc)
	 * @see com.uimirror.api.common.user.dao.UserDetailsDao#createUserDetails(java.util.Map)
	 */
	@Override
	public void createUserDetails(Map<String, Object> userDetailsMap) throws MongoException {
		LOG.info("[START]- Creating user details");
		userDetailsCollection.save(CommonDaoHelper.getDBObjectFromMap(userDetailsMap));
		LOG.info("[END]- Creating user details");
	}

}
