package com.uimirror.common.util;

import com.google.gson.Gson;

/**
 * <p>This is the utility class for Gson work
 * <br>Any conversion from GSON To String or GSON
 * Object to Object will be taken care 
 *  
 * @author Jayaram
 *
 */
public class GsonUtility {
    
    private static final Gson gson = new Gson();
    
    /**
     * <p>Check if the object is not null then convert to GSON String
     * else throw exception saying invalid object
     * <p>errorCode: 
     * @param obj
     * @return
     */
    public static String convertToGsonString(final Object obj){
    	return gson.toJson(obj);
    }

}
