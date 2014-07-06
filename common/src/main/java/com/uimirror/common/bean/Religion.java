/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.bean
 * @createdOn 12-Mar-20141:59:08 am
 * @modifiedby Jayaram
 * @modifiedon 12-Mar-20141:59:08 am
 * ***********************************************************************
 */

package com.uimirror.common.bean;


/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : This will hold the different religions 
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 12-Mar-20141:59:08 am
 * @modifiedby Jayaram
 * @modifiedon 12-Mar-20141:59:08 am
 * ***********************************************************************
 */

public enum Religion {

    HINDU("Hindu"), 
    MUSLIM("Muslim"), 
    CHIRSTIAN("Chirstian");
    
    private String value;
 
    Religion(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }

    @Override
    public String toString() {
	return this.getValue();
    } 

    public static Religion getEnum(String value) {
	if(value == null)
	    throw new IllegalArgumentException();
	for(Religion v : values())
	    if(value.equalsIgnoreCase(v.getValue())) return v;
	throw new IllegalArgumentException();
    }
}


