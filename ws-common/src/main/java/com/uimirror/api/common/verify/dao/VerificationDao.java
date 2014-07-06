/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.verify.dao
 * @createdOn 15-Mar-2014 12:46:06 AM
 * @modifiedby Jayaram
 * @modifiedon 15-Mar-2014 12:46:06 AM
 * ***********************************************************************
 */
package com.uimirror.api.common.verify.dao;

import javax.ws.rs.core.MultivaluedMap;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : contains all the JDBC operation for the
 * verification service like persist, query DB etc.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 15-Mar-2014 12:46:06 AM
 * @modifiedby Jayaram
 * @modifiedon 15-Mar-2014 12:46:06 AM
 * ***********************************************************************
 */
public interface VerificationDao {
	
	public MultivaluedMap<String, Object> getTempuserByLinkToken(final String tempProfileId, final String email, final String token);
	
	public MultivaluedMap<String, Object> getTempuserByLink(final String tempProfileId, final String email, final String token);

}
