/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.controller
 * @createdOn 13-Mar-2014 9:54:57 PM
 * @modifiedby Jayaram
 * @modifiedon 13-Mar-2014 9:54:57 PM
 * ***********************************************************************
 */
package com.uimirror.api.controller;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Context Initializer for the jersey 
 * application.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 13-Mar-2014 9:54:57 PM
 * @modifiedby Jayaram
 * @modifiedon 13-Mar-2014 9:54:57 PM
 * ***********************************************************************
 */
public class JerseyResourceInitializer extends ResourceConfig {

	public JerseyResourceInitializer() {
		packages(true, "com.uimirror.api.controller");
		//register(GZIPContentEncodingFilter.class);
		// Register entity-filtering security feature.
        //register(SecurityEntityFilteringFeature.class);
        // Entity Data Filtering feature.
        //register(EntityFilteringFeature.class);
		//register(PoweredByResponseFilter.class);
		//register(RolesAllowedDynamicFeature.class);
		register(JacksonFeature.class);
	}
	
	

}
