/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.user.dao
 * @createdOn 20-Mar-2014 5:09:11 PM
 * @modifiedby Jayaram
 * @modifiedon 20-Mar-2014 5:09:11 PM
 * ***********************************************************************
 */
package com.uimirror.api.common.user.dao;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.dao.SequenceDao;
import com.uimirror.common.dao.util.CommonDaoHelper;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : common repository for the user profile
 * get/update/delete.
 * implementation of {@link UserDao}
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 20-Mar-2014 5:09:11 PM
 * @modifiedby Jayaram
 * @modifiedon 20-Mar-2014 5:09:11 PM
 * ***********************************************************************
 */
public class UserDaoImpl implements UserDao{
	
	protected static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	private DBCollection userCollection;
	
	@Autowired
	private SequenceDao sequenceDao;
	
	
	/* (non-Javadoc)
	 * @see com.uimirror.api.common.user.dao.UserProfileDao#createUserProfile(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	public void createUserProfile(final Map<String, Object> userMap) throws MongoException {
		LOG.info("[START]- Creating user with data base in collection uim_user");
		//Finally Save into DB
		userCollection.insert(CommonDaoHelper.getDBObjectFromMap(userMap));
		LOG.info("[END]- Creating user with data base in collection uim_user");
	}

	/* (non-Javadoc)
	 * @see com.uimirror.api.common.user.dao.UserProfileDao#importProfile(javax.ws.rs.core.MultivaluedMap, java.lang.String)
	 */
	@Override
	public void importProfile(final Map<String, Object> importMap, final String profileId) throws MongoException {
		LOG.info("[START]- Updating imported user with data base in collection uim_user");
		//Build query map
		DBObject selectFields = new BasicDBObject(1);
		selectFields.put(CommonConstants._ID, profileId);
		//Fire Update query to remove political view
		userCollection.findAndModify(selectFields, CommonDaoHelper.getSetQuery(importMap));
		LOG.info("[END]- Updating imported user with data base in collection uim_user");
	}
	
	/* (non-Javadoc)
	 * @see com.uimirror.api.common.user.dao.UserProfileDao#getNextprofileId()
	 */
	@Override
	public String getNextprofileId() throws MongoException {
		return sequenceDao.getNextSequence(CommonConstants.USER_SEQUENCE);
	}
	
	/* (non-Javadoc)
	 * @see com.uimirror.api.common.user.dao.UserProfileDao#modifySingleUser(java.util.Map, java.util.Map)
	 */
	@Override
	public void modifySingleUser(Map<String, Object> query, Map<String, Object> updates) throws MongoException {
		LOG.info("[START]- Updateing user document.");
		//Build the select criteria for updating single document.
		DBObject selectFields = CommonDaoHelper.getDBObjectFromMap(query);
		
		//Builds Update fields
		DBObject updateFields = CommonDaoHelper.getDBObjectFromMap(updates);
		
		userCollection.update(selectFields, updateFields);
		LOG.info("[END]- Updateing user document.");
	}
	
	public void setUserCollection(DBCollection userCollection) {
		this.userCollection = userCollection;
	}

	public void setSequenceDao(SequenceDao sequenceDao) {
		this.sequenceDao = sequenceDao;
	}

}
