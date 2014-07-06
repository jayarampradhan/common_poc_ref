/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common
 * @createdOn 20-Mar-2014 12:47:13 AM
 * @modifiedby Jayaram
 * @modifiedon 20-Mar-2014 12:47:13 AM
 * ***********************************************************************
 */
package com.uimirror.api.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uimirror.api.common.exception.CommonApiSystemException;
import com.uimirror.api.common.user.service.UserService;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.util.ResponseHelper;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : will update/ edit profile various profile 
 * details such as about me, quotation, current city etc.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 20-Mar-2014 12:47:13 AM
 * @modifiedby Jayaram
 * @modifiedon 20-Mar-2014 12:47:13 AM
 * ***********************************************************************
 */
@Component
public class UserServiceController {
	
	protected static final Logger LOG = LoggerFactory.getLogger(UserServiceController.class);
	
	@Autowired
	private UserService userService;

	public UserServiceController() {
		super();
	}
	
	/**
	 * <p>This will edit/update/delete about me section of the user.
	 * <p>This will first validate and save type of privacy.
	 * @param form
	 * @return
	 */
	public Map<String, Object> modifyUserById(Map<String, Object> request){
		
		LOG.info("[START]- User Profile edit about user is intiated.");
		
		try{
			
			userService.modifyUserById(request);
			//No Error then return to caller saying sucess
			return ResponseHelper.buildResponseMap(CommonConstants.SUCESS_RESPONSE_CD, CommonConstants.SUCESS_RESPONSE_MSG);
			
		}catch(Exception e){
			
			if(e instanceof IllegalArgumentException){
				return ResponseHelper.buildResponseMap(CommonConstants.INVALID_DATA_RS_CD, CommonConstants.INVALID_DATA_RS_MSG); 
			}else if(e instanceof CommonApiSystemException){
				return ResponseHelper.buildResponseMap(CommonConstants.INTERNAL_ERR_RS_CD, CommonConstants.INTERNAL_ERR_RS_MSG);
			}else{
				LOG.error("[EXCEPTION]- During processing of modification {}.",e);
				return ResponseHelper.buildResponseMap(CommonConstants.INTERNAL_ERR_RS_CD, CommonConstants.INTERNAL_ERR_RS_MSG);
			}
			
		}finally{
			
			LOG.info("[END]- User Profile edit about user is Completed.");
		}
		
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
