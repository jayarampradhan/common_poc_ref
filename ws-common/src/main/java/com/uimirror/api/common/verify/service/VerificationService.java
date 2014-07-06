package com.uimirror.api.common.verify.service;

import java.util.Map;

import com.uimirror.api.common.bean.VerifyBean;
import com.uimirror.api.common.exception.CommonApiSystemException;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : this will help for the verification process 
 * complete.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 15-Mar-2014 12:09:49 AM
 * @modifiedby Jayaram
 * @modifiedon 15-Mar-2014 12:09:49 AM
 * ***********************************************************************
 */
public interface VerificationService {
	
	/**
	 * <p>Will verify the user provided data with the temp data,
	 * if matched, it will create a real user into main table and delete 
	 * the temp record.
	 * <p>Steps involved on this are
     * <ol>
     * <li>validate the bean with rule engine</li>
     * <li>finally persist into DB</li>
     * <li>delete temp record aschynly</li>
     * <li>return profile id for the first completion process.</li>
     * </ol>
	 * @param verifyFormBean
	 * @return
	 * @throws CommonApiSystemException 
	 */
	public Map<String, Object> doVerify(VerifyBean verifyFormBean) throws CommonApiSystemException;

}
