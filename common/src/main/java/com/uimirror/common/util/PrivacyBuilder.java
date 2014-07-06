/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.util
 * @createdOn 28-Mar-2014 12:01:25 AM
 * @modifiedby Jayaram
 * @modifiedon 28-Mar-2014 12:01:25 AM
 * ***********************************************************************
 */
package com.uimirror.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.uimirror.common.CommonConstants;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Builds the privacy map.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 28-Mar-2014 12:01:25 AM
 * @modifiedby Jayaram
 * @modifiedon 28-Mar-2014 12:01:25 AM
 * ***********************************************************************
 */
public class PrivacyBuilder {

	/**
	 * <p>This will build the privacy map for any field.
	 * @param privacyName
	 * @param shareWith
	 * @param notshareWith
	 * @return
	 */
	public static Map<String, Object> buildPrivacy(final String privacyName, final List<Map<String,String>> shareWith, final List<Map<String,String>> notshareWith){
		
		Map<String, Object> privacy = new HashMap<String, Object>(4);
		
		if(StringUtility.checkEmptyString(privacyName)){
			return null;
		}else{
			privacy.put(CommonConstants.FL_PRIVACY, privacyName);
		}
    	Map<String, Object> customMap = new HashMap<String, Object>(4);
    	
    	if(shareWith != null){
    		customMap.put(CommonConstants.FL_PRIVACY_SHARE_WITH, shareWith);
    	}
    	if(notshareWith != null){
    		customMap.put(CommonConstants.FL_PRIVACY_SHARE_NOT_WITH, notshareWith);
    	}
    	
    	if(customMap.size() > 0){
    		privacy.put(CommonConstants.FL_PRIVACY_CUSTOM, customMap);
    	}
		
		return privacy;
	}
}
