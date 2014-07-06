/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.login.service
 * @createdOn 17-Mar-2014 9:31:52 PM
 * @modifiedby Jayaram
 * @modifiedon 17-Mar-2014 9:31:52 PM
 * ***********************************************************************
 */
package com.uimirror.common.authentication.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.mongodb.MongoException;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.authentication.AuthenticationBean;
import com.uimirror.common.authentication.dao.AuthenticationDao;
import com.uimirror.common.exception.CommonErrorConstants;
import com.uimirror.common.exception.CommonSystemException;
import com.uimirror.common.meta.dao.DeviceDao;
import com.uimirror.common.thread.CachedPoolService;
import com.uimirror.common.util.ResponseHelper;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : implementation of {@code LoginService}.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 17-Mar-2014 9:31:52 PM
 * @modifiedby Jayaram
 * @modifiedon 17-Mar-2014 9:31:52 PM
 * ***********************************************************************
 */
public class AuthenticationServiceImpl implements AuthenticationService {
	
	protected static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
	
	@Autowired 
    private Validator validator;
	
	@Autowired
	private AuthenticationDao authenticationDao;
	
	@Autowired
	private DeviceDao deviceDao;

	/* (non-Javadoc)
	 * @see com.uimirror.api.common.login.service.LoginService#loginUser(com.uimirror.api.common.bean.LoginFormBean)
	 */
	@Override
	public Map<String, Object> doAuthenticate(AuthenticationBean authBean) throws CommonSystemException{
		
		LOG.info("[START]-Authentication validation and perssit.");
    	
		//Step 1- Check for the not null of the request object.
    	Assert.notNull(authBean, "Authentication can't be performed as no data available.");
    	
    	Set<ConstraintViolation<AuthenticationBean>> violations = validator.validate(authBean);
    	
    	Assert.notNull(violations);
    	
    	if(violations.size() > CommonConstants.NUMBER_0){
    		//Return field validation failed
    		return ResponseHelper.buildValidationFaildResponse(violations);
    	}
    	
    	try {

    		//Step 2- Get State of the user
    		Map<String, Object> states = authBean.getStates();
    		Assert.notEmpty(states, "Validation process was not successful.");
    		
    		if(CommonConstants.SAVE_SESSION.equals(states.get(CommonConstants.SAVE_SESSION))){
    			//Step 3- Save session in Background
    			//Invoke BackGround Job to persist
    			logSession(states);
    		}

    		//Step 4- Return to the caller
    		return buildCookieDetails(states);

    	} catch (MongoException me) {
    		LOG.error("[EXCEPTION] Authentication validation, and exception stack is:{}",me.getMessage());
    		throw new CommonSystemException(CommonErrorConstants.COMMON_DB_ERROR , CommonErrorConstants.COMMON_DB_ERROR_CD);
    	}finally{
    		LOG.info("[END]-Authentication validation and perssit part end");
    	}
	}
	
	/**
	 * <p>This will save the session details in Background.
	 * @param res
	 */
	private void logSession(Map<String, Object> res){
		LOG.info("[START]- Creating Background Job for Saving session.");
		CachedPoolService cachedPoolService = CachedPoolService.getInstance(1);
		
		List<Callable<Object>> jobs = new ArrayList<Callable<Object>>(1);
		
		Map<String, Object> session = new HashMap<String, Object>(12);
		
		session.put(CommonConstants._ID, res.get(CommonConstants._ID));
		session.put(CommonConstants.PRF_ID, res.get(CommonConstants.PRF_ID));
		session.put(CommonConstants.PRV_AUTH_ID, res.get(CommonConstants.PRV_AUTH_ID));
		session.put(CommonConstants.AUTH_TOKEN, res.get(CommonConstants.AUTH_TOKEN));
		session.put(CommonConstants.PRV_AUTH_TOKEN, res.get(CommonConstants.PRV_AUTH_TOKEN));
		session.put(CommonConstants.AUTH_TOKEN_EXPIRES_ON, res.get(CommonConstants.AUTH_TOKEN_EXPIRES_ON));
		session.put(CommonConstants.FL_DEVICE, res.get(CommonConstants.FL_DEVICE));
		session.put(CommonConstants.ID_DEVICE, res.get(CommonConstants.ID_DEVICE));
		session.put(CommonConstants.IP, res.get(CommonConstants.IP));
		
		LogSessionCallable logSession = new LogSessionCallable(authenticationDao, deviceDao, session);
		jobs.add(logSession);
		cachedPoolService.submitTask(jobs);
		LOG.info("[END]- Creating Background Job for Saving session.");
	}
    
    /**
	 * <p>This will build the response code and block for the authentication block
	 * @param cookieRes
	 * @return
	 */
	private Map<String, Object> buildCookieDetails(Map<String, Object> cookieRes){
		
		Map<String, Object> response = new HashMap<String, Object>(7);
		
		response.put(CommonConstants.RESPONSE_CODE, CommonConstants.AUTHENTICATION_SUCESS_RS_CD);
		response.put(CommonConstants.AUTH_ID, cookieRes.get(CommonConstants._ID));
		response.put(CommonConstants.PRV_AUTH_ID, cookieRes.get(CommonConstants.PRV_AUTH_ID));
		response.put(CommonConstants.AUTH_TOKEN, cookieRes.get(CommonConstants.AUTH_TOKEN));
		response.put(CommonConstants.PRV_AUTH_TOKEN, cookieRes.get(CommonConstants.PRV_AUTH_TOKEN));
		response.put(CommonConstants.AUTH_TOKEN_INTERVAL, cookieRes.get(CommonConstants.AUTH_TOKEN_INTERVAL));
		response.put(CommonConstants.PRF_ID, cookieRes.get(CommonConstants.PRF_ID));
		
		return response;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public void setAuthenticationDao(AuthenticationDao authenticationDao) {
		this.authenticationDao = authenticationDao;
	}

}
