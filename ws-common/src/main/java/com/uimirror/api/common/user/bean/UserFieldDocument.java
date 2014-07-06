/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.user.bean
 * @createdOn 10-Apr-2014 1:00:45 PM
 * @modifiedby Jayaram
 * @modifiedon 10-Apr-2014 1:00:45 PM
 * ***********************************************************************
 */
package com.uimirror.api.common.user.bean;

import java.util.List;

import com.uimirror.common.bean.DroolsListDto;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : $.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 10-Apr-2014 1:00:45 PM
 * @modifiedby Jayaram
 * @modifiedon 10-Apr-2014 1:00:45 PM
 * ***********************************************************************
 */
public class UserFieldDocument extends DroolsListDto {

	private static final long serialVersionUID = 2368180992418702691L;

	public UserFieldDocument(List<String> facts) {
		super(facts);
	}

}
