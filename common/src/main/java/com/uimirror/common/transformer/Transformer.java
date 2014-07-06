package com.uimirror.common.transformer;

/**
 * 
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Common Transformer interface, which will suggest
 * to do the operation of transformer.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 12-Apr-2014 1:34:54 AM
 * @modifiedby Jayaram
 * @modifiedon 12-Apr-2014 1:34:54 AM
 * ***********************************************************************
 */
public interface Transformer {

	/**
	 * <p>Override the own logic while implementing transformation.
	 * @param obj
	 * @return
	 */
	public Object doTransform(Object obj);
}
