/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.util
 * @createdOn 21-Mar-2014 11:07:36 PM
 * @modifiedby Jayaram
 * @modifiedon 21-Mar-2014 11:07:36 PM
 * ***********************************************************************
 */
package com.uimirror.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.util.Assert;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : collection util for the various data type 
 * conversion like multi valued map to simple map etc.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 21-Mar-2014 11:07:36 PM
 * @modifiedby Jayaram
 * @modifiedon 21-Mar-2014 11:07:36 PM
 * ***********************************************************************
 */
public class CollectionUtils extends org.apache.commons.collections.CollectionUtils{
	
	private CollectionUtils(){
		
	}
	
	public static CollectionUtils getInstance(){
		return new CollectionUtils();
	}
	
	/**
	 * <p>This will convert multi valued map to normal hash map
	 * keeping list object as list and convert other object as normal value
	 * @param multiMap
	 * @return
	 */
	public Map<String, Object> getMapFromMultiMap(final MultivaluedMap<String, String> multiMap){
		if(multiMap == null){
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>(multiMap.size());
		for(Entry<String, ?> entry :  multiMap.entrySet()){
			@SuppressWarnings("unchecked")
			List<Object> val = (List<Object>) entry.getValue();
			
			if(val != null && val.size() > 1){
				map.put(entry.getKey(), val);
			}else if(val != null){
				map.put(entry.getKey(), val.get(0));
			}
		}
		return map;
	}
	
	/**
	 * <p>This will get multi val to singel or multi val map.
	 * @param map
	 * @return
	 */
	//TODO
	/*@SuppressWarnings("unchecked")
	public static Map<String, ? extends Object> convertAllMultiToSomeSingel(final Map<String, Object> map){
		if(map == null){
			return null;
		}
		Map<String, Object> res = new HashMap<String, Object>();
		for(Entry<String, Object> entry : map.entrySet()){
			List<Object> val = (List<Object>)entry.getValue();
			if(val != null && val.size() > 1){
				map.put(entry.getKey(), val);
			}else if(val != null){
				map.put(entry.getKey(), val.get(0));
			}
		}
		return res;
	}*/
	
	/**
	 * <p>This will decode the fields from the string/ array of strings into list.
	 * <p>This will be fail fast, if a object is null, it will fail with {@link IllegalArgumentException} exception
	 * @param obj
	 * @param message Validation failed message like, in case object null.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> decodeToListOfString(final Object obj, final String message){
		Assert.notNull(obj, message);
		List<String> lst = new ArrayList<String>(50);
		if (obj instanceof String){
			lst.add((String) obj);
		}else if(obj instanceof Collection){
			lst.addAll((List<? extends String>) obj);
		}
		return lst;
	}
	
	/**
	 * <p>This will decode the fields from the string/ array of strings into list.
	 * <p>unlike {@link CollectionUtils#decodeToListOfString(Object, String)}, it will exception safe,
	 * it will return the lists or null according to the parameter.
	 * @param obj
	 * @return null if object is null else list of string
	 */
	@SuppressWarnings("unchecked")
	public static List<String> decodeToListOfString(final Object obj){
		if(obj != null){
			List<String> lsts = new ArrayList<String>(50);
			if (obj instanceof String){
				lsts.add((String) obj);
			}else{
				lsts.addAll((List<? extends String>) obj);
			}
			return lsts;
		}
		return null;
	}
	
	/**
	 * <p>It will build the update map to be executed from lists
	 * like fields, values, commands and separator.
	 * <p>If no separator present the it will map each command to each fields
	 * @param cmds
	 * @param flds
	 * @param vals
	 * @param seprators
	 * @param prefix 
	 * @return
	 */
	public static Map<String, Object> decodeUpdateMap(final List<String> cmds, final List<String> flds, final List<String> vals, final List<String> seprators) {
		
		int length = flds.size();
		int comandSize = cmds.size();
		int sepratorSum = MathematicsHelper.getSum(seprators);
		int sepratorSize = sepratorSum == 0 ? 0 : seprators.size();
		
		int startOffset = 0;
		int endOffset = 0;
		int counter = 0;
		int reminder = 0;
		int quotient = 0;
		
		//Validate length constraints
		CommonValidationUtil.validateDecodedUpdateMap(length, comandSize, vals.size(), sepratorSum, sepratorSize);

		Map<String, Object> update = new HashMap<String, Object>(50);		
		if(sepratorSum > 0){
			endOffset = Integer.parseInt(seprators.get(0));
			if(sepratorSize < comandSize){
				int remaining = length - sepratorSum;
				int remainingLength = comandSize - sepratorSize;
				reminder = remaining % remainingLength;
				quotient = remaining / remainingLength;
			}
		}else {
					
			reminder = length%comandSize;
			quotient = length/comandSize;
			endOffset = quotient;
		}

		while(startOffset < endOffset){
		
			update.put(cmds.get(counter), buildMapFromList(startOffset, endOffset, flds, vals));
			counter++;
			startOffset = endOffset;
					
			if(counter < sepratorSize){
				endOffset = endOffset+Integer.parseInt(seprators.get(counter));
			} else if(endOffset < length){
				int diff = length - endOffset;
				int qrSum = reminder+quotient;
				if(diff == qrSum){
					endOffset += qrSum; 
				}else{
					endOffset += quotient;
				}

			}
		}
		return update;
	}

	/**
	 * <p>This will build a Map instance from the two lists.
	 * <p>Basic criteria is fields and values should be in equal length
	 * @param startOffest
	 * @param endOffset
	 * @param fields
	 * @param vals
	 * @return
	 */
	public static Map<String, Object> buildMapFromList(int startOffest, int endOffset, List<String> fields, List<String> vals){
		Map<String, Object> map = new HashMap<String, Object>();
		for(int i = startOffest; i < endOffset; i++){
			map.put(fields.get(i), vals.get(i));
		}
		return map;
	}
	

}
