package com.uimirror.common.dao;

import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : mongo options factory that will be used to 
 * set the different options for the mongo connection factory.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 15-Mar-2014 9:51:12 PM
 * @modifiedby Jayaram
 * @modifiedon 15-Mar-2014 9:51:12 PM
 * ***********************************************************************
 */
public class MongoOptionsFactory {
	
	private int maxPoolSize = 20;
	private int connectTimeout = 2000;
	private ReadPreference readPreference = ReadPreference.secondaryPreferred();
	private WriteConcern writeConcern = WriteConcern.ACKNOWLEDGED;

	public MongoOptionsFactory(int maxPoolSize, int connectTimeout) {
		super();
		this.maxPoolSize = maxPoolSize;
		this.connectTimeout = connectTimeout;
	}

	public MongoOptionsFactory() {
		super();
	}

	/**
	 * <p>Builds the options for the mongo connection.
	 * @return
	 */
	public MongoClientOptions getMongoOptions(){
		return MongoClientOptions.builder()
                .connectionsPerHost(maxPoolSize)
                .connectTimeout(this.connectTimeout)
                .readPreference(readPreference)
                .writeConcern(writeConcern)
                .build();
	}

	
	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public ReadPreference getReadPreference() {
		return readPreference;
	}

	public void setReadPreference(ReadPreference readPreference) {
		this.readPreference = readPreference;
	}

	public WriteConcern getWriteConcern() {
		return writeConcern;
	}

	public void setWriteConcern(WriteConcern writeConcern) {
		this.writeConcern = writeConcern;
	}

}
