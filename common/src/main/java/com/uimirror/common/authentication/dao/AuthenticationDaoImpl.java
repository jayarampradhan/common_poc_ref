/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.login.dao
 * @createdOn 17-Mar-2014 10:17:39 PM
 * @modifiedby Jayaram
 * @modifiedon 17-Mar-2014 10:17:39 PM
 * ***********************************************************************
 */
package com.uimirror.common.authentication.dao;

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
 * Details of class goes like : implementation of {@link AuthenticationDao}.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 17-Mar-2014 10:17:39 PM
 * @modifiedby Jayaram
 * @modifiedon 17-Mar-2014 10:17:39 PM
 * ***********************************************************************
 */
public class AuthenticationDaoImpl implements AuthenticationDao {
	
	protected static final Logger LOG = LoggerFactory.getLogger(AuthenticationDaoImpl.class);
	
	@Autowired
	private DBCollection authenticationCollection;
	
	@Autowired
	private DBCollection loginSessionCollection;
	
	@Autowired
	private SequenceDao sequenceDao;
	
	
	/* (non-Javadoc)
	 * @see com.uimirror.common.login.dao.AuthenticationDao#addUser(java.util.Map)
	 */
	@Override
	public void addUser(Map<String, Object> userAuth)  throws MongoException{
		LOG.info("[START]- Adding new User to authentication");
		authenticationCollection.insert(CommonDaoHelper.getDBObjectFromMap(userAuth));
		LOG.info("[END]- Adding new User to authentication");
	}

	/* (non-Javadoc)
	 * @see com.uimirror.common.login.dao.AuthenticationDao#modifyCurrentPassword(java.util.Map, java.lang.String)
	 */
	@Override
	public void modifyCurrentPassword(Map<String, Object> pwdMap, String profileId) throws MongoException{
		LOG.info("[START]- Changing Current password.");
		//Build query map
		DBObject selectFields = new BasicDBObject();
		selectFields.put(CommonConstants._ID, profileId);
		authenticationCollection.findAndModify(selectFields, CommonDaoHelper.getSetQuery(pwdMap));
		LOG.info("[END]- Changing current password.");
	}

	@Override
	public void addRecentPassword(String pwd, String profileId) throws MongoException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUserId(String userId, String profileId) throws MongoException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserId(String userId, String profileId, String userIdType) throws MongoException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserActiveStatus(final Map<String, Object> statusMap, final String profileId) throws MongoException{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.uimirror.common.login.dao.AuthenticationDao#getUserByCredentials(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getUserByCredentials(final String userId, final String password, final String userIdType) throws MongoException{
		LOG.info("[START]- Getting user by credentials");
		DBObject rs = authenticationCollection.findOne(AuthenticationDaoHelper.getQueryForCredentialsByType(userId, password, userIdType));
		LOG.info("[END]- Getting user by credentials");
		return rs == null ? null : rs.toMap();
	}

	@Override
	public Map<String, Object> getUserCredentialsByProfileId(String profileId) throws MongoException{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.uimirror.common.login.dao.AuthenticationDao#createLoginSession(java.util.Map)
	 */
	@Override
	public void createLoginSession(Map<String, Object> session) throws MongoException {
		LOG.info("[START]- Creating a new Login Session");
		loginSessionCollection.insert(CommonDaoHelper.getDBObjectFromMap(session));
		LOG.info("[END]- Creating a new Login Session");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getSessionByAuthId(String loginId) throws MongoException {
		LOG.info("[START]- Getting Login Session By Login Id");
		DBObject object = loginSessionCollection.findOne(loginId);
		LOG.info("[END]- Getting Login Session By Login Id");
		return object == null ? null : object.toMap();
	}

	/* (non-Javadoc)
	 * @see com.uimirror.common.login.dao.AuthenticationDao#updateLoginSession(java.util.Map, java.lang.String)
	 */
	@Override
	public void updateLoginSession(Map<String, Object> updateSession, String loginId) throws MongoException {
		LOG.info("[START]- Updating Login Session");
		DBObject selectFields = new BasicDBObject();
		selectFields.put(CommonConstants._ID, loginId);
		loginSessionCollection.findAndModify(selectFields, CommonDaoHelper.getSetQuery(updateSession));
		LOG.info("[END]- Updating Login Session");
	}

	/* (non-Javadoc)
	 * @see com.uimirror.common.authentication.dao.AuthenticationDao#createNextSessionId()
	 */
	@Override
	public String getNextSessionId() throws MongoException {
		return sequenceDao.getNextSequence(CommonConstants.SESSION_SEQUENCE);
	}

	public void setAuthenticationCollection(DBCollection authenticationCollection) {
		this.authenticationCollection = authenticationCollection;
	}

	public void setLoginSessionCollection(DBCollection loginSessionCollection) {
		this.loginSessionCollection = loginSessionCollection;
	}

	public void setSequenceDao(SequenceDao sequenceDao) {
		this.sequenceDao = sequenceDao;
	}

}
