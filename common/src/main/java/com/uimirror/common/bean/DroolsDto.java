/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.bean
 * @createdOn 14-Mar-2014 5:06:33 PM
 * @modifiedby Jayaram
 * @modifiedon 14-Mar-2014 5:06:33 PM
 * ***********************************************************************
 */
package com.uimirror.common.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : This will hold the generic map which needs 
 * to be validated.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 14-Mar-2014 5:06:33 PM
 * @modifiedby Jayaram
 * @modifiedon 14-Mar-2014 5:06:33 PM
 * ***********************************************************************
 */
public class DroolsDto implements Serializable{

	private static final long serialVersionUID = -4385413967059982682L;
	protected final Map<String, Object> facts;
	protected Map<String, Object> states;
	
	public DroolsDto(Map<String, Object> facts){
		this.facts = facts;
		this.states = new HashMap<String, Object>();
	}
	
	public void updateStates(String key, Object val){
		this.states.put(key, val);
	}

	public Map<String, Object> getStates() {
		return states;
	}

	public void setStates(Map<String, Object> states) {
		this.states = states;
	}

	public Map<String, Object> getFacts() {
		return facts;
	}
	
	public Object getFact(String key){
		return facts.get(key);
	}
	
}
