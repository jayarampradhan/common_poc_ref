package com.uimirror.api;

import java.security.Principal;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : validates the requester for each incoming
 * request made by any caller.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 03-Mar-201411:49:44 PM
 * @modifiedby Jayaram
 * @modifiedon 03-Mar-201411:49:44 PM
 * ***********************************************************************
 */

public class UIMirrorSecurityContext implements javax.ws.rs.core.SecurityContext {
    
    protected static final Logger LOG = LoggerFactory.getLogger(UIMirrorSecurityContext.class);
    private final Session session;

    public UIMirrorSecurityContext(Session session) {
	this.session = session;
    }

    @Override
    public String getAuthenticationScheme() {
	return SecurityContext.BASIC_AUTH;
    }

    @Override
    public Principal getUserPrincipal() {
	return session.getUser();
    }

    @Override
    public boolean isSecure() {
	return (null != session) ? session.isSecure() : Boolean.FALSE;
    }

    @Override
	public boolean isUserInRole(String role) {

		if (null == session || !session.isActive()) {
			sendAccessDenied();
		}

		try {
			// this user has this role?
			return session.getUser().getRoles().contains(Role.valueOf(role));
		} catch (Exception e) {
			LOG.error("Exception occured while geting the user role details, exception is :{}",e);
			sendAccessDenied();
		}

		return Boolean.FALSE;
	}
    
    /**
     * <p>Send user as access denied.
     */
    private void sendAccessDenied(){
	// Forbidden
        Response denied = Response.status(Response.Status.UNAUTHORIZED).entity("Permission Denied").build();
        throw new WebApplicationException(denied);
    }

}
