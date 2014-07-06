/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.authentication.annotation
 * @createdOn 19-Mar-2014 1:04:03 PM
 * @modifiedby Jayaram
 * @modifiedon 19-Mar-2014 1:04:03 PM
 * ***********************************************************************
 */
package com.uimirror.common.authentication.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : annotation will be for specifying if any 
 * method that require login for this action, it uses cookie based login management
 * , it will check the method param with cookie login details such as login id
 * , login token, profile id, browser mets, client IP, remember me flag, las login issue date
 * .
 * <p>It will check if the user IP is different from previous then it will disable previous one 
 * and regenerate new login. if a user is not logged in it will ask user to re-login
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 19-Mar-2014 1:04:03 PM
 * @modifiedby Jayaram
 * @modifiedon 19-Mar-2014 1:04:03 PM
 * ***********************************************************************
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface AuthenticationRequired {

}
