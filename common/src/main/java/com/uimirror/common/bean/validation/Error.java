/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.bean.validation
 * @createdOn 15-Mar-2014 2:23:03 AM
 * @modifiedby Jayaram
 * @modifiedon 15-Mar-2014 2:23:03 AM
 * ***********************************************************************
 */
package com.uimirror.common.bean.validation;

import com.uimirror.common.CommonConstants;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : holds the error bean.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 15-Mar-2014 2:23:03 AM
 * @modifiedby Jayaram
 * @modifiedon 15-Mar-2014 2:23:03 AM
 * ***********************************************************************
 */
public class Error {
	
	private final Object target;
    private final String field;
    private final String message;
    private final String suggestions;

    public Error(Object target, String field, String message, String suggestions) {
    	super();
    	this.target = target;
    	this.field = field;
    	this.message = message;
    	this.suggestions = suggestions;
    }
    
    public Error(Object target, String field, String message) {
    	super();
    	this.target = target;
    	this.field = field;
    	this.message = message;
    	this.suggestions = CommonConstants.EMPTY_STRING;
    }

    public Object getTarget() {
	return target;
    }

    public String getField() {
	return field;
    }

    public String getMessage() {
	return message;
    }

    public String getSuggestions() {
        return suggestions;
    }

}
