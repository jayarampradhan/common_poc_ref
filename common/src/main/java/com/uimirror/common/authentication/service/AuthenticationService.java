/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.login.service
 * @createdOn 17-Mar-2014 9:26:05 PM
 * @modifiedby Jayaram
 * @modifiedon 17-Mar-2014 9:26:05 PM
 * ***********************************************************************
 */
package com.uimirror.common.authentication.service;

import java.util.Map;

import com.uimirror.common.authentication.AuthenticationBean;
import com.uimirror.common.exception.CommonSystemException;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : contains process of login.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 17-Mar-2014 9:26:05 PM
 * @modifiedby Jayaram
 * @modifiedon 17-Mar-2014 9:26:05 PM
 * ***********************************************************************
 */
public interface AuthenticationService {
	
	/**
     * 
     * <p>loginUser: will login the user in memory and return back with
     * 				next login token if necessary.
     * <p>Steps involved on this are
     * <ol>
     * <li>validate the bean with rule engine</li>
     * <li>Generate the next login token if necessary</li>
     * <li>finally persist into DB</li>
     * <li>return with next login token and validity.</li>
     * </ol>
     * @param loginFormBean {@code AuthenticationBean}
     * @return  
	 * @throws CommonSystemException 
     *
     */
    public Map<String, Object> doAuthenticate(AuthenticationBean authBean) throws CommonSystemException;

}
