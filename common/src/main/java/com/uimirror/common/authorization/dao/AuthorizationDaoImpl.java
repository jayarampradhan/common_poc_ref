/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.authorization.dao
 * @createdOn 24-Mar-2014 11:51:31 PM
 * @modifiedby Jayaram
 * @modifiedon 24-Mar-2014 11:51:31 PM
 * ***********************************************************************
 */
package com.uimirror.common.authorization.dao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.dao.util.CommonDaoHelper;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : $.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 24-Mar-2014 11:51:31 PM
 * @modifiedby Jayaram
 * @modifiedon 24-Mar-2014 11:51:31 PM
 * ***********************************************************************
 */
public class AuthorizationDaoImpl implements AuthorizationDao {
	
	protected static final Logger LOG = LoggerFactory.getLogger(AuthorizationDaoImpl.class);
	
	@Autowired
	private DBCollection authorizationSessionCollection;

	@Override
	public void createAuthorization(final Map<String, Object> authorizationMap) throws MongoException {
		LOG.info("[START]- Creating Authorization");
		authorizationSessionCollection.insert(CommonDaoHelper.getDBObjectFromMap(authorizationMap));
		LOG.info("[END]- Creating Authorization");
	}

	@Override
	public void revokeAuthorization(final Map<String, Object> authorizationMap, final String profileId) throws MongoException {
		LOG.info("[START]- Revoking Authorization from user");
		//Build query map
		DBObject selectFields = new BasicDBObject();
		selectFields.put(CommonConstants._ID, profileId);
		//Deleting record
		authorizationSessionCollection.findAndModify(selectFields, CommonDaoHelper.getPullQuery(authorizationMap));
		LOG.info("[END]- Revoking Authorization from user");
	}

	@Override
	public void addAuthorization(final List<Map<String, Object>> authorizationMap, final String profileId) throws MongoException {
		LOG.info("[START]- Adding Authorization to user");
		//Build query map
		DBObject selectFields = new BasicDBObject();
		selectFields.put(CommonConstants._ID, profileId);
		//Add record
		authorizationSessionCollection.findAndModify(selectFields, CommonDaoHelper.getAddToSetWithEachQuery(authorizationMap, CommonConstants.FL_OBJECT));
		LOG.info("[END]- Adding Authorization to user");
	}

	@Override
	public Map<String, Object> getAccessMapByProfileId(final String profileId) throws MongoException {
		LOG.info("[START]- Getting Authorization details for user");
		//Build query map
		DBObject selectFields = new BasicDBObject();
		selectFields.put(CommonConstants._ID, profileId);
		DBObject rs = authorizationSessionCollection.findOne(selectFields);
		LOG.info("[END]- Adding Authorization details for user");
		return rs == null ? null : rs.toMap();
	}

	@Override
	public Map<String, Object> getSpecificAccessMapByProfileId(final Map<String, Object> queryMap, final Map<String, Object> fileds, final String profileId) throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updateAccountType(Map<String, Object> accountTypeMap,
			String profileId) throws MongoException {
		// TODO Auto-generated method stub
		
	}

	public void setAuthorizationSessionCollection(DBCollection authorizationSessionCollection) {
		this.authorizationSessionCollection = authorizationSessionCollection;
	}

}
