package com.uimirror.api.filter;

import static com.uimirror.api.CommonConstants.API_KEY;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.uimirror.api.Session;
import com.uimirror.api.UIMirrorSecurityContext;
import com.uimirror.api.filter.service.UserAuthenticationService;
import com.uimirror.common.util.StringUtility;
/**
 * 
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Filter all incoming requests, look for 
 * possible session information and use that
 * to create and load a SecurityContext to request.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 03-Mar-201411:58:46 PM
 * @modifiedby Jayaram
 * @modifiedon 03-Mar-201411:58:46 PM
 * ***********************************************************************
 */
//@PreMatching   // let spring manage the lifecycle
@Provider    // register as jersey's provider
public class SecurityContextFilter implements ContainerRequestFilter{

    protected static final Logger LOG = LoggerFactory.getLogger(SecurityContextFilter.class);
    
    @Autowired
    private UserAuthenticationService userAuthenticationService;
    
    public SecurityContextFilter() {
    }


	@Override
	public void filter(final ContainerRequestContext request) throws IOException {

		// Get session id from request header
		final String apiKey = request.getHeaderString(API_KEY);
		Session session = null;
		if (!StringUtility.checkEmptyString(apiKey)) {
			// Get the session details from the data base or cache
			session = userAuthenticationService.getUserSessionByAPIKey(apiKey);
		}
		request.setSecurityContext(new UIMirrorSecurityContext(session));

	}

}
