/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.dao.util
 * @createdOn 23-Mar-2014 3:26:03 PM
 * @modifiedby Jayaram
 * @modifiedon 23-Mar-2014 3:26:03 PM
 * ***********************************************************************
 */
package com.uimirror.common.dao.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.uimirror.common.CommonConstants;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : helper for different activities for the DB 
 * operations which is common for all the db.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 23-Mar-2014 3:26:03 PM
 * @modifiedby Jayaram
 * @modifiedon 23-Mar-2014 3:26:03 PM
 * ***********************************************************************
 */
public class CommonDaoHelper {
	
	/**
	 * <p>Builds the dbobject from the specified map.
	 * @param idValue
	 * @return
	 */
	public static DBObject getDBObjectFromMap(final Map<String, Object> args){
		return new BasicDBObject(args);
	}
	
	/**
	 * <p>Builds the dbobject for unset fields 
	 * <p>Equivalent to set name="" 
	 * @param changeFileds
	 * @return
	 */
	public static DBObject getUnsetQuery(final String ... args){
		DBObject change = new BasicDBObject();
		for (String arg : args){
			change.put(arg, CommonConstants.EMPTY_STRING);
		}
		return new BasicDBObject(CommonConstants._UNSET, change);
	}
	
	/**
	 * <p>Builds the dbobject for unset fields 
	 * <p>Equivalent to set name="" 
	 * @param changeFileds
	 * @return
	 */
	public static DBObject getUnsetQuery(final Map<String, Object> args){
		DBObject change = new BasicDBObject(args.size());
		change.putAll(args);
		return new BasicDBObject(CommonConstants._UNSET, change);
	}
	
	/**
	 * <p>Builds the dbobject for set fields 
	 * <p>Equivalent to set name="val" 
	 * @param changeFileds
	 * @return
	 */
	public static final DBObject getSetQuery(final Map<String, Object> args){
		DBObject change = new BasicDBObject(args.size());
		change.putAll(args);
		return new BasicDBObject(CommonConstants._SET, change);
	}

	/**
	 * <p>Builds the dbobject for set fields in array 
	 * <p>Equivalent to set name="val"
	 * @param vals
	 * @param fieldName
	 * @return
	 */
	public static final DBObject getAddToSetWithEachQuery(final List<Map<String, Object>> vals, String fieldName){
		DBObject each = new BasicDBObject(1);
		each.put(CommonConstants._EACH, vals);
		DBObject fields = new BasicDBObject(1);
		fields.put(fieldName, each);
		return new BasicDBObject(CommonConstants._ADDTOSET, fields);
	}
	
	/**
	 * <p>Builds the dbobject for remove fields in array 
	 * <p>Equivalent to set name=""
	 * @param vals
	 * @return
	 */
	public static final DBObject getPullQuery(final Map<String, Object> vals){
		DBObject fields = new BasicDBObject(vals.size());
		fields.putAll(vals);
		return new BasicDBObject(CommonConstants._PULL, fields);
	}
	
	/**
	 * <p>This will get the map for the id field of the document.
	 * @param idValue
	 * @return
	 */
	public static final Map<String, Object> getMapForId(final String idValue){
		Map<String, Object> map = new HashMap<String, Object>(4);
		map.put(CommonConstants._ID, idValue);
		return map;
	}

}
