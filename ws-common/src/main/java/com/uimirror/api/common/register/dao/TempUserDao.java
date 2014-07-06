/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.register.dao
 * @createdOn 09-Mar-20148:35:37 pm
 * @modifiedby Jayaram
 * @modifiedon 09-Mar-20148:35:37 pm
 * ***********************************************************************
 */

package com.uimirror.api.common.register.dao;

import java.util.Map;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;
import com.mongodb.MongoException;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : contains all the JDBC operation for the 
 * Registration process, like persist, query DB etc.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 09-Mar-20148:35:37 pm
 * @modifiedby Jayaram
 * @modifiedon 09-Mar-20148:35:37 pm
 * ***********************************************************************
 */

public interface TempUserDao {
    
	/**
	 * <p>This will retrieve the existing user by email in Temp user collection. 
	 * @param email
	 * @return
	 * @throws MongoException
	 */
	public Map<String, Object> getUserByEmail(final String email) throws MongoException;
	
	/**
	 * <p>This will retrieve the existing user by profileId in Temp user collection. 
	 * @param profileId
	 * @return
	 * @throws MongoException
	 */
	public Map<String, Object> getUser(final String profileId) throws MongoException;
	
	/**
	 * <p>This will retrieve the existing user by profileId in Temp user collection with project fields. 
	 * @param profileId
	 * @return
	 * @throws MongoException
	 */
	public Map<String, Object> getUserSpecificFields(final Map<String, Object> fields, final String profileId) throws MongoException;
	
	/**
	 * <p>This will delete temp user from temp collection.
	 * @param tempId
	 * @return
	 * @throws MongoException
	 */
	public void deleteUser(final String tempId) throws MongoException;
	
	/**
     * <p>Save user in temp table Data Base.
     * @param registerMap {@link Map}
     */
    public void createUser(final Map<String, Object> registerMap) throws MongoException;
    
    /**
     * <p>This will create new profile ID.
     * @return
     * @throws MongoException
     */
    public String getNextProfileId() throws MongoException;
    
    /**
     * <p>This will update user profile details such as token etc by profile id.
     * @param updates
     * @param profileId
     * @throws MongoException
     */
    public void updateUserByProfileId(final Map<String, Object> updates, final String profileId) throws MongoException;
	
	
    /**
     * <p>Check if any user has already exists with same email id.
     * @param args {@code Object []} of an email id.
     * @return profile id of the existing user
     *
     */
    @Cacheable(cacheName="com.uimirror.api.USER_EXISTANCE_CACHE", 
	    keyGenerator = @KeyGenerator (name = "StringCacheKeyGenerator",
	    properties = {
		    @Property( name="useReflection", value="false" ),
		    @Property( name="checkforCycles", value="false" ),
		    @Property( name="includeMethod", value="false" )
	    }
	   )
	)
    public boolean checkUserExistanceByEmail(final String email, final int hours) throws MongoException;

}


