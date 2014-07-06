/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.validation
 * @createdOn 07-Mar-20142:13:58 AM
 * @modifiedby Jayaram
 * @modifiedon 07-Mar-20142:13:58 AM
 * ***********************************************************************
 */

package com.uimirror.common.bean.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : $
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 07-Mar-20142:13:58 AM
 * @modifiedby Jayaram
 * @modifiedon 07-Mar-20142:13:58 AM
 * ***********************************************************************
 */

public class Errors {

    private final List<Error> errors = Collections.synchronizedList(new ArrayList<Error>());
    
    public Errors(){
    	
    }
    
    public Errors getInstance(){
    	return new Errors(); 
    }

    public Collection<Error> getErrors() {
    	return Collections.unmodifiableCollection(errors);
    }
    
    public void addError(Object target, String field, String message) {
    	this.errors.add(new Error(target, field, message));
    }

    public void addError(Object target, String field, String message, String suggestion) {
    	this.errors.add(new Error(target, field, message, suggestion));
    }

    public boolean hasErrors() {
    	if (this.errors.size() > 0) {
    		return true;
    	}
    	return false;
    }
}
