package com.uimirror.encryption.util;

import java.util.UUID;

import com.uimirror.common.CommonConstants;

public class EncryptionUtil {

    public EncryptionUtil() {
    }
    
    
    /**
     * <p>This will generate the random ID by given 
     * length
     * <p>length should be less than 16 bit.
     * @param length
     * @return
     */
    public static String getRandomId(int length){
    	if(length >= 16){
    		throw new IllegalArgumentException("random key length should be below 16");
    	}
    	String token = CommonConstants.EMPTY_STRING;
    	do{
    		token =  UUID.randomUUID().toString().replaceAll("-", "");
    	}while(token.length() <= length);
	
    	int tokenLength = token.length();
    	int startPos = tokenLength-length;
    	return token.substring(startPos, tokenLength);
    }

}
