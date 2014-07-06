/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api
 * @createdOn 04-Mar-201411:39:18 PM
 * @modifiedby Jayaram
 * @modifiedon 04-Mar-201411:39:18 PM
 * ***********************************************************************
 */

package com.uimirror.api;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : This will hold the user role for this 
 * uimirror API.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 04-Mar-201411:39:18 PM
 * @modifiedby Jayaram
 * @modifiedon 04-Mar-201411:39:18 PM
 * ***********************************************************************
 */

public enum Role {
    
    ADMIN("1"), 
    PUBLIC("2"), 
    Contributor("3");
    
    private String value;
 
    Role(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }

    @Override
    public String toString() {
	return this.getValue();
    } 

    public static Role getEnum(String value) {
	if(value == null)
	    throw new IllegalArgumentException();
	for(Role v : values())
	    if(value.equalsIgnoreCase(v.getValue())) return v;
	throw new IllegalArgumentException();
    }
    

}


