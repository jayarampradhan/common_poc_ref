/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.bean
 * @createdOn 06-Mar-20142:30:30 AM
 * @modifiedby Jayaram
 * @modifiedon 06-Mar-20142:30:30 AM
 * ***********************************************************************
 */

package com.uimirror.api.common.bean;
 
import java.io.Serializable;
import java.util.Map;

import com.uimirror.api.common.annotation.RegisterationRuleConstraint;
import com.uimirror.common.bean.DroolsDto;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : holds the property of a user like fName
 * ,lName, email, password etc.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 06-Mar-20142:30:30 AM
 * @modifiedby Jayaram
 * @modifiedon 06-Mar-20142:30:30 AM
 * ***********************************************************************
 */
@RegisterationRuleConstraint
public class RegisterBean extends DroolsDto implements Serializable{

	private static final long serialVersionUID = -5600730023605134642L;
	
    public RegisterBean(Map<String, Object> fact) {
		super(fact);
	}

}


