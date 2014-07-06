/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.base
 * @createdOn 26-Mar-2014 6:32:46 PM
 * @modifiedby Jayaram
 * @modifiedon 26-Mar-2014 6:32:46 PM
 * ***********************************************************************
 */
package com.uimirror.api.common.base;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.uimirror.common.CommonConstants;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : This will load the external file specified into memory.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 26-Mar-2014 6:32:46 PM
 * @modifiedby Jayaram
 * @modifiedon 26-Mar-2014 6:32:46 PM
 * ***********************************************************************
 */
public class LoadExternalJson {

	protected static final Logger LOG = LoggerFactory.getLogger(LoadExternalJson.class);
	
	/**
	 * <p>Load test data for unit testing.
	 * @param fullPath
	 * @param clazz
	 * @return
	 */
	public static Object loadData(String fullPath, Class<? extends Object> clazz){
    	Gson gson = new Gson();
		return gson.fromJson(loadFile(fullPath), clazz);
	}
	
	/**
	 * <p>Loads file from external source.
	 * @param fullPath
	 * @return
	 */
	private static String loadFile(String fullPath){
		StringWriter wrtr = new StringWriter();
    	try {
			IOUtils.copy(LoadExternalJson.class.getClassLoader().getResourceAsStream(fullPath), wrtr, CommonConstants.UTF_8);
		} catch (IOException e) {
			LOG.error("File specified {}, not found with exception {}", fullPath, e);
		}
    	return wrtr.toString();
	}
}
