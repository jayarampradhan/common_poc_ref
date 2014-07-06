/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.bean
 * @createdOn 12-Mar-20141:55:32 am
 * @modifiedby Jayaram
 * @modifiedon 12-Mar-20141:55:32 am
 * ***********************************************************************
 */

package com.uimirror.common.bean;


/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : This will hold the user sex details.
 * such as Male, Female, other
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 12-Mar-20141:55:32 am
 * @modifiedby Jayaram
 * @modifiedon 12-Mar-20141:55:32 am
 * ***********************************************************************
 */

public enum Sex {

    MALE("M"), 
    FEMALE("F"), 
    OTHER("O");
    
    private String value;
 
    Sex(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }

    @Override
    public String toString() {
	return this.getValue();
    } 

    public static Sex getEnum(String value) {
	if(value == null)
	    throw new IllegalArgumentException();
	for(Sex v : values())
	    if(value.equalsIgnoreCase(v.getValue())) return v;
	throw new IllegalArgumentException();
    }
}


