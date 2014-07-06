/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.bean
 * @createdOn 17-Mar-2014 9:18:41 PM
 * @modifiedby Jayaram
 * @modifiedon 17-Mar-2014 9:18:41 PM
 * ***********************************************************************
 */
package com.uimirror.common.authentication;

import java.util.Map;

import com.uimirror.common.authentication.annotation.AuthenticationRuleConstraint;
import com.uimirror.common.bean.DroolsDto;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : holds the property for login related 
 * operations.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 17-Mar-2014 9:18:41 PM
 * @modifiedby Jayaram
 * @modifiedon 17-Mar-2014 9:18:41 PM
 * ***********************************************************************
 */
@AuthenticationRuleConstraint
public class AuthenticationBean extends DroolsDto{

	private static final long serialVersionUID = -2859075613200932030L;

	public AuthenticationBean(Map<String, Object> formData) {
		super(formData);
	}
	
}
