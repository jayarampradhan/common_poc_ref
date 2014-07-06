/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.user.service
 * @createdOn 08-Apr-2014 2:32:57 PM
 * @modifiedby Jayaram
 * @modifiedon 08-Apr-2014 2:32:57 PM
 * ***********************************************************************
 */
package com.uimirror.api.common.user.service;

import java.util.Map;

import com.uimirror.api.common.exception.CommonApiSystemException;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Service for all the operation in user document.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 08-Apr-2014 2:32:57 PM
 * @modifiedby Jayaram
 * @modifiedon 08-Apr-2014 2:32:57 PM
 * ***********************************************************************
 */
public interface UserService {

	/**
	 * <p>Service will handle for any modification to user basic document.
	 * 
	 * @param request
	 * @throws CommonApiSystemException
	 */
	public void modifyUserById(Map<String, Object> request) throws CommonApiSystemException;
}
