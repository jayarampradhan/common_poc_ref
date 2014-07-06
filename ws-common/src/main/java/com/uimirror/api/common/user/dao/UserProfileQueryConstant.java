/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.register.dao
 * @createdOn 09-Mar-20148:44:45 pm
 * @modifiedby Jayaram
 * @modifiedon 09-Mar-20148:44:45 pm
 * ***********************************************************************
 */

package com.uimirror.api.common.user.dao;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : 
 * holds all the query constants used in 
 * registeration process.
 * <p>Format is 
 * <ol>
 * <li>_</li>
 * <li>SL-for select , IN-for insert, UP- for update, DL-for delete</li>
 * <li>_</li>
 * <li>Meaningful name of the query separated by _</li>
 * <li>FL_ Field name </li>
 * </ol>
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 09-Mar-20148:44:45 pm
 * @modifiedby Jayaram
 * @modifiedon 09-Mar-20148:44:45 pm
 * ***********************************************************************
 */

public interface UserProfileQueryConstant {
    
    /**
     * <p>Query to get the user by EMAIL ID.
     */
    public static final String _SL_GET_USER_BY_EMAIL_ID = "";
    
    /**
     * <p>Query to save the user in temp table
     */
    public static final String _IN_USR_TMP = "";
    
    public static String FL_EMAIL = "email";
    public static String FL_CREATED_ON = "created_on";

}


