package com.uimirror.common;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : This class will help to build the query 
 * parameters for MYSQL queries.
 * <p>This class no longer in use as we are using MYSQL->MONGO
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 10-Apr-2014 12:36:37 PM
 * @modifiedby Jayaram
 * @modifiedon 10-Apr-2014 12:36:37 PM
 * ***********************************************************************
 */
@Deprecated
public final class QueryBuilder implements Serializable{

    private static final long serialVersionUID = -3131293383762174431L;
    protected static final Logger LOG = LoggerFactory.getLogger(QueryBuilder.class);

    private QueryBuilder() {
	LOG.debug("Instance of {} has been created",this.getClass());
    }
    
    public static QueryBuilder getInstance(){
	return new QueryBuilder();
    }
    
    /**
     * <p>this will build the values for the in 
     * clause
     * @param args
     * @return
     */
    public Object[] buildInParameter(String ... args){
	Object[] obj = new Object[1];
	StringBuilder sb = new StringBuilder();
	int length = args.length;
	for(int i=0; i<args.length-1;i++){
	    sb.append(args[i]);
	    sb.append(",");
	}
	sb.append(args[length-1]);
	obj[0] = sb.toString();
	return obj;
    }
    
    /**
     * <p>this will build the values for ? place  holder
     * @param args
     * @return
     */
    public Object[] buildPlaceHolderParameter(Object ... args){
	Object[] obj = new Object[args.length];
	for(int i=0; i<args.length;i++){
	    obj[i] = args[i];
	}
	return obj;
    }

}
