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

package com.uimirror.api.common;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.uimirror.api.common.bean.RegisterBean;
import com.uimirror.api.common.bean.ResendTokenEmailBean;
import com.uimirror.api.common.bean.VerifyBean;
import com.uimirror.api.common.register.service.RegisterationService;
import com.uimirror.api.common.verify.service.VerificationService;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.authentication.service.AuthenticationServiceController;
import com.uimirror.common.util.ResponseHelper;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : will handle the service for the common
 * http request like register login etc.
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
public class CommonServiceController {

    protected static final Logger LOG = LoggerFactory.getLogger(CommonServiceController.class);
    
    @Autowired
    private RegisterationService registerationService;
    
    @Autowired
    private VerificationService verificationService;
    
    @Autowired
	private AuthenticationServiceController authenticationServiceController;
    
    
    public CommonServiceController() {
    }

    /**
     * <p>registerUser, helps to register a user by providing few required details.
     * such as name, email, password etc.
     * <p>To save the user it has following steps
     * <ol>
     * <li>populate bean from map</li>
     * <li>validate the bean with rule engine</li>
     * <li>validate the email id for already exist or not</li>
     * <li>finally persist into DB</li>
     * <li>Send a welcome and verification mail aschynly</li>
     * <li>return to the caller for the next step with the data, else with error</li>
     * </ol>
     * @param T, a {@code Map} which has the register form. 
     * @return {@link String} either with error message or success and required message for next step. 
     * 
     **/
	public Map<String, Object> registerUser(Map<String, Object> request) {
	
    	LOG.info("[START]-Registeration process for the user is intiated.");
    	
    	try {
    		
    		Assert.notEmpty(request, "Request Can't be empty.");
    		RegisterBean rg = new RegisterBean(request);

    		//Process registration
    		Map<String, Object> responseMap = registerationService.doRegister(rg);
    		
    		Assert.notEmpty(responseMap);
	    
    		String responseCode = (String)responseMap.get(CommonConstants.RESPONSE_CODE);
    		responseMap.remove(responseCode);
    		
    		return ResponseHelper.buildResponseMap(responseCode, CommonConstants.MSG, responseMap);
	
    	} catch (Exception e) {
    		
    		LOG.error("[EXCEPTION]- Registeration process stopped due to some internal error, error details: {}",e);
    		if(e instanceof IllegalArgumentException){
    	
    			return ResponseHelper.buildResponseMap(CommonConstants.INVALID_DATA_RS_CD, CommonConstants.INVALID_DATA_RS_MSG);
    		
    		}
    		
    		return ResponseHelper.buildResponseMap(CommonConstants.INTERNAL_ERR_RS_CD, CommonConstants.INTERNAL_ERR_RS_MSG);
    	
    	}finally{
    		
    		LOG.info("[END]-Registeration process for the user.");
    	
    	}

    }
    
    /**
     * <p>Verify user helps, to complete the action performed by the user.
     * User can verify his account in two ways:
     * <ul>
     * <li>by entering the token sent to email.</li>
     * <li>By clicking on the activation link sent to the registered email</li>
     * </ul>
     * <p>The complete process of verification has been divided into following steps:
     * <ol>
     * <li>populate bean from map</li>
     * <li>validate the bean with rule engine and decide by which action user is verifying account</li>
     * <li>retrieve all the object state and persist into main table</li>
     * <li>Do first time login</li>
     * <li>finally return the user with status code for the action</li>
     * <li>Aschnly delete the temp record</li>
     * </ol>
     * @param verifyForm
     * @return
     */
	public Map<String, Object> verifyUser(Map<String, Object> request){

    	LOG.info("[START]-Verification process for the user is intiated.");
    	
    	try {
    		
    		Assert.notEmpty(request, "Request Can't be empty.");
    		VerifyBean vrfyBean = new VerifyBean(request);
    		
    		//Process verification
    		Map<String, Object> responseMap = verificationService.doVerify(vrfyBean);
    		
    		Assert.notEmpty(responseMap);
    	    
    		String responseCode = (String)responseMap.get(CommonConstants.RESPONSE_CODE);
    		
    		responseMap.remove(responseCode);
    		Map<String, Object> response = null;
    		
    		//Check response code if success do login
    		if(CommonConstants.VRFY_SUCESS_RS_CD.equals(responseCode)){

    			Map<String, Object> lgnRes = authenticationServiceController.doAuthenticate(buildAuthenticationForm(responseMap, request));
        		
        		//Build response with auth block
        		response = ResponseHelper.buildResponseMap(responseCode, CommonConstants.RES_DATA_BLOCK, buildVerificationResponseUser(responseMap));
        		ResponseHelper.editResponseMap(response, CommonConstants.AUTHENTICATION_BLOCK, lgnRes.get(CommonConstants.MSG));

    		}else{
    		
    			//Something went wrong so return him with error response
    			response = ResponseHelper.buildResponseMap(CommonConstants.VRFY_ERR_RS_CD, CommonConstants.VRFY_ERR_RS_MSG);
    			
    		}

    		//finally return response.
    		return response;
	
    	} catch (Exception e) {
    		
    		LOG.error("[END]- Verification process stopped due to some internal error, error details: {}",e);
    		return ResponseHelper.buildResponseMap(CommonConstants.INTERNAL_ERR_RS_CD, CommonConstants.INTERNAL_ERR_RS_MSG);
    	
    	}finally{
    	
    		LOG.info("[END]-Verification process for the user.");
    	
    	}
    }

    /**
     * <p>This will re-send mail to the newly created user.
     * <p>Steps are :
     * <ol>
     * <li>Validate the bean with rule engine if it is the same user or not</li>
     * <li>Generate new token</li>
     * <li>Update new token into DB</li>
     * <li>Send Email</li>
     * </ol>
     * @param request
     * @return
     */
	public Map<String, Object> resendTokenMail(Map<String, Object> request){

    	LOG.info("[START]- Resend Email for the verification link and token.");
    	
    	try {

    		Assert.notEmpty(request, "Request Can't be Empty.");
    		
    		ResendTokenEmailBean rMailBean = new ResendTokenEmailBean(request);
    		
    		//Process Re-send EMail Token mail
    		Map<String, Object> responseMap = registerationService.reSendVerifyLinkMail(rMailBean);
    		
    		Assert.notEmpty(responseMap);
    	    
    		String responseCode = (String)responseMap.get(CommonConstants.RESPONSE_CODE);
    		responseMap.remove(responseCode);
    		
    		return ResponseHelper.buildResponseMap(responseCode, CommonConstants.MSG, responseMap);
	
    	} catch (Exception e) {
    		LOG.error("[END]- Re-Send Email Token process stopped due to some internal error, error details: {}",e);
    		return ResponseHelper.buildResponseMap(CommonConstants.INTERNAL_ERR_RS_CD, CommonConstants.INTERNAL_ERR_RS_MSG);
    	}finally{
    		LOG.info("[END]- Resend Email for the verification link and token.");
    	}
    }

    /**
     * <p>This will build form for the login process.
     * @param req
     * @param clientForm 
     * @return
     */
    private Map<String, Object> buildAuthenticationForm(Map<String, Object> req, Map<String, Object> clientForm){
    	
    	Map<String, Object> authReq = new HashMap<String, Object>(6);
    	//Set auth type form
    	authReq.put(CommonConstants.AUTH_TYPE, CommonConstants.FORM);
    	authReq.put(CommonConstants.CLIENT_META, clientForm.get(CommonConstants.CLIENT_META));
    	authReq.put(CommonConstants.IP, clientForm.get(CommonConstants.IP));
    	authReq.put(CommonConstants.USER_ID, req.get(CommonConstants.EMAIL));
    	authReq.put(CommonConstants.PASSWORD, req.get(CommonConstants.PASSWORD));
    	authReq.put(CommonConstants.KEEP_ME_LGN, CommonConstants.NO);
    	authReq.put(CommonConstants.USER_ID_TYPE, CommonConstants.ID_EMAIl);
    	
    	return authReq;
    }
    
    /**
     * <p>This will create response map
     *  <p>build map with these info Name, Sex, Email, profile Id, tz, locale, currency
     * @param res
     * @return
     */
    private Map<String, Object> buildVerificationResponseUser(Map<String, Object> res){
    	
    	//Name, Sex, Email, profile Id, tz, locale, currency
    	Map<String, Object> usr = new HashMap<String, Object>(7);
    	usr.put(CommonConstants.PRF_ID, res.get(CommonConstants.PRF_ID));
    	usr.put(CommonConstants.FL_FIRST_NAME, res.get(CommonConstants.FL_FIRST_NAME));
    	usr.put(CommonConstants.FL_LAST_NAME, res.get(CommonConstants.FL_LAST_NAME));
    	usr.put(CommonConstants.FL_SEX, res.get(CommonConstants.FL_SEX));
    	usr.put(CommonConstants.EMAIL, res.get(CommonConstants.EMAIL));
    	usr.put(CommonConstants.FL_TIME_ZONE, res.get(CommonConstants.FL_TIME_ZONE));
    	usr.put(CommonConstants.FL_LOCALE, res.get(CommonConstants.FL_LOCALE));
    	usr.put(CommonConstants.FL_CURRENCY, res.get(CommonConstants.FL_CURRENCY));
    	usr.put(CommonConstants.FL_APP, res.get(CommonConstants.FL_APP));
    	
    	return usr;
    	
    }
    
    public void setRegisterationService(RegisterationService registerationService) {
        this.registerationService = registerationService;
    }

	public void setVerificationService(VerificationService verificationService) {
		this.verificationService = verificationService;
	}

	public void setAuthenticationServiceController(AuthenticationServiceController authenticationServiceController) {
		this.authenticationServiceController = authenticationServiceController;
	}

}


