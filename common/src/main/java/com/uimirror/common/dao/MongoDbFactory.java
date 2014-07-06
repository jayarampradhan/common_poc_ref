/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.dao
 * @createdOn 15-Mar-2014 9:09:48 PM
 * @modifiedby Jayaram
 * @modifiedon 15-Mar-2014 9:09:48 PM
 * ***********************************************************************
 */
package com.uimirror.common.dao;

import com.mongodb.DB;
import com.mongodb.Mongo;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : This the factory class for the mongo db 
 * connection per db.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 15-Mar-2014 9:09:48 PM
 * @modifiedby Jayaram
 * @modifiedon 15-Mar-2014 9:09:48 PM
 * ***********************************************************************
 */
public class MongoDbFactory {
	private final Mongo mongo;
	private String dbName;

	public MongoDbFactory(Mongo mongo) {
		super();
		this.mongo = mongo;
	}
	
	/**
	 * <p>Gets or create the db from the 
	 * @return
	 */
	public DB getDB(){
		return this.mongo.getDB(dbName);
	}
	
	/**
	 * <p>Close all the open connection with mongo server.
	 */
	public void close(){
		this.mongo.close();
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

}
