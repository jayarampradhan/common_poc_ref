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

package com.uimirror.api.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.uimirror.api.common.validation.VerificationRulesConstraintValidator;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : annotation to use for verification validation
 * such as token, email etc.
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 12-Mar-20141:00:06 am
 * @modifiedby Jayaram
 * @modifiedon 12-Mar-20141:00:06 am
 * ***********************************************************************
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=VerificationRulesConstraintValidator.class)
public @interface VerificationRuleConstraint {
    
    String message() default "Verification rule validation failed.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}


