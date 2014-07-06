/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.util
 * @createdOn 28-Mar-2014 12:08:26 AM
 * @modifiedby Jayaram
 * @modifiedon 28-Mar-2014 12:08:26 AM
 * ***********************************************************************
 */
package com.uimirror.common.util;

import java.util.HashMap;
import java.util.Map;

import com.uimirror.common.CommonConstants;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Contains all utility methods for the profile 
 * builder activities.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 28-Mar-2014 12:08:26 AM
 * @modifiedby Jayaram
 * @modifiedon 28-Mar-2014 12:08:26 AM
 * ***********************************************************************
 */
public class ProfileDataBuilder {
	
	/**
	 * <p>This Builds the name map.
	 * @param fName
	 * @param lName
	 * @return
	 */
	public static Map<String, Object> buildName(Object fName, Object lName){
		Map<String, Object> nameMap = new HashMap<String, Object>(30);
    	nameMap.put(CommonConstants.FL_FIRST_NAME, fName);
    	nameMap.put(CommonConstants.FL_LAST_NAME, lName);
    	return nameMap;
	}
	
	/**
	 * <p>This will build date Of Birth Map
	 * <p>Assumes DOB is in DD-MM-YYYY format
	 * <p>user has date of birth, which consist of date, month, year, which may be public or may not
	 * @param date
	 * @param month
	 * @param year
	 * @param dobPrivacyMap
	 * @param yearPrivacyMap
	 * @return
	 */
	public static Map<String, Object> buildDateOfBirth(final Object dob, final Map<String, Object> dobPrivacyMap, final Map<String, Object> yearPrivacyMap){
		
		//user has date of birth, which consist of date, month, year, which may be public or may not
    	
		Map<String, Object> dobMap = new HashMap<String, Object>(4);
    	String[] data = ((String)dob).split(CommonConstants.DOB_SEPRATOR);
    	
    	dobMap.put(CommonConstants.FL_DATE, data[0]);
    	dobMap.put(CommonConstants.FL_MONTH, data[1]);
    	dobMap.put(CommonConstants.FL_YEAR, data[2]);
    	
    	Map<String, Object> dobPrivacy = new HashMap<String, Object>(2);
    	
    	dobPrivacy.put(CommonConstants.FL_BD_DATE_PRIVACY, dobPrivacyMap);
    	dobPrivacy.put(CommonConstants.FL_BD_YEAR_PRIVACY, yearPrivacyMap);
    	
    	dobMap.put(CommonConstants.FL_PRIVACY, dobPrivacy);
		
    	return dobMap;
	}

}
