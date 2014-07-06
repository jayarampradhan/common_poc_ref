/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.annotation
 * @createdOn 07-Mar-20142:00:53 AM
 * @modifiedby Jayaram
 * @modifiedon 07-Mar-20142:00:53 AM
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

import com.uimirror.api.common.validation.RegisterationRulesConstraintValidator;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : annotation to use for registration validation
 * such as name, age etc.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 07-Mar-20142:00:53 AM
 * @modifiedby Jayaram
 * @modifiedon 07-Mar-20142:00:53 AM
 * ***********************************************************************
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=RegisterationRulesConstraintValidator.class)
public @interface RegisterationRuleConstraint {
    
    String message() default "Registeration rule validation failed.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}


