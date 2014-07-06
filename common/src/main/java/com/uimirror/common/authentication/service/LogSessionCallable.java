/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.authentication.service
 * @createdOn 26-Mar-2014 12:53:03 AM
 * @modifiedby Jayaram
 * @modifiedon 26-Mar-2014 12:53:03 AM
 * ***********************************************************************
 */
package com.uimirror.common.authentication.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uimirror.common.CommonConstants;
import com.uimirror.common.authentication.dao.AuthenticationDao;
import com.uimirror.common.meta.dao.DeviceDao;
import com.uimirror.common.util.CollectionUtils;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Callable service to store the session
 *  information in background.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 26-Mar-2014 12:53:03 AM
 * @modifiedby Jayaram
 * @modifiedon 26-Mar-2014 12:53:03 AM
 * ***********************************************************************
 */
public class LogSessionCallable implements Callable<Object> {
	
	protected static final Logger LOG = LoggerFactory.getLogger(LogSessionCallable.class);
	
	private final AuthenticationDao authenticationDao;
	
	private final DeviceDao deviceDao;
	
	private final Map<String, Object> session;
	
	public LogSessionCallable(final AuthenticationDao authenticationDao, final DeviceDao deviceDao, final Map<String, Object> session){
		this.authenticationDao = authenticationDao;
		this.session = session;
		this.deviceDao = deviceDao;
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Object call() throws Exception {
		
		LOG.info("[START]- Stroing User session");
		try{
			if(!CollectionUtils.sizeIsEmpty(session)){
				
				if(deviceDao.getDeviceById(String.valueOf(session.get(CommonConstants.FL_DEVICE))) == null){
					deviceDao.saveDevice(buildDevice(session));
				}
				session.remove(CommonConstants.ID_DEVICE);
				authenticationDao.createLoginSession(session);
				
			}else{
				LOG.error("Session can't be stored.");
			}
			
		}catch(Exception e){
			LOG.error("[EXCEPTION]-Session can't be stored {}.",e);
		}finally{
			LOG.info("[END]- Stroing User session");
		}
		
		return null;
	}

	/**
	 * <p>This will create device map to be saved into DB 
	 * @param data
	 * @return
	 */
	private Map<String, Object> buildDevice(Map<String, Object> data) {
		Map<String, Object> device = new HashMap<String, Object>(2);
		device.put(CommonConstants._ID, data.get(CommonConstants.FL_DEVICE));
		device.put(CommonConstants.FL_DEVICE, data.get(CommonConstants.ID_DEVICE));
		return device;
	}

}
