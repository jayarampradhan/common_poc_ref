package com.uimirror.common.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : factory bean to get the collection namespaces.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 15-Mar-2014 11:47:15 PM
 * @modifiedby Jayaram
 * @modifiedon 15-Mar-2014 11:47:15 PM
 * ***********************************************************************
 */
public class DBCollectionFactory {

	private final DB db;
	private final String collectionName;
	
	public DBCollectionFactory(DB db, String collectionName) {
		super();
		this.db = db;
		this.collectionName = collectionName;
	}
	
	/**
	 * <p>Returns the collection specified in the collection construct
	 * @return
	 */
	public DBCollection getCollection(){
		return this.db.getCollection(collectionName);
	}
	
	
}
