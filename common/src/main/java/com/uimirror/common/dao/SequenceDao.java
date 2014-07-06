/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.dao
 * @createdOn 22-Mar-2014 1:53:27 AM
 * @modifiedby Jayaram
 * @modifiedon 22-Mar-2014 1:53:27 AM
 * ***********************************************************************
 */
package com.uimirror.common.dao;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : helpful to generate sequence for the 
 * different id generation.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 22-Mar-2014 1:53:27 AM
 * @modifiedby Jayaram
 * @modifiedon 22-Mar-2014 1:53:27 AM
 * ***********************************************************************
 */
public interface SequenceDao {

	/**
	 * <p>Get the next sequence number for the sequence name.
	 * @param seqName
	 * @return
	 */
	public String getNextSequence(final String seqName);
}
