/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.authorization.dao
 * @createdOn 24-Mar-2014 11:51:08 PM
 * @modifiedby Jayaram
 * @modifiedon 24-Mar-2014 11:51:08 PM
 * ***********************************************************************
 */
package com.uimirror.common.authorization.dao;

import java.util.List;
import java.util.Map;

import com.mongodb.MongoException;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : contains all the role related 
 * information uim_authorization.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 24-Mar-2014 11:51:08 PM
 * @modifiedby Jayaram
 * @modifiedon 24-Mar-2014 11:51:08 PM
 * ***********************************************************************
 */
public interface AuthorizationDao {
	
	/**
	 * <p>This will create authorization for the new user
	 * 
	 * @param authorizationMap
	 * @throws MongoException
	 */
	public void createAuthorization(final Map<String, Object> authorizationMap) throws MongoException;
	
	/**
	 * <p>This will delete/ revoke authorization for the user 
	 * @param authorizationMap
	 * @param profileId
	 * @throws MongoException
	 */
	public void revokeAuthorization(final Map<String, Object> authorizationMap, final String profileId) throws MongoException;
	
	/**
	 * <p>This will add authorization details for the user.
	 * @param authorizationMap
	 * @param profileId
	 * @throws MongoException
	 */
	public void addAuthorization(final List<Map<String, Object>> authorizationMap, final String profileId) throws MongoException;
	
	/**
	 * <p>This will retrieve all the access user has.
	 * @param profileId
	 * @return
	 * @throws MongoException
	 */
	public Map<String, Object> getAccessMapByProfileId(final String profileId) throws MongoException;
	
	/**
	 * <p>This will retrieve specific fields from the access list. 
	 * @param queryMap
	 * @param fileds
	 * @param profileId
	 * @return
	 * @throws MongoException
	 */
	public Map<String, Object> getSpecificAccessMapByProfileId(final Map<String, Object> queryMap, final Map<String, Object> fileds, final String profileId) throws MongoException;
	
	/**
	 * <p>This will update account type map
	 * @param accountTypeMap
	 * @param profileId
	 * @throws MongoException
	 */
	public void updateAccountType(final Map<String, Object> accountTypeMap, final String profileId) throws MongoException;

}
