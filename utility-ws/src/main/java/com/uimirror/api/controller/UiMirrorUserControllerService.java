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

import com.uimirror.api.common.UserServiceController;
import com.uimirror.common.annotation.ProfileExecution;
import com.uimirror.common.authentication.annotation.AuthenticationRequired;
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
public class UiMirrorUserControllerService implements Serializable{

    private static final long serialVersionUID = 4065801835455386561L;
    protected static final Logger LOG = LoggerFactory.getLogger(UiMirrorUserControllerService.class);
    private UriInfo uriInfo;
    
    private UserServiceController userServiceController;

    public UiMirrorUserControllerService() {
    }
    
    @POST
    @Path("usr")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @RolesAllowed({ "ADMIN"})
    @ProfileExecution
    @AuthenticationRequired
    public String registerUser(MultivaluedMap<String, String> request){
    	
    	LOG.info("[START]- Request Recived to edit a user basic details from {}",uriInfo.getBaseUri());
    	
    	Map<String, Object> form = new HashMap<String, Object>(1);
    	
    	form.putAll(CollectionUtils.getInstance().getMapFromMultiMap(request));
    	
    	Map<String, Object> res = userServiceController.modifyUserById(form);

    	LOG.debug("[END]- Request Completed for edit user basic details");
    	
    	return GsonUtility.convertToGsonString(res);
    	
    }
    
    
    @Context
    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @Inject
	public void setUserServiceController(UserServiceController userServiceController) {
		this.userServiceController = userServiceController;
	}
    
}


