/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.controller
 * @createdOn 05-Mar-201412:12:07 AM
 * @modifiedby Jayaram
 * @modifiedon 05-Mar-201412:12:07 AM
 * ***********************************************************************
 */

package com.uimirror.api.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.uimirror.api.common.CommonServiceController;
import com.uimirror.common.annotation.ProfileExecution;
import com.uimirror.common.authentication.service.AuthenticationServiceController;
import com.uimirror.common.util.CollectionUtils;
import com.uimirror.common.util.GsonUtility;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : This will handle all the common requests 
 * of the user for different activities like login, profile update, get
 * profile details etc.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 05-Mar-201412:12:07 AM
 * @modifiedby Jayaram
 * @modifiedon 05-Mar-201412:12:07 AM
 * ***********************************************************************
 */
@Component
@Path("/")
@Singleton
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class UiMirrorCommonControllerService implements Serializable{

    private static final long serialVersionUID = 4065801835455386561L;
    protected static final Logger LOG = LoggerFactory.getLogger(UiMirrorCommonControllerService.class);
    private UriInfo uriInfo;
    private CommonServiceController commonServiceController;
    private AuthenticationServiceController authenticationServiceController;

    public UiMirrorCommonControllerService() {
    }
    
    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @RolesAllowed({ "ADMIN"})
    @ProfileExecution
    public String registerUser(MultivaluedMap<String, String> request){
    	
    	LOG.info("[START]-Request Recived to register a user from {}",uriInfo.getBaseUri());
    	Map<String, Object> newUser = new HashMap<String, Object>(1);
    	newUser.putAll(CollectionUtils.getInstance().getMapFromMultiMap(request));
    	Map<String, Object> res = commonServiceController.registerUser(newUser);
    	LOG.debug("[END]- Request Completed for Registering a user");
    	
    	return GsonUtility.convertToGsonString(res);
    	
    }
    
    @POST
    @Path("resendlinktoken")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @RolesAllowed({ "ADMIN"})
    @ProfileExecution
    public String resendEmailToken(MultivaluedMap<String, String> request){
    	
    	LOG.info("[START]-Request Recived to resend token email from {}",uriInfo.getBaseUri());
    	Map<String, Object> form = new HashMap<String, Object>(1);
    	form.putAll(CollectionUtils.getInstance().getMapFromMultiMap(request));
    	Map<String, Object> res = commonServiceController.resendTokenMail(form);
    	LOG.debug("[END]- Request Completed for resend token email");
    	
    	return GsonUtility.convertToGsonString(res);
    }
    
    @POST
    @Path("verify")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @RolesAllowed({ "ADMIN"})
    @ProfileExecution
    public String verifyUser(MultivaluedMap<String, String> request){
    	
    	LOG.info("[START]-Request Recived to verify a  user from {}",uriInfo.getBaseUri());
    	Map<String, Object> form = new HashMap<String, Object>(1);
    	form.putAll(CollectionUtils.getInstance().getMapFromMultiMap(request));
    	Map<String, Object> res = commonServiceController.verifyUser(form);
    	LOG.info("[END]- Verification process Completed");
    
    	return GsonUtility.convertToGsonString(res);
    
    }
    
    @POST
    @Path("auth")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @RolesAllowed({ "ADMIN"})
    @ProfileExecution
    public String authenticateUser(MultivaluedMap<String, String> request){
    	
    	LOG.info("[START]-Request Recived to Authenticate a  user from {}",uriInfo.getBaseUri());
    	
    	Map<String, Object> auth = new HashMap<String, Object>(1);
    	auth.putAll(CollectionUtils.getInstance().getMapFromMultiMap(request));
    	
    	Map<String, Object> res = authenticationServiceController.doAuthenticate(auth);
    	LOG.info("[END]- Authentication process Completed");
    	
    	return GsonUtility.convertToGsonString(res);
    }
    
    
    @Context
    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }
    
    @Inject
    public void setCommonServiceController(CommonServiceController commonServiceController) {
        this.commonServiceController = commonServiceController;
    }

    @Inject
	public void setAuthenticationServiceController(AuthenticationServiceController authenticationServiceController) {
		this.authenticationServiceController = authenticationServiceController;
	}
    
}


