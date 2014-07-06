/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.util
 * @createdOn 10-Mar-20142:02:08 am
 * @modifiedby Jayaram
 * @modifiedon 10-Mar-20142:02:08 am
 * ***********************************************************************
 */

package com.uimirror.common.util;

import static com.uimirror.common.CommonConstants.MSG;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path.Node;

import org.springframework.util.Assert;

import com.uimirror.common.CommonConstants;
import com.uimirror.common.bean.DroolsDto;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : is responsible to build the response map.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 10-Mar-20142:02:08 am
 * @modifiedby Jayaram
 * @modifiedon 10-Mar-20142:02:08 am
 * ***********************************************************************
 */

public class ResponseHelper {

    public ResponseHelper() {
    }

    /**
     * <p>Builds the response map that will be sent over network in compression.
     * Map<String,Object>
     *
     */
    public static Map<String, Object> buildResponseMap(final String resCode, final Object resMsg){
    	
    	Assert.hasText(resCode, "Response Code shouldn't be empty.");
    	Assert.notNull(resMsg, "Response Message Can't be null.");
    	Map<String, Object> res = new HashMap<String, Object>(50);
    	res.put(CommonConstants.RESPONSE_CODE, resCode);
    	res.put(MSG, resMsg);
    	return res;
    }

    /**
     * <p>Builds the response map that will be sent over network in compression .
     * Map<String,Object>
     *
     */
    public static Map<String, Object> buildResponseMap(final String resCode, final String block, final Object resMsg){
    	Assert.hasText(resCode, "Response Code shouldn't be empty.");
    	Assert.hasText(block, "Response Block shouldn't be empty.");
    	Assert.notNull(resMsg, "Response Message Can't be null.");
    	Map<String, Object> res = new HashMap<String, Object>(50);
    	res.put(CommonConstants.RESPONSE_CODE, resCode);
    	res.put(block, resMsg);
    	
    	return res;
    }
    
    /**
     * <p>add the response map that will be sent over network in compression .
     * Map<String,Object>
     *
     */
    public static Map<String, Object> editResponseMap(Map<String, Object> data, final String block, final Object resMsg){
    	Assert.hasText(block, "Response Block shouldn't be empty.");
    	Assert.notNull(resMsg, "Response Message Can't be null.");
    	Assert.notNull(data, "Map Object Can't be null.");
    	data.put(block, resMsg);
    	return data;
    }
    
    /**
     * <p>Build the response message for the invalid fileds.
     * it has invalid key, msg and suggestions.
     * @param violations
     * @return {@link Object} which is a map of type {@code Map<String,String>}
     *
     */
    //TODO work on to format the message properly like abc, xyz and pqr
    @SuppressWarnings("unchecked")
	public static Map<String, Object> buildValidationFaildResponse(final Object violations){
    	Assert.notNull(violations, "Input Object can't be null");
    	final String INVALID = "Invalid ";
    	Set<ConstraintViolation<? extends DroolsDto>> errors = null;
    	if(violations instanceof Set){
    		errors = (Set<ConstraintViolation<? extends DroolsDto>>) violations;
    	}else{
    		throw new IllegalArgumentException("Validation message type cast is not supported");
    	}
	
    	StringBuilder invalidKey = new StringBuilder(32);
    	StringBuilder invalidMsg = new StringBuilder(32);
    	StringBuilder suggestions = new StringBuilder(32);
	
    	String SEPRATOR = CommonConstants.EMPTY_STRING;
    	int size = errors.size();
    	int counter = 0;

    	for(ConstraintViolation<? extends DroolsDto> violation : errors){
    		if(counter > 0 && counter+1 == size){
    			SEPRATOR = CommonConstants.FIELD_AND_SEP;
    		}else if(counter > 0){
    			SEPRATOR = CommonConstants.FIELD_COMMA_SEP;
    		}
		
    		Iterator<Node> nodes = violation.getPropertyPath().iterator();
    		invalidMsg.append(SEPRATOR);
    		invalidMsg.append(violation.getMessage());
    		while(nodes.hasNext()){
    			Node node = nodes.next();
    			invalidKey.append(SEPRATOR);
    			invalidKey.append(node.getName());
    			while(nodes.hasNext()){
    				node = nodes.next();
    				suggestions.append(SEPRATOR);
    				suggestions.append(node.getName());
    				break;
    			}
    		}
    		counter++;

    	}
    	Map<String, Object> resMap = new HashMap<String, Object>(3);
    	resMap.put(CommonConstants.VALIDATION_INVLDKEY, invalidKey.toString());
    	resMap.put(CommonConstants.VALIDATION_INVLDMSG, INVALID+invalidMsg.toString());
    	resMap.put(CommonConstants.VALIDATION_INVLDSUG, suggestions.toString());
    	resMap.put(CommonConstants.RESPONSE_CODE, CommonConstants.VALIDATION_FAILD_RS_CD);
    	return resMap;
    }
    
    /**
     * <p>This will build map for the validation failed response.
     * 
     * @return
     */
    public static Map<String, Object> buildValidationFaildResponse(){
    	Map<String, Object> resMap = new HashMap<String, Object>(8);
    	resMap.put(CommonConstants.VALIDATION_INVLDMSG, CommonConstants.INVALID_DATA_RS_MSG);
    	resMap.put(CommonConstants.RESPONSE_CODE, CommonConstants.VALIDATION_FAILD_RS_CD);
    	return resMap;
    }

}


