/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.register.service
 * @createdOn 09-Mar-20148:19:06 pm
 * @modifiedby Jayaram
 * @modifiedon 09-Mar-20148:19:06 pm
 * ***********************************************************************
 */

package com.uimirror.api.common.register.service;

import java.util.Map;

import com.uimirror.api.common.bean.RegisterBean;
import com.uimirror.api.common.bean.ResendTokenEmailBean;
import com.uimirror.api.common.exception.CommonApiSystemException;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : contains process of registering the user.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 09-Mar-20148:19:06 pm
 * @modifiedby Jayaram
 * @modifiedon 09-Mar-20148:19:06 pm
 * ***********************************************************************
 */

public interface RegisterationService {
    
    /**
     * 
     * <p>registerUser: will register the user in temp memory and return back with
     * verification code
     * <p>Steps involved on this are
     * <ol>
     * <li>validate the bean with rule engine</li>
     * <li>validate the email id for already exist or not</li>
     * <li>finally persist into DB</li>
     * <li>Send a welcome and verification mail aschynly</li>
     * <li>return with verification code and temp profile id.</li>
     * </ol>
     * @param registerFormBean {@code RegisterFormBean}
     * @return 
     * @throws CommonApiSystemException 
     *
     */
    public Map<String, Object> doRegister(RegisterBean registerFormBean) throws CommonApiSystemException;
    
    /**
     * <p>will resend the email to the user's registered email.
     * <p>Steps involved on this are
     * <ol>
     * <li>Validate the bean with rule engine if it is the same user or not</li>
     * <li>Generate new token</li>
     * <li>Update new token into DB</li>
     * <li>Send Email</li>
     * </ol>
     * @param rMailBean
     * @return
     * @throws CommonApiSystemException
     */
    public Map<String, Object> reSendVerifyLinkMail(ResendTokenEmailBean rMailBean) throws CommonApiSystemException;

}


