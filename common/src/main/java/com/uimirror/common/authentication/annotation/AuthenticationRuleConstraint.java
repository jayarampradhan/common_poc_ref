/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.annotation
 * @createdOn 12-Mar-20141:00:06 am
 * @modifiedby Jayaram
 * @modifiedon 12-Mar-20141:00:06 am
 * ***********************************************************************
 */

package com.uimirror.common.authentication.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.uimirror.common.validation.AuthenticationRulesConstraintValidator;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : annotation to use for login validation
 * such as token, email, password etc.
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 16-Mar-20141:00:06 am
 * @modifiedby Jayaram
 * @modifiedon 16-Mar-20141:00:06 am
 * ***********************************************************************
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=AuthenticationRulesConstraintValidator.class)
public @interface AuthenticationRuleConstraint {
    
    String message() default "Authentication rule validation failed.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}


