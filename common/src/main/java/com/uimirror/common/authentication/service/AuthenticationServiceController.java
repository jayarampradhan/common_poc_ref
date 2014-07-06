/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common
 * @createdOn 06-Mar-20142:26:27 AM
 * @modifiedby Jayaram
 * @modifiedon 06-Mar-20142:26:27 AM
 * ***********************************************************************
 */

package com.uimirror.common.authentication.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.uimirror.common.CommonConstants;
import com.uimirror.common.authentication.AuthenticationBean;
import com.uimirror.common.util.ResponseHelper;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : will handle the service for the common
 * http request like login and releated operation.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 06-Mar-20142:26:27 AM
 * @modifiedby Jayaram
 * @modifiedon 06-Mar-20142:26:27 AM
 * ***********************************************************************
 */
@Component
public class AuthenticationServiceController {

    protected static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceController.class);
    
    @Autowired
    private AuthenticationService authenticationService;
    
    public AuthenticationServiceController() {
    }

    /**
     * <p>loginUser, helps to login a user by providing few required details.
     * such as user id and password etc.
     * <p>To Validate the user has the following steps
     * <ol>
     * <li>populate bean from map</li>
     * <li>validate the bean with rule engine</li>
     * <li>validate the email id and password or login token </li>
     * <li>Check if IP is modified from last login then update login activity with new token else check
     * if user session is beyond 30 mins then renew based on the renew flag for next 1 day or 15 days.</li>
     * <li>finally register the next login token and update login activity if specified in back ground</li>
     * </ol>
     * @param T, a {@code Map} which has the login form. 
     * @return {@link String} either with error message or success and required message for next step. 
     * 
     **/
	public Map<String, Object> doAuthenticate(Map<String, Object> request) {
	
    	LOG.info("[START]-Authentication process for the user is intiated.");
	
    	try {
    		//Check Request Object shouldn't be empty
    		Assert.notEmpty(request, "Request Can't be empty.");
    		AuthenticationBean authBean = new AuthenticationBean(request);
    		//Process Authentication
    		Map<String, Object> responseMap = authenticationService.doAuthenticate(authBean);
    		
    		Assert.notEmpty(responseMap, "Authentication process was unsuccessful");
    		
    		String responseCode = (String)responseMap.get(CommonConstants.RESPONSE_CODE);
    		responseMap.remove(responseCode);

    		return ResponseHelper.buildResponseMap(responseCode, responseMap);
	
    	} catch (Exception e) {
    		LOG.error("[END]- Login process stopped due to some internal error, error details: {}",e);
    		return ResponseHelper.buildResponseMap(CommonConstants.AUTHENTICATION_INTERNAL_ERR_RS_CD, CommonConstants.AUTHENTICATION_INTERNAL_ERR_RS_MSG);
    	}finally{
    		LOG.info("[END]-Authentication process for the user.");
    	}
	
    }

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	

}


