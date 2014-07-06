/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.filter.dao
 * @createdOn 04-Mar-201412:57:13 AM
 * @modifiedby Jayaram
 * @modifiedon 04-Mar-201412:57:13 AM
 * ***********************************************************************
 */

package com.uimirror.api.filter.dao;

import java.sql.SQLException;

import com.uimirror.api.User;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : DAo to validate and save the user details
 * who is making the API call.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 04-Mar-201412:57:13 AM
 * @modifiedby Jayaram
 * @modifiedon 04-Mar-201412:57:13 AM
 * ***********************************************************************
 */

public interface UserAuthenticationDao {
    
    /**
     * <p>This will retrieve the user details by the API key.
     * @param args apiKey
     * @return {@link User} if apiKey is valid else null.
     * @throws SQLException 
     */
    public User getUerByAPIKey(final Object[] args) throws SQLException;

}


