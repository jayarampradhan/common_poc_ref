/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.util
 * @createdOn 15-Mar-2014 2:15:23 AM
 * @modifiedby Jayaram
 * @modifiedon 15-Mar-2014 2:15:23 AM
 * ***********************************************************************
 */
package com.uimirror.common.util;

import javax.validation.ConstraintValidatorContext;

import com.uimirror.common.bean.validation.Error;
import com.uimirror.common.bean.validation.Errors;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Builds the validation failed message.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 15-Mar-2014 2:15:23 AM
 * @modifiedby Jayaram
 * @modifiedon 15-Mar-2014 2:15:23 AM
 * ***********************************************************************
 */
public class ValidationMessageBuilder {
	
	private final ConstraintValidatorContext context;
	///Make this constructor so that it will not be visible out side
	private ValidationMessageBuilder(ConstraintValidatorContext context){
		this.context = context;
	}
	
	private ValidationMessageBuilder(){
		this.context = null;
	}
	
	public static ValidationMessageBuilder getInstance(ConstraintValidatorContext context){
		return new ValidationMessageBuilder(context);
	}
	
	public ConstraintValidatorContext buildError(Errors errors){
		this.context.disableDefaultConstraintViolation();
        for (Error error : errors.getErrors()) {
            if(!StringUtility.checkEmptyString(error.getSuggestions())){
            	this.context.buildConstraintViolationWithTemplate(error.getMessage()).addPropertyNode(error.getField()).addPropertyNode(error.getSuggestions()).addConstraintViolation();
            }else{
            	this.context.buildConstraintViolationWithTemplate(error.getMessage()).addPropertyNode(error.getField()).addConstraintViolation();
            }
        }
        return this.context;
	}

}
