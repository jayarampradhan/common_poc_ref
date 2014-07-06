package com.uimirror.common.util;

import static com.uimirror.common.CommonConstants.NUMBER_0;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : This is the unit test case for MathematicHelper.java
 * @author Gourang
 * @version $
 * @see 
 * @createdOn 09-Apr-2014 11:32:22 pm
 * @modifiedby Gourang
 * @modifiedon 09-Apr-2014 11:32:22 pm
 * ***********************************************************************
 */
public final class MathematicsHelper {

    public MathematicsHelper() {
    }

    /**
     * <p>This will add all strings to equivalent integer
     * 
     * @param args array of string {@link java.lang.String}
     * @return sum of String {@link java.lang.String}
     */
    public static String addStringToInt(String ... args){

    	if(args==null){
    		return null;
    	}
    	int sum = NUMBER_0;
    	
    	for(String arg : args){

    		if(NumberUtils.isDigits(arg)){
    			sum += Integer.parseInt(arg);
    		}else{
    			sum += NUMBER_0;
    		}
    	}
    	return Integer.toString(sum);
    }
    
//    public static int addStringReturnSum(String ... args){
//
//    	if(args==null){
//    		return 0;
//    	}
//    	int sum = NUMBER_0;
//    	
//    	for(String arg : args){
//
//    		if(NumberUtils.isDigits(arg)){
//    			sum += Integer.parseInt(arg);
//    		}else{
//    			sum += NUMBER_0;
//    		}
//    	}
//    	return sum;
//    }

    
    /**
     * <p>This will get the sum from the list of strings,
     * where each string should be a valid integer.
     * @param lst
     * @return
     */
    public static int getSum(List<String> lst){
    	
    	if(CollectionUtils.isEmpty(lst)){
    		return 0;
    	}

    	int sum = NUMBER_0;
    	
    	for(String str : lst){
    	
    		if(NumberUtils.isDigits(str)){
    			sum +=Integer.parseInt(str);
    		}else{
    			sum += NUMBER_0;
    		}
    	}
    	return sum;
    }
}
