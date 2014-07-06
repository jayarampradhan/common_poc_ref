/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.authentication.annotation
 * @createdOn 19-Mar-2014 2:16:31 AM
 * @modifiedby Jayaram
 * @modifiedon 19-Mar-2014 2:16:31 AM
 * ***********************************************************************
 */
package com.uimirror.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : holds the mechanism for the stop watch around 
 * methods to monitor the time it took.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 19-Mar-2014 2:16:31 AM
 * @modifiedby Jayaram
 * @modifiedon 19-Mar-2014 2:16:31 AM
 * ***********************************************************************
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface ProfileExecution {

}
