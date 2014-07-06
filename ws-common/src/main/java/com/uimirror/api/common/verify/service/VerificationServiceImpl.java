/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.register.service
 * @createdOn 15-Mar-2014 12:13:41 AM
 * @modifiedby Jayaram
 * @modifiedon 15-Mar-2014 12:13:41 AM
 * ***********************************************************************
 */
package com.uimirror.api.common.verify.service;

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
import com.uimirror.api.common.bean.VerifyBean;
import com.uimirror.api.common.exception.CommonApiSystemException;
import com.uimirror.api.common.register.dao.TempUserDao;
import com.uimirror.api.common.user.dao.UserDetailsDao;
import com.uimirror.api.common.user.dao.UserDao;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.annotation.ReadCommitedWriteTransaction;
import com.uimirror.common.authentication.dao.AuthenticationDao;
import com.uimirror.common.authorization.dao.AuthorizationDao;
import com.uimirror.common.exception.CommonErrorConstants;
import com.uimirror.common.thread.CachedPoolService;
import com.uimirror.common.util.DateTimeFactory;
import com.uimirror.common.util.PrivacyBuilder;
import com.uimirror.common.util.ProfileDataBuilder;
import com.uimirror.common.util.ResponseHelper;
import com.uimirror.common.util.StringUtility;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : implementation of {@link VerificationService}.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 15-Mar-2014 12:13:41 AM
 * @modifiedby Jayaram
 * @modifiedon 15-Mar-2014 12:13:41 AM
 * ***********************************************************************
 */
public class VerificationServiceImpl implements VerificationService{
	
	protected static final Logger LOG = LoggerFactory.getLogger(VerificationServiceImpl.class);
	
	@Autowired 
    private Validator validator;
	
	@Autowired
	private UserDao userProfileDao;
	
	@Autowired
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	private AuthenticationDao authenticationDao;

	@Autowired
	private AuthorizationDao authorizationDao;
	
	@Autowired
	private TempUserDao tempUserDao;
	

	/* (non-Javadoc)
	 * @see com.uimirror.api.common.verify.service.VerificationService#doVerify(com.uimirror.api.common.bean.VerifyBean)
	 */
	@Override
	@ReadCommitedWriteTransaction
	public Map<String, Object> doVerify(VerifyBean verifyBean) throws CommonApiSystemException{
		
		LOG.info("[START]-verification validation and perssit.");
    	
		//Step 1- Check for the not null of the object.
    	Assert.notNull(verifyBean);
    	//Step 2- Validate the bean
    	Set<ConstraintViolation<VerifyBean>> violations = validator.validate(verifyBean);
    	Assert.notNull(violations);
    	if(violations.size() > CommonConstants.NUMBER_0){
    		//Return field validation failed
    		return ResponseHelper.buildValidationFaildResponse(violations);
    	}
    	
    	try{
    		//Step 3- Get the states of the user
    		Map<String, Object> user = verifyBean.getStates();
    		Assert.notEmpty(user, "User Can't be empty");
    		
    		//Step 4-Save new User first
    		String profileId = userProfileDao.getNextprofileId();
    		userProfileDao.createUserProfile(buildNewUser(user, verifyBean.getFacts(), profileId));
    		userDetailsDao.createUserDetails(buildUserDetails(user, verifyBean.getFacts(), profileId));
    		
    		//Step 5- Create User Authentication and Authorization
    		authenticationDao.addUser(buildUserCredentials(profileId, user));
    		authorizationDao.createAuthorization(buildAuthorization(profileId));
    		
    		//Step 6- Delete temp profile Asynly
    		deleteTempProfile((String)user.get(CommonConstants._ID));
    		
    		//Step 7- Finally returns to the user
    		return buildVerificationSucessResponse(user, profileId);
    	
    	}catch(MongoException me){
    		LOG.error("[EXCEPTION] While saving user: {}",me);
    		throw new CommonApiSystemException(CommonErrorConstants.COMMON_DB_ERROR , CommonErrorConstants.COMMON_DB_ERROR_CD);
    	}
	}

	/**
	 * <p>This will create first time user profile details.
	 * @param user
	 * @param additional
	 * @param profileId 
	 * @return
	 */
	public Map<String, Object> buildNewUser(final Map<String, Object> user, final Map<String, Object> additional, String profileId){
		Map<String, Object> newUser = new HashMap<String, Object>(5);
		
		//Profile Id First
		newUser.put(CommonConstants._ID, profileId);
		
		//Name First
		newUser.put(CommonConstants.FL_NAME, ProfileDataBuilder.buildName(user.get(CommonConstants.FL_FIRST_NAME), user.get(CommonConstants.FL_LAST_NAME)));
		
		//SEX Second
		newUser.put(CommonConstants.FL_SEX, user.get(CommonConstants.FL_SEX));
		
		//Active status default is active
		newUser.put(CommonConstants.FL_ACTIVE, CommonConstants.YES);
		
		//User Extra Info like timezone, currency, local etc
		newUser.put(CommonConstants.FL_TIME_ZONE, additional.get(CommonConstants.FL_TIME_ZONE));
		newUser.put(CommonConstants.FL_LOCALE, additional.get(CommonConstants.FL_LOCALE));
		String currency = (String)additional.get(CommonConstants.FL_CURRENCY);
		if(!StringUtility.checkEmptyString(currency)){
			newUser.put(CommonConstants.FL_CURRENCY, currency);
		}
		//Privacy
		Map<String, Object> profilePrivacy = new HashMap<String, Object>(3);
		profilePrivacy.put(CommonConstants.FL_ALLOW_FRIEND_REQ, CommonConstants.PUBLIC);
		profilePrivacy.put(CommonConstants.FL_ALLOW_INBOX_MSG, CommonConstants.FRIENDS);
		
		//Search me Map
		Map<String, Object> lookUp = new HashMap<String, Object>();
		lookUp.put(CommonConstants.FL_EMAIL_SEARCH_ME, CommonConstants.PUBLIC);
		lookUp.put(CommonConstants.FL_MOBILE_SEARCH_ME, CommonConstants.ONLY_ME);
		
		profilePrivacy.put(CommonConstants.FL_SEARCH_ME, lookUp);

		profilePrivacy.put(CommonConstants.FL_SEE_MY_POST, CommonConstants.FRIENDS);
		newUser.put(CommonConstants.FL_PRIVACY, profilePrivacy);
		return newUser;
	}
	
	/**
	 * <p>This will build the Map for the Authentication.
	 * @param profileId
	 * @param user
	 * @return
	 */
	public Map<String, Object> buildUserCredentials(final String profileId, final Map<String, Object> user){
		Map<String, Object> auth = new HashMap<String, Object>(3);
		auth.put(CommonConstants._ID, profileId);
		auth.put(CommonConstants.PASSWORD, user.get(CommonConstants.PASSWORD));
		//Build User id map
		Map<String, Object> uid = new HashMap<String, Object>(1);
		uid.put(CommonConstants.EMAIL, user.get(CommonConstants.EMAIL));
		auth.put(CommonConstants.FL_USER_ID, uid);
		return auth;
	}

	/**
	 * <p>This Will build the authorization map for the first time user.
	 * @param profileId
	 * @return
	 */
	public Map<String, Object> buildAuthorization(String profileId){
		Map<String, Object> authorization = new HashMap<String, Object>(2);
		authorization.put(CommonConstants._ID, profileId);
		authorization.put(CommonConstants.TYPE, CommonConstants.BASIC);
		return authorization;
	}
	
	/**
	 * <p>This will aschyncl delete temp user profile that has been created for temp purpose.
	 * @param profileId
	 */
	private void deleteTempProfile(String profileId) {
		LOG.info("[START]- Job Submitted to delete temporary profile.");
		CachedPoolService cp = CachedPoolService.getInstance(1);
		List<Callable<Object>> jobs = new ArrayList<Callable<Object>>(1);
		jobs.add(new DeleteTempProfile(profileId, this.tempUserDao));
		cp.submitTask(jobs);
		LOG.info("[END]- Job Submitted to delete temporary profile.");
	}
	
	/**
	 * <p>This will build the user details map for extra info about the user.
	 * @param user
	 * @param facts
	 * @param profileId
	 * @return
	 */
	private Map<String, Object> buildUserDetails(Map<String, Object> user, Map<String, Object> facts, String profileId) {
		
		Map<String, Object> userDetails = new HashMap<String, Object>(3);
		
		//Profile Id First
		userDetails.put(CommonConstants._ID, profileId);

		//Birthday Date Privacy
		Map<String, Object> bdDatePr =  PrivacyBuilder.buildPrivacy(CommonConstants.PUBLIC, null, null);
		Map<String, Object> bdYearPr =  PrivacyBuilder.buildPrivacy(CommonConstants.ONLY_ME, null, null);
		
		//Date Of Birth
		userDetails.put(CommonConstants.FL_DATE_OF_BIRTH, ProfileDataBuilder.buildDateOfBirth(user.get(CommonConstants.FL_DATE_OF_BIRTH), bdDatePr, bdYearPr));
		
		//Extra Info About the profile
		Map<String, Object> extraInfo = new HashMap<String, Object>(5);
		extraInfo.put(CommonConstants.FL_ON, DateTimeFactory.getCurrentDateTimeInString());
		extraInfo.put(CommonConstants.IP, facts.get(CommonConstants.IP));
		userDetails.put(CommonConstants.FL_REGISTRY, extraInfo);
		
		return userDetails;
	}
	
	/**
	 * <p>This will build the final sucess message for the verification process.
	 * @param user
	 * @param profileId 
	 * @return
	 */
	private Map<String, Object> buildVerificationSucessResponse(final Map<String, Object> user, String profileId) {
		Map<String, Object> resMap = new HashMap<String, Object>(4);
    	resMap.put(CommonConstants.RESPONSE_CODE, CommonConstants.VRFY_SUCESS_RS_CD);
    	resMap.putAll(user);
    	resMap.put(CommonConstants.PRF_ID, profileId);
    	return resMap;
	}
	
	/**
	 * 
	 * ***********************************************************************
	 * This class is developed by UIMirror Team.
	 * Details of class goes like : Inner class for the temp profile delete 
	 * by callable.
	 * 
	 * @author Jayaram
	 * @version $
	 * @see 
	 * @createdOn 29-Mar-2014 12:57:07 AM
	 * @modifiedby Jayaram
	 * @modifiedon 29-Mar-2014 12:57:07 AM
	 * ***********************************************************************
	 */
	private class DeleteTempProfile implements Callable<Object>{

		private final String profileId;
		private final TempUserDao tempUserDao;
		
		public DeleteTempProfile(String profileId, TempUserDao tempUserDao){
			this.profileId = profileId;
			this.tempUserDao = tempUserDao;
		}
		@Override
		public Object call() throws Exception {
			this.tempUserDao.deleteUser(profileId);
			return null;
		}
		
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public void setAuthenticationDao(AuthenticationDao authenticationDao) {
		this.authenticationDao = authenticationDao;
	}

	public void setAuthorizationDao(AuthorizationDao authorizationDao) {
		this.authorizationDao = authorizationDao;
	}

	public void setTempUserDao(TempUserDao tempUserDao) {
		this.tempUserDao = tempUserDao;
	}

	public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}

}
