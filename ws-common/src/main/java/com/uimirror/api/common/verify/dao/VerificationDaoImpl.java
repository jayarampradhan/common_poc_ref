/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.verify.dao
 * @createdOn 15-Mar-2014 12:57:31 AM
 * @modifiedby Jayaram
 * @modifiedon 15-Mar-2014 12:57:31 AM
 * ***********************************************************************
 */
package com.uimirror.api.common.verify.dao;

import javax.ws.rs.core.MultivaluedMap;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : implementation of {@link VerificationDao}.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 15-Mar-2014 12:57:31 AM
 * @modifiedby Jayaram
 * @modifiedon 15-Mar-2014 12:57:31 AM
 * ***********************************************************************
 */
public class VerificationDaoImpl implements VerificationDao {

	@Override
	public MultivaluedMap<String, Object> getTempuserByLinkToken(final String tempProfileId, final String email, final String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultivaluedMap<String, Object> getTempuserByLink(final String tempProfileId, final String email, final String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
