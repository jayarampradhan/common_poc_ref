/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.register.service
 * @createdOn 09-Mar-20148:25:52 pm
 * @modifiedby Jayaram
 * @modifiedon 09-Mar-20148:25:52 pm
 * ***********************************************************************
 */

package com.uimirror.api.common.register.service;

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
import com.uimirror.api.common.bean.RegisterBean;
import com.uimirror.api.common.bean.ResendTokenEmailBean;
import com.uimirror.api.common.exception.CommonApiSystemException;
import com.uimirror.api.common.register.dao.TempUserDao;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.encryption.util.EncryptData;
import com.uimirror.common.encryption.util.EncryptionUtil;
import com.uimirror.common.exception.CommonErrorConstants;
import com.uimirror.common.mail.Mailer;
import com.uimirror.common.thread.CachedPoolService;
import com.uimirror.common.util.DateTimeFactory;
import com.uimirror.common.util.ResponseHelper;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : implementation of {@code RegisterationService}
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 09-Mar-20148:25:52 pm
 * @modifiedby Jayaram
 * @modifiedon 09-Mar-20148:25:52 pm
 * ***********************************************************************
 */

public class RegisterationServiceImpl implements RegisterationService {
    
    protected static final Logger LOG = LoggerFactory.getLogger(RegisterationServiceImpl.class);
    
    @Autowired 
    private Validator validator;
    
    @Autowired
    private TempUserDao tempUserDao;
    
    @Autowired
    private Mailer mailer;
    
    private String baseUrl;
    
    public RegisterationServiceImpl() {
    }

    /* (non-Javadoc)
     * @see com.uimirror.api.common.register.service.RegisterationService#doRegister(com.uimirror.api.common.bean.RegisterBean)
     */
    @Override
    public Map<String, Object> doRegister(RegisterBean registerBean) throws CommonApiSystemException {
    	LOG.info("[START]- Registration validation and perssit.");
    	//Step 1- Check for the not null of the object.
    	Assert.notNull(registerBean);
    	Assert.notEmpty(registerBean.getFacts());
    	Set<ConstraintViolation<RegisterBean>> violations = validator.validate(registerBean);
    	Assert.notNull(violations);
    	if(violations.size() > CommonConstants.NUMBER_0){
    		//Return field validation failed
    		return ResponseHelper.buildValidationFaildResponse(violations);
    	}
	
    	try {

    		//Step 2- get Temp profile Id
    		String profileId = tempUserDao.getNextProfileId();
    		//Step 3- Create User in temp collection
    		String linkToken = EncryptData.encrypt(EncryptionUtil.getRandomId(CommonConstants.REG_LNK_TOKEN_LN), CommonConstants.REG_LNK_TOKEN_LN_ENC_KEY, "register").getEncryptdMsg();
    		String formToken = EncryptionUtil.getRandomId(CommonConstants.REG_FORM_TOKEN_LN);
    		tempUserDao.createUser(createUserDocument(registerBean.getFacts(), profileId, linkToken, formToken));
		
    		//Step 4- Send Email Asyncly
    		sendWelcomeMail(linkToken, formToken, profileId, registerBean.getFacts());

    		//Step 5- exist with form return
    		return buildRegisterationSucessResponse(profileId, linkToken, formToken);

    	} catch (MongoException me) {
    		LOG.error("[EXCEPTION] Creating New User experienced and exception, and exception stack is:{}", me.getMessage());
    		throw new CommonApiSystemException(CommonErrorConstants.COMMON_DB_ERROR , CommonErrorConstants.COMMON_DB_ERROR_CD);
    	}finally{
    		LOG.info("[END]-Registration validation and perssit.");
    	}
    
    }
    
    /* (non-Javadoc)
     * @see com.uimirror.api.common.register.service.RegisterationService#reSendVerifyLinkMail(com.uimirror.api.common.bean.ResendEmailTokenMailBean)
     */
    @Override
	public Map<String, Object> reSendVerifyLinkMail(ResendTokenEmailBean rMailBean) throws CommonApiSystemException {
    	LOG.info("[START]- Resending Email with new token.");
    	//Step 1- Check for the not null of the object.
    	Assert.notNull(rMailBean);
    	Assert.notEmpty(rMailBean.getFacts());
    	Set<ConstraintViolation<ResendTokenEmailBean>> violations = validator.validate(rMailBean);
    	Assert.notNull(violations);
    	if(violations.size() > CommonConstants.NUMBER_0){
    		//Return field validation failed
    		return ResponseHelper.buildValidationFaildResponse(violations);
    	}
    	try{
    		
    		Map<String, Object> states = rMailBean.getStates();
    		Assert.notEmpty(states);
    		//Step 2- No validation issue, move to generate new tokens
    		String profileId = (String)rMailBean.getFact(CommonConstants.PRF_ID);
    		String linkToken = EncryptData.encrypt(EncryptionUtil.getRandomId(CommonConstants.REG_LNK_TOKEN_LN), CommonConstants.REG_LNK_TOKEN_LN_ENC_KEY, "register").getEncryptdMsg();
    		String formToken = EncryptionUtil.getRandomId(CommonConstants.REG_FORM_TOKEN_LN);
    		
    		//Step 3- Update Token details with DB
    		tempUserDao.updateUserByProfileId(buildTokenUpdateMap(linkToken, formToken), profileId);
    		
    		//Step 4- Send Email
    		sendWelcomeMail(linkToken, formToken, profileId, states);

    		//Step 5-Finally Send the sucess Response.
    		return buildRegisterationSucessResponse(profileId, linkToken, formToken);
    		
    	}catch(MongoException me){
    		LOG.error("[EXCEPTION] Resening Email token got some issue, and exception stack is:{}",me.getMessage());
    		throw new CommonApiSystemException(CommonErrorConstants.COMMON_DB_ERROR , CommonErrorConstants.COMMON_DB_ERROR_CD);
    	}finally{
    		LOG.info("[END]- Resending Email with new token.");
    	}
	}
	

	/**
     * <p>This will build the dcoument which will 
     * be saved into temp user collection.
     * @param args
     * @param profileId
     * @return
     */
    private Map<String, Object> createUserDocument(final Map<String, Object> args, final String profileId, final String linkToken, final String formToken){
    	Map<String, Object> doc = new HashMap<String, Object>(11);
    	doc.put(CommonConstants._ID, profileId);
    	doc.put(CommonConstants.FL_FIRST_NAME, args.get(CommonConstants.FL_FIRST_NAME));
    	doc.put(CommonConstants.FL_LAST_NAME, args.get(CommonConstants.FL_LAST_NAME));
    	doc.put(CommonConstants.EMAIL, args.get(CommonConstants.EMAIL));
    	doc.put(CommonConstants.PASSWORD, args.get(CommonConstants.PASSWORD));
    	doc.put(CommonConstants.FL_DATE_OF_BIRTH, args.get(CommonConstants.FL_DATE_OF_BIRTH));
    	doc.put(CommonConstants.FL_SEX, args.get(CommonConstants.FL_SEX));
    	doc.put(CommonConstants.FL_ON, DateTimeFactory.getCurrentDateTimeInString());
    	doc.put(CommonConstants.FL_LN_TOKEN, linkToken);
    	doc.put(CommonConstants.FL_FORM_TOKEN, formToken);
    	String app = (String)args.get(CommonConstants.FL_APP);
    	doc.put(CommonConstants.FL_APP, app == null ? CommonConstants.SOCIAL : app);
    	return doc;
    }

    /**
     * <p>This will build the registration success response map
     * @param formToken 
     * @param linkToken 
     * @param tempProfileId Created Recently
     */
    private Map<String, Object> buildRegisterationSucessResponse(final String profileId, final String linkToken, final String formToken){
    	Map<String, Object> resMap = new HashMap<String, Object>(4);
    	resMap.put(CommonConstants.RESPONSE_CODE, CommonConstants.REG_SUCESS_RS_CD);
    	resMap.put(CommonConstants.REG_TMP_PRF_ID, profileId);
    	resMap.put(CommonConstants.FL_LN_TOKEN, linkToken);
    	resMap.put(CommonConstants.FL_FORM_TOKEN, formToken);
    	return resMap;
    }
    
    /**
     * <p>This will build the welcome mail and send to user asyncly
     * @param linkToken
     * @param formToken
     * @param profileId
     * @param states
     */
    private void sendWelcomeMail(final String linkToken, final String formToken, final String profileId, final Map<String, Object> states) {
    	
    	String email = (String)states.get(CommonConstants.EMAIL);
    	StringBuilder sb = new StringBuilder(100);
    	sb.append("?q=");
    	sb.append(email);
    	sb.append(CommonConstants.GET_PARAM);
    	sb.append("d=");
    	sb.append(linkToken);
    	sb.append(CommonConstants.GET_PARAM);
    	sb.append("i=");
    	sb.append(profileId);
    	sb.append("&ref=email");
    	
    	String link = baseUrl+sb.toString();
    	
    	CachedPoolService cService = CachedPoolService.getInstance(1);
    	List<Callable<Object>> jobs = new ArrayList<Callable<Object>>(1);
    	jobs.add(new WelcomeMailCallable(this.mailer, (String)states.get(CommonConstants.FL_FIRST_NAME), email, formToken, link, email));

    	cService.submitTask(jobs);

    }
    
    /**
     * <p>This will create map for updating user token details
     * @param linkToken
     * @param formToken
     * @return
     */
    private Map<String, Object> buildTokenUpdateMap(final String linkToken, final String formToken) {
    	Map<String, Object> updates = new HashMap<String, Object>(2);
    	updates.put(CommonConstants.FL_LN_TOKEN, linkToken);
    	updates.put(CommonConstants.FL_FORM_TOKEN, formToken);
		return updates;
	}

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

	public void setTempUserDao(TempUserDao tempUserDao) {
		this.tempUserDao = tempUserDao;
	}

	public void setMailer(Mailer mailer) {
		this.mailer = mailer;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

}


