/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.annotation
 * @createdOn 30-Mar-2014 1:33:51 AM
 * @modifiedby Jayaram
 * @modifiedon 30-Mar-2014 1:33:51 AM
 * ***********************************************************************
 */
package com.uimirror.api.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.uimirror.api.common.validation.ResendTokenEmailRulesConstraintValidator;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : annotation for validating user details before 
 * re-sending email link token.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 30-Mar-2014 1:33:51 AM
 * @modifiedby Jayaram
 * @modifiedon 30-Mar-2014 1:33:51 AM
 * ***********************************************************************
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=ResendTokenEmailRulesConstraintValidator.class)
public @interface ResendTokenEmailRuleConstraint {
	String message() default "Resend Email Token rule validation failed.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
