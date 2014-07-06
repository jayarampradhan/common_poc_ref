/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.common.util
 * @createdOn 19-Mar-2014 1:26:49 AM
 * @modifiedby Jayaram
 * @modifiedon 19-Mar-2014 1:26:49 AM
 * ***********************************************************************
 */
package com.uimirror.common.util;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.InetAddressValidator;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : implementation of apache common validation.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 19-Mar-2014 1:26:49 AM
 * @modifiedby Jayaram
 * @modifiedon 19-Mar-2014 1:26:49 AM
 * ***********************************************************************
 */
public class CommonValidationUtil {
	
	public static boolean isIpValid(final String ip){
		return InetAddressValidator.getInstance().isValid(ip);
	}
	
	//TODO implement the proper logic for user id email and mobile number validation
	public static boolean isValidUserId(final String userId){
		
		return EmailValidator.getInstance().isValid(userId);
	}
	
	/**
	 * <p>This will validate the update request map before forming update map.
	 * @param length
	 * @param comandSize
	 * @param valSize
	 * @param sepratorSum
	 * @param sepratorSize
	 */
	public static void validateDecodedUpdateMap(final int length, final int comandSize, final int valSize, final int sepratorSum, final int sepratorSize) {
		//Field length and value size should match 
		if(length != valSize){
			throw new IllegalArgumentException("Fileds and Values should be equal length.");
		}
		
		//Command and field should be at least one command to execute
		if(length <= 0 || comandSize <= 0){
			throw new IllegalArgumentException("There should be at least one command and field to execute against a field.");
		}
		
		//Separator sum should be less than total field length
		if(sepratorSum > length){
			throw new IllegalArgumentException("Command Seprator should be equal or less than the field length.");
		}
		
		//Separator size should be less than command size
		if(sepratorSize > comandSize){
			throw new IllegalArgumentException("Command Seprator should be equal or less than the Command length.");
		}
		
		//Field length should be greater or equal command size.
		if(length < comandSize){
			throw new IllegalArgumentException("Field Length should be greater or equal to command size.");
		}
	}
	
	/**
	 * <p>This will validate the incoming request for checking update requests.
	 * <p>Validation not required as while forming the map , it will check for 
	 * all valid condition.
	 * @param request
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	@Deprecated
	public static boolean isValidUpdateRequest(final Map<String, Object> request){
		//First Check request shouldn't be empty.
		Assert.notEmpty(request, "Update Request shouldn't be empty");
				
		Object commands = request.get(CommonConstants.COMMANDS);
		Assert.notNull(commands, "Commands Shouldn't be empty.");
				
		Object fields = request.get(CommonConstants.FIELDS);
		Assert.notNull(fields, "Fileds Shouldn't be empty.");
				
		Object values = request.get(CommonConstants.VALUES);
		Assert.notNull(values, "Values Shouldn't be empty.");
				
		Object cmdSeprators = request.get(CommonConstants.COMMAND_SEP);
				
		List<String> cmds = new ArrayList<String>(5);
		List<String> flds = new ArrayList<String>(50);
		List<String> vals = new ArrayList<String>(50);
		List<String> seprators = null;
				
		if (commands instanceof String){
			cmds.add((String) commands);
		}else{
			cmds.addAll((List<? extends String>) commands);
		}
				
		if (fields instanceof String){
			flds.add((String) fields);
		}else{
			flds.addAll((List<? extends String>) fields);
		}
				
		if (values instanceof String){
			vals.add((String) fields);
		}else{
			vals.addAll((List<? extends String>) values);
		}
		
		if(cmdSeprators != null){
			seprators = new ArrayList<String>(50);
			if (cmdSeprators instanceof String){
				seprators.add((String) fields);
			}else{
				seprators.addAll((List<? extends String>) cmdSeprators);
			}
		}
				
		if(flds.size() != vals.size()){
			throw new IllegalArgumentException("Update Fields and values should be of same length");
		}
				
		if(seprators != null && cmds.size() < seprators.size()){
			throw new IllegalArgumentException("field seprators shouldn't be more than commands.");
		}
		return Boolean.TRUE;
	}
*/
}
