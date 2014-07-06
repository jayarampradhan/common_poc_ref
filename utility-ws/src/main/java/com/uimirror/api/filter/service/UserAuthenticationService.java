/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.filter.service
 * @createdOn 04-Mar-201412:36:43 AM
 * @modifiedby Jayaram
 * @modifiedon 04-Mar-201412:36:43 AM
 * ***********************************************************************
 */

package com.uimirror.api.filter.service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;
import com.uimirror.api.Session;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Interface for the web service authentication.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 04-Mar-201412:36:43 AM
 * @modifiedby Jayaram
 * @modifiedon 04-Mar-201412:36:43 AM
 * ***********************************************************************
 */

public interface UserAuthenticationService {
   
    @Cacheable(cacheName="com.uimirror.api.API_KEY_CACHE", 
	    keyGenerator = @KeyGenerator (name = "StringCacheKeyGenerator",
	    properties = {
		    @Property( name="useReflection", value="false" ),
		    @Property( name="checkforCycles", value="false" ),
		    @Property( name="includeMethod", value="false" )
	    }
	   )
	)
    public Session getUserSessionByAPIKey(final String apiKey);

}


