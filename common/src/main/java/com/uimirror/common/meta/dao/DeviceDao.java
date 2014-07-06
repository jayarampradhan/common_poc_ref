/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.meta.dao
 * @createdOn 30-Mar-2014 11:05:20 PM
 * @modifiedby Jayaram
 * @modifiedon 30-Mar-2014 11:05:20 PM
 * ***********************************************************************
 */
package com.uimirror.common.meta.dao;

import java.util.Map;

import com.mongodb.MongoException;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Device Dao for all the service 
 * Related to device.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 30-Mar-2014 11:05:20 PM
 * @modifiedby Jayaram
 * @modifiedon 30-Mar-2014 11:05:20 PM
 * ***********************************************************************
 */
public interface DeviceDao {
	
	/**
	 * <p>This will save the device infor into the device meta.
	 * @param device
	 * @throws MongoException
	 */
	public void saveDevice(Map<String, Object> device) throws MongoException;
	
	/**
	 * <p>This will retrieve Device information from Data base by device Id.
	 * @param deviceId
	 * @return
	 * @throws MongoException
	 */
	public Map<String, Object> getDeviceById(final String deviceId) throws MongoException;

}
