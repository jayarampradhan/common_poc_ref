/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.authentication.service
 * @createdOn 26-Mar-2014 1:56:36 AM
 * @modifiedby Jayaram
 * @modifiedon 26-Mar-2014 1:56:36 AM
 * ***********************************************************************
 */
package com.uimirror.common.authentication.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.uimirror.common.CommonConstants;
import com.uimirror.common.authentication.dao.AuthenticationDao;
import com.uimirror.common.encryption.util.EncryptionUtil;
import com.uimirror.common.util.DateTimeFactory;
import com.uimirror.common.util.StringUtility;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Main service level validations for authentication 
 * will be done here.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 26-Mar-2014 1:56:36 AM
 * @modifiedby Jayaram
 * @modifiedon 26-Mar-2014 1:56:36 AM
 * ***********************************************************************
 */
public class AuthenticationCheckPoint {
	
	protected static final Logger LOG = LoggerFactory.getLogger(AuthenticationCheckPoint.class);
	
	@Autowired
	private AuthenticationDao authenticationDao;
	
	/**
	 * <p>This will get the user by credentials then check for validity 
	 * if valid return the response map for next operation else null.
	 * @param userId
	 * @param password
	 * @param userIdType
	 * @param keepMeLogin
	 * @param device
	 * @param ip
	 * @return
	 */
	public Map<String, Object> checkCredentials(final String userId, final String password, final String userIdType, final String keepMeLogin, final String device, final String ip){
		
		LOG.info("[START]- Checking creadetilas");
		//Step 1- Check all the input parameters shouldn't be empty
		if(StringUtility.checkEmptyString(userId, password, userIdType)){
			return null;
		}
		
		try{
			//Step 2- Get user by credentials
			Map<String, Object> user = authenticationDao.getUserByCredentials(userId, password, userIdType);
			Assert.notEmpty(user, "No User found with the provided credentials.");
			
			//Step 3- Get next session sequence
			String sessionId = authenticationDao.getNextSessionId();
			
			//Step 4- Generate cookie Token
			String token = EncryptionUtil.getRandomId(CommonConstants.COOKIE_TOKEN_SIZE);
			
			//Step 5- decide cookie interval default is for 60*24 which is one day
			int interval = CommonConstants.COOKIE_TOKEN_INTERVAL;
			
			if(CommonConstants.YES.equalsIgnoreCase(keepMeLogin)){
				interval = CommonConstants.COOKIE_TOKEN_INTERVAL_LONG;
			}
		
			//Step 6- Finally Return with the new session details.
			return buildAuthSession(user.get(CommonConstants._ID), sessionId, sessionId, token, token, interval, device, ip, CommonConstants.YES);
		
		}catch(Exception e){
			
			LOG.error("[EXCEPTION] retriving user by credentials, {}", e.getMessage());
			return null;
		
		}finally{
			LOG.info("[END]- Checking creadetilas");
		}
		
	}
	
	/**
	 * <p>This will check for the existing session for the user,
	 * if session found for the current or previous token, 
	 * it will validate and return for the next step
	 * else null
	 * @param authId
	 * @param prevAuthId
	 * @param token
	 * @param prevToken
	 * @param profileId
	 * @param device
	 * @param ip
	 * @return
	 */
	public Map<String, Object> checkSession(final String authId, final String prevAuthId, final String token, final String prevToken, final String profileId, final String device, final String ip){
		
		LOG.info("[START]- Checking session details");
		//Step 1- Check all the input parameters shouldn't be empty
		if(StringUtility.checkEmptyString(authId, prevAuthId, token, prevToken, profileId)){
			return null;
		}
		
		try{
			//Step 2- Get session details by authentication id
			Map<String, Object> session = getSession(0, authId, prevAuthId);
			Assert.notEmpty(session, "No Session Found with the provided details.");
			
			//Step 3- If session is valid
			if(!isValidSession(session, profileId, token, prevToken)){
				return null;
			}
			
			int interval = Integer.parseInt((String)session.get(CommonConstants.AUTH_TOKEN_INTERVAL));
			
			//Step 4- If token renew required
			if(isTokenRenewalRequired(session, ip)){
				
				String sessionId = authenticationDao.getNextSessionId();
				String newToken = EncryptionUtil.getRandomId(CommonConstants.COOKIE_TOKEN_SIZE);
				
				return buildAuthSession(session.get(CommonConstants.PRF_ID), sessionId, authId, newToken, token, interval, device, ip, CommonConstants.YES);
			}
			
			return buildAuthSession(session.get(CommonConstants.PRF_ID), authId, prevAuthId, token, prevToken, interval, device, ip, CommonConstants.NO);
			
		}catch(Exception e){
			
			LOG.error("[EXCEPTION] Checking session {}",e);
			return null;
			
		}finally{
			
			LOG.info("[END]- Checking session details");
			
		}
	}
	
	/**
	 * <p>Get the session details by login id,
	 * if the current token is not saved due to some reason 
	 * it will check with previous token
	 * 
	 * @param index
	 * @param authIds
	 * @return
	 */
	private Map<String, Object> getSession(int index, final String ... authIds){
		
		LOG.info("[START]- Getting session details");
		
		try{
			
			Map<String, Object> user = authenticationDao.getSessionByAuthId(authIds[index]);
			Assert.notEmpty(user);
			return user;
			
		}catch(Exception e){
			
			LOG.warn("[WARN]Session details not found");
			return index < (authIds.length-1) ? getSession(index++, authIds): null;
			
		}finally{
			LOG.info("[END]- Getting session details");
		}
	}
	
	/**
	 * <p>This will check if the retrieved data from Data base is matched with the user and token is valid.
	 * @param session
	 * @param profileId
	 * @param token
	 * @param prevToken
	 * @return
	 */
	private boolean isValidSession(final Map<String, Object> session, final String profileId, final String token, final String prevToken){
		
		//Check if the retrived session is for this user or not
		String tokenToValidate = (String)session.get(CommonConstants.AUTH_TOKEN);
		String expiryOn = (String)session.get(CommonConstants.AUTH_TOKEN_EXPIRES_ON);
		
		//Check expired of the token and matching of token
		//Check profile id
		if(profileId.equals(session.get(CommonConstants.PRF_ID)) 
				&& DateTimeFactory.compareDateTimeWithCurrentDate(expiryOn) > 0 
				&& (tokenToValidate.equals(token) || tokenToValidate.equals(prevToken))){
			
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
	
	/**
	 * <p>Checks if ip has been changed or session save time is older than 15 mins
	 * then returns true, else false.
	 * @param session
	 * @param ip
	 * @return
	 */
	private boolean isTokenRenewalRequired(final Map<String, Object> session, final String ip){

		String ipTovalidate = (String) session.get(CommonConstants.IP);
		String createOn = (String)session.get(CommonConstants.FL_ON);
		
		//Check if IP has been changed or create date is more than 15 mins then needs a fresh key.
		if(!ipTovalidate.equals(ip) 
				|| DateTimeFactory.getMinuteDifferance(createOn) >= 15){
			
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
	
	/**
	 * <p>This creates the map for the new session store.
	 * @param profileId
	 * @param auth_id
	 * @param prev_auth_id
	 * @param token
	 * @param prev_token
	 * @param expiryOnInterval
	 * @param device
	 * @param ip
	 * @return
	 */
	private Map<String, Object> buildAuthSession(Object profileId, String auth_id, String prev_auth_id, String token, String prev_token, int expiryOnInterval, String device, String ip, String saveSession){
		Map<String, Object> session = new HashMap<String, Object>(10);
		session.put(CommonConstants._ID, auth_id);
		session.put(CommonConstants.PRF_ID, profileId);
		session.put(CommonConstants.PRV_AUTH_ID, prev_auth_id);
		session.put(CommonConstants.AUTH_TOKEN, token);
		session.put(CommonConstants.PRV_AUTH_TOKEN, prev_token);
		session.put(CommonConstants.FL_ON, DateTimeFactory.getCurrentDateTimeInString());
		session.put(CommonConstants.AUTH_TOKEN_EXPIRES_ON, DateTimeFactory.getDateAfterSomeDays(expiryOnInterval));
		session.put(CommonConstants.AUTH_TOKEN_INTERVAL, expiryOnInterval);
		session.put(CommonConstants.FL_DEVICE, device.hashCode());
		session.put(CommonConstants.ID_DEVICE, device);
		session.put(CommonConstants.IP, ip);
		session.put(CommonConstants.SAVE_SESSION, saveSession);
		return session;
	}

}
