/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.authentication.annotation.impl
 * @createdOn 19-Mar-2014 1:10:29 PM
 * @modifiedby Jayaram
 * @modifiedon 19-Mar-2014 1:10:29 PM
 * ***********************************************************************
 */
package com.uimirror.common.authentication.annotation.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.uimirror.common.CommonConstants;
import com.uimirror.common.authentication.annotation.AuthenticationRequired;
import com.uimirror.common.authentication.service.AuthenticationServiceController;
import com.uimirror.common.util.CollectionUtils;
import com.uimirror.common.util.GsonUtility;
import com.uimirror.common.util.ResponseHelper;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : intercepts all method that annotate with login 
 * required.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 19-Mar-2014 1:10:29 PM
 * @modifiedby Jayaram
 * @modifiedon 19-Mar-2014 1:10:29 PM
 * ***********************************************************************
 */
@Aspect
public class AuthenticationRequiredAspect {
	
	protected static final Logger LOG = LoggerFactory.getLogger(AuthenticationRequiredAspect.class);
	
	@Autowired
	private AuthenticationServiceController authenticationController;
	
	/**
	 * <p>Check the argument if caller has specified the login details
	 * then processed else stop the process.
	 * @param joinPoint
	 * @param authenticationRequired
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* *(..)) && @annotation(authenticationRequired)")
	public Object doCookieLoginCheck(final ProceedingJoinPoint joinPoint, final AuthenticationRequired authenticationRequired) throws Throwable{
		//Get the arguments from the caller
		Object[] args = joinPoint.getArgs();
		if(args == null || args.length == 0){
			//Stop the process as user can't be validated.
			return ResponseHelper.buildResponseMap(CommonConstants.FORBIDDEN_RS_CD, CommonConstants.UN_AUTHORIZED_RS_MSG);
		}

        try {
        	@SuppressWarnings("unchecked")
    		MultivaluedMap<String, String> rqData = (MultivaluedMap<String, String>) args[0];
    		
    		//Check authentication map shouldn't be empty
    		Assert.notEmpty(rqData, "Request Parameter shouldn't be empty.");

    		Map<String, Object> request = new HashMap<String, Object>(1);
        	request.putAll(CollectionUtils.getInstance().getMapFromMultiMap(rqData));
        	request.put(CommonConstants.AUTH_TYPE, CommonConstants.COOKIE);
    		
    		//Do authentication
    		Map<String, Object> res = authenticationController.doAuthenticate(request);
    		Assert.notEmpty(res, "Authentication was not sucessful");
    		//Check response code received
    		String responseCode = (String)res.get(CommonConstants.RESPONSE_CODE);
    		
    		//Login response either modifed or unmodified
    		if(CommonConstants.UN_MODIFIED_RESPONSE_CD.equals(responseCode) || CommonConstants.SUCESS_RESPONSE_CD.equals(responseCode)){
    			//Remove Block and replace with new data.
        		//rqData.add(CommonConstants.AUTHENTICATION_BLOCK, res.get(CommonConstants.MSG));
    			//Update the authentication required block
    			@SuppressWarnings("unchecked")
				Map<String, Object> auth_res = (Map<String, Object>) res.get(CommonConstants.MSG);
    			rqData.remove(CommonConstants.AUTH_ID);
    			rqData.add(CommonConstants.AUTH_ID, (String)auth_res.get(CommonConstants.AUTH_ID));
    			rqData.remove(CommonConstants.PRV_AUTH_ID);
    			rqData.add(CommonConstants.PRV_AUTH_ID, (String)auth_res.get(CommonConstants.PRV_AUTH_ID));
    			rqData.remove(CommonConstants.AUTH_TOKEN);
    			rqData.add(CommonConstants.AUTH_TOKEN, (String)auth_res.get(CommonConstants.AUTH_TOKEN));
    			rqData.remove(CommonConstants.PRV_AUTH_TOKEN);
    			rqData.add(CommonConstants.PRV_AUTH_TOKEN, (String)auth_res.get(CommonConstants.PRV_AUTH_TOKEN));
    			rqData.remove(CommonConstants.AUTH_TOKEN_INTERVAL);
    			rqData.add(CommonConstants.AUTH_TOKEN_INTERVAL, (String)auth_res.get(CommonConstants.AUTH_TOKEN_INTERVAL));
        		//Set the first argument to cookie after modifying and process with call.
        		args[0] = rqData;
                return joinPoint.proceed();
    		}else{
    			//Other than success is fail, so return user with un authorized access
    			return GsonUtility.convertToGsonString(ResponseHelper.buildResponseMap(CommonConstants.FORBIDDEN_RS_CD, CommonConstants.UN_AUTHORIZED_RS_MSG));
    		}
    		
        } catch(Exception e){
        	LOG.error("[EXCEPTION]- occured during authentication process , {}",e);
        	return GsonUtility.convertToGsonString(ResponseHelper.buildResponseMap(CommonConstants.FORBIDDEN_RS_CD, CommonConstants.UN_AUTHORIZED_RS_MSG));
        }finally {
        	LOG.info("[END]-Authentication Completed.");
        }
	}

	public void setLoginServiceController(AuthenticationServiceController authenticationServiceController) {
		this.authenticationController = authenticationServiceController;
	}

}
