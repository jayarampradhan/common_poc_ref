package com.uimirror.common.transformer;

/**
 * 
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Transformer Factory for managing transformer 
 * for each type.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 12-Apr-2014 2:03:51 AM
 * @modifiedby Jayaram
 * @modifiedon 12-Apr-2014 2:03:51 AM
 * ***********************************************************************
 */
public interface TransformerFactory {

	/**
	 * <p>Service factory locator to get the bean by name/id
	 * @param name
	 * @return
	 */
	public Transformer getTransformer(String name);
}
