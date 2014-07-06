/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.bean
 * @createdOn 30-Mar-2014 1:04:23 AM
 * @modifiedby Jayaram
 * @modifiedon 30-Mar-2014 1:04:23 AM
 * ***********************************************************************
 */
package com.uimirror.api.common.bean;

import java.io.Serializable;
import java.util.Map;

import com.uimirror.api.common.annotation.ResendTokenEmailRuleConstraint;
import com.uimirror.common.bean.DroolsDto;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : holds the property of a user like profile id
 * ,token, email etc..
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 30-Mar-2014 1:04:23 AM
 * @modifiedby Jayaram
 * @modifiedon 30-Mar-2014 1:04:23 AM
 * ***********************************************************************
 */
@ResendTokenEmailRuleConstraint
public class ResendTokenEmailBean extends DroolsDto implements Serializable{

	private static final long serialVersionUID = -3893445548717079454L;
	
	public ResendTokenEmailBean(Map<String, Object> facts) {
		super(facts);
	}

}
