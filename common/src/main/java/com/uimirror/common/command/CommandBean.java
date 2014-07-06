/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.command
 * @createdOn 10-Apr-2014 2:00:20 PM
 * @modifiedby Jayaram
 * @modifiedon 10-Apr-2014 2:00:20 PM
 * ***********************************************************************
 */
package com.uimirror.common.command;

import java.util.List;

import com.uimirror.common.bean.DroolsListDto;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Command bean will hold command like set, 
 * unset etc in list.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 10-Apr-2014 2:00:20 PM
 * @modifiedby Jayaram
 * @modifiedon 10-Apr-2014 2:00:20 PM
 * ***********************************************************************
 */
public class CommandBean extends DroolsListDto {

	private static final long serialVersionUID = 2907209067484906144L;

	public CommandBean(List<String> facts) {
		super(facts);
	}

}
