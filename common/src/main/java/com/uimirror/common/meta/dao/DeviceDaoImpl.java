/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.meta.dao
 * @createdOn 30-Mar-2014 11:06:09 PM
 * @modifiedby Jayaram
 * @modifiedon 30-Mar-2014 11:06:09 PM
 * ***********************************************************************
 */
package com.uimirror.common.meta.dao;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.uimirror.common.dao.util.CommonDaoHelper;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Dao Implementation for device meta.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 30-Mar-2014 11:06:09 PM
 * @modifiedby Jayaram
 * @modifiedon 30-Mar-2014 11:06:09 PM
 * ***********************************************************************
 */
public class DeviceDaoImpl implements DeviceDao{
	
	protected static final Logger LOG = LoggerFactory.getLogger(DeviceDaoImpl.class);
	
	@Autowired
	private DBCollection deviceCollection;
	
	/* (non-Javadoc)
	 * @see com.uimirror.common.meta.dao.DeviceDao#saveDevice(java.util.Map)
	 */
	@Override
	public void saveDevice(Map<String, Object> device) throws MongoException {
		LOG.info("[START]- Saving Device Info");
		deviceCollection.save(CommonDaoHelper.getDBObjectFromMap(device));
		LOG.info("[END]- Saving Device Info");
	}

	/* (non-Javadoc)
	 * @see com.uimirror.common.meta.dao.DeviceDao#getDeviceById(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getDeviceById(String deviceId) throws MongoException {
		LOG.info("[START]- Getting the device Info by ID");
		DBObject object = deviceCollection.findOne(deviceId);
		LOG.info("[END]- Getting the device Info by ID");
		return object == null ? null : object.toMap();
	}

	public void setDeviceCollection(DBCollection deviceCollection) {
		this.deviceCollection = deviceCollection;
	}

}
