/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.bean
 * @createdOn 10-Apr-2014 12:45:03 PM
 * @modifiedby Jayaram
 * @modifiedon 10-Apr-2014 12:45:03 PM
 * ***********************************************************************
 */
package com.uimirror.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : $.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 10-Apr-2014 12:45:03 PM
 * @modifiedby Jayaram
 * @modifiedon 10-Apr-2014 12:45:03 PM
 * ***********************************************************************
 */
public class DroolsListDto implements Serializable {

	private static final long serialVersionUID = 7336933395891722848L;
	protected final List<String> facts;
	protected List<String> states;
	
	public DroolsListDto(List<String> facts) {
		Assert.notEmpty(facts, "Facts Can't empty.");
		this.facts = facts;
		this.states = new ArrayList<>(facts != null ? facts.size() : 0);
	}
	
	public void addToState(String obj){
		this.states.add(obj);
	}

	public List<String> getFacts() {
		return facts;
	}

	public List<String> getStates() {
		return states;
	}

	public void setStates(List<String> states) {
		this.states = states;
	}
	
	public String getFact(int index){
		return this.facts.get(index);
	}

}
