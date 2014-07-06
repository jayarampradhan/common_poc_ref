/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.user.service
 * @createdOn 08-Apr-2014 2:35:36 PM
 * @modifiedby Jayaram
 * @modifiedon 08-Apr-2014 2:35:36 PM
 * ***********************************************************************
 */
package com.uimirror.api.common.user.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.mongodb.MongoException;
import com.uimirror.api.common.exception.CommonApiSystemException;
import com.uimirror.api.common.user.bean.UserFieldDocument;
import com.uimirror.api.common.user.dao.UserDao;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.command.CommandBean;
import com.uimirror.common.dao.util.CommonDaoHelper;
import com.uimirror.common.exception.CommonErrorConstants;
import com.uimirror.common.transformer.TransformerFactory;
import com.uimirror.common.util.CollectionUtils;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : implementation of {@link UserService}.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 08-Apr-2014 2:35:36 PM
 * @modifiedby Jayaram
 * @modifiedon 08-Apr-2014 2:35:36 PM
 * ***********************************************************************
 */
public class UserServiceImpl implements UserService {
	
	protected static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static final String USR_DOC_TRANFORMER = "usrDocTransformer";
	private static final String COMMAND_TRANSFORMER = "cmdStateTransformer";

	@Autowired
	private UserDao userDao;
	
	@Autowired 
    private TransformerFactory transformerFactory;
			
	/* (non-Javadoc)
	 * @see com.uimirror.api.common.user.service.UserService#modifyUserById(java.util.Map)
	 */
	@Override
	public void modifyUserById(Map<String, Object> request) throws CommonApiSystemException {
		
		LOG.info("[START]- User Profile edit about user.");
		
		//First Check if profile ID present in the request
		isProfileIdPresent(request);
		
		//get fields
		//Validate Field Document key property path
		UserFieldDocument usrFieldDocument = new UserFieldDocument(CollectionUtils.decodeToListOfString(request.get(CommonConstants.FIELDS), "Field Can't be empty"));
		transformerFactory.getTransformer(USR_DOC_TRANFORMER).doTransform(usrFieldDocument);
		List<String> fieldStates = usrFieldDocument.getStates();
		Assert.notEmpty(fieldStates, "No Valid Fields To execute against.");
		
		//get commands
		//validate Command key
		CommandBean commandBean = new CommandBean(CollectionUtils.decodeToListOfString(request.get(CommonConstants.COMMANDS), "Commands can't be empty"));
		transformerFactory.getTransformer(COMMAND_TRANSFORMER).doTransform(commandBean);
		List<String> commandStates = commandBean.getStates();
		Assert.notEmpty(commandStates, "No Valid Command To execute.");
		
		//Build the lists from the parameter
		//Get Values and validate for null
		List<String> values = CollectionUtils.decodeToListOfString(request.get(CommonConstants.VALUES), "Commands can't be empty");
		
		//get Separators		
		List<String> seprators = CollectionUtils.decodeToListOfString(request.get(CommonConstants.COMMAND_SEP));
		
		//Finally decode the update map
		Map<String, Object> updates = CollectionUtils.decodeUpdateMap(commandBean.getStates(), fieldStates, values, seprators);
		
		//Build select query by profile id
		Map<String, Object> selectFields = CommonDaoHelper.getMapForId((String)request.get(CommonConstants.PRF_ID));
				
		try{
			//Finally Update in data base		
			userDao.modifySingleUser(selectFields, updates);
					
		}catch(MongoException me){
		
			LOG.error("[EXCEPTION]- Updateing user basic details has some error {}", me.getMessage());
			throw new CommonApiSystemException(CommonErrorConstants.COMMON_DB_ERROR , CommonErrorConstants.COMMON_DB_ERROR_CD);
		
		}finally{
			LOG.info("[END]- Modifying User Basci Details completed.");
		}

	}

	/**
	 * <p>This will test if profile id present in the request.
	 * @param request
	 */
	private void isProfileIdPresent(Map<String, Object> request) {
		String prfId = (String)request.get(CommonConstants.PRF_ID);
		if(!NumberUtils.isDigits(prfId)){
			throw new IllegalArgumentException("Profile Id of a user should be numeric.");
		}
		
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
