/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.dao
 * @createdOn 22-Mar-2014 1:55:17 AM
 * @modifiedby Jayaram
 * @modifiedon 22-Mar-2014 1:55:17 AM
 * ***********************************************************************
 */
package com.uimirror.common.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : $.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 22-Mar-2014 1:55:17 AM
 * @modifiedby Jayaram
 * @modifiedon 22-Mar-2014 1:55:17 AM
 * ***********************************************************************
 */
public class SequenceDaoImpl implements SequenceDao {
	
	private DBCollection seqCollection;

	/* (non-Javadoc)
	 * @see com.uimirror.common.dao.SequenceDao#getNextSequence(java.lang.String)
	 */
	@Override
	public String getNextSequence(String seqName) {
		// this object represents your "query", its analogous to a WHERE clause in SQL
	    DBObject query = new BasicDBObject(1);
	    query.put("_id", seqName); // where _id = the input sequence name
	 
	    // this object represents the "update" or the SET blah=blah in SQL
	    DBObject change = new BasicDBObject("seq", 1);
	    DBObject update = new BasicDBObject("$inc", change); // the $inc here is a mongodb command for increment
	 
	    // Atomically updates the sequence field and returns the value for you
	    DBObject res = seqCollection.findAndModify(query, new BasicDBObject(), new BasicDBObject(), false, update, true, true);
	    return res.get("seq").toString();
	}
	
	
	public void setSeqCollection(DBCollection seqCollection) {
		this.seqCollection = seqCollection;
	}

}
