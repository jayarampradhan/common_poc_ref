package com.uimirror.common.authentication.dao;

import java.util.Map;

import com.mongodb.MongoException;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Dao Service for the login and logout 
 * Related activities.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 17-Mar-2014 10:16:05 PM
 * @modifiedby Jayaram
 * @modifiedon 17-Mar-2014 10:16:05 PM
 * ***********************************************************************
 */
public interface AuthenticationDao {
	
	/**
	 * <p>This will add user to the authentication collection.
	 * @param userAuth
	 */
	public void addUser(Map<String, Object> userAuth) throws MongoException;
	
	/**
	 * <p>This will change the current Password.
	 * @param pwdMap
	 * @param profileId
	 */
	public void modifyCurrentPassword(Map<String, Object> pwdMap, String profileId) throws MongoException;
	
	/**
	 * <p>This will add recent password.
	 * @param pwd
	 * @param profileId
	 */
	public void addRecentPassword(String pwd, String profileId) throws MongoException;
	
	/**
	 * <p>This will add a new user id.
	 * @param userId
	 * @param profileId
	 */
	public void addUserId(String userId, String profileId) throws MongoException;
	
	/**
	 * <p>This will delete user id.
	 * @param userId
	 * @param profileId
	 * @param userIdType
	 */
	public void deleteUserId(final String userId, final String profileId, final String userIdType) throws MongoException;
	
	/**
	 * <p>This will update credential active status. 
	 * @param statusMap
	 * @param profileId
	 */
	public void updateUserActiveStatus(final Map<String, Object> statusMap, final String profileId) throws MongoException;
	
	/**
	 * <p>This will retrieve the user by user id and password.
	 * <p><p>type specifies which fields needs to check against.
	 * i.e email or mobile.
	 * @param userId
	 * @param password
	 * @param userIdType
	 * @return
	 */
	public Map<String, Object> getUserByCredentials(final String userId, final String password, final String userIdType) throws MongoException;
	
	
	/**
	 * <p>This will retrieve the user by profile id.
	 * @param profileId
	 * @return
	 */
	public Map<String, Object> getUserCredentialsByProfileId(final String profileId) throws MongoException;
	
	/**
	 * <>this will create a new session for the user.
	 * @param session
	 * @throws MongoException
	 */
	public void createLoginSession(final Map<String, Object> session) throws MongoException;
	
	/**
	 * <p>This will get the session for the login id specified.
	 * @param loginId
	 * @return
	 * @throws MongoException
	 */
	public Map<String, Object> getSessionByAuthId(final String loginId) throws MongoException;

	/**
	 * <p>updates the login session by login id.
	 * 
	 * @param updateSession
	 * @param loginId
	 * @throws MongoException
	 */
	public void updateLoginSession(final Map<String, Object> updateSession, final String loginId) throws MongoException;
	
	/**
	 * <p>This will get the next sequence id for the session.
	 * @return
	 * @throws MongoException
	 */
	public String getNextSessionId() throws MongoException;

}
