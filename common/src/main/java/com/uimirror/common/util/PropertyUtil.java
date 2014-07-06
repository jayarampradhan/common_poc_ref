/**
 * 
 */
package com.uimirror.common.util;

import java.io.Serializable;
import java.util.Properties;

/**
 * <p>This class will help to read 
 * the property from a external property file
 * 
 * @author Jayaram
 * @category Util for property
 * @version 1.0
 */
public final class PropertyUtil implements Serializable {

	private static final long serialVersionUID = -3531432927558428229L;
	
	public PropertyUtil() {
		super();
		
	}
	
	protected static Properties PROPERTIES;
	
	// Cheating spring to set a static property.
	public static Properties createInstance(Properties propertiesArgs) {
		PROPERTIES = propertiesArgs;
		return PROPERTIES;

	}

	
	/**
	 * <p>This will get the property value from the property key specified
	 * 
	 * @param propKey
	 * @return a valid property value
	 * 
	 */
	public static String getProperty(String propKey){
		if(StringUtility.checkEmptyString(propKey)){
			return null;
		}
		return PROPERTIES.getProperty(propKey);
	}
	
	/**
	 * <p>This will set the property on the fly, this will map the property like key value pair
	 * 
	 * @param propKey
	 * @param propValue
	 * 
	 */
	public void setProperty(String propKey, String propValue){
		if(StringUtility.checkEmptyString(propKey)||StringUtility.checkEmptyString(propValue)){
			throw new IllegalArgumentException("Property key and value should not be empty");
		}
		PROPERTIES.setProperty(propKey, propValue);
	}
	
}
