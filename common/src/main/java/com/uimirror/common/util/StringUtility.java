package com.uimirror.common.util;

import org.springframework.util.StopWatch;

public class StringUtility extends org.apache.commons.lang.StringUtils{

	/**
	 * <nl>
	 * If string is null or only spaces, return true.
	 * <nl>
	 * <nl>
	 * Example <code>String toCheck = ""</code>,then it will return true
	 * <nl>
	 * Example 2 <code>String tocheck2="   "//default spaces</code>, then it
	 * will return true
	 * <nl>
	 * 
	 * @param string
	 * @return
	 */
	public static boolean checkEmptyString(String str) {
		// If a string is null or only have white spaces then return true
		if (str == null || str.matches("\\s+") || "".equals(str)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
	
	/**
	 * <p>Check the array of string for empty, if one found as empty then return 
	 * <p>This is useful when checking for breaking the condition if any one is empty.
	 * @param strs
	 * @return
	 */
	public static boolean checkEmptyString(String ... strs){
		if(strs==null){
			return Boolean.TRUE;
		}
		boolean isEmpty = Boolean.FALSE;
		for(String str : strs){
			isEmpty = checkEmptyString(str);
			if(isEmpty){
				break;
			}
		}
		return isEmpty;
	}

	/**
	 * <p>
	 * This will check the provided string without white space and null is
	 * greater than or equal to the length specified
	 */
	public static boolean isLengthGreaterOrEqual(String str, int ln) {
		if (!checkEmptyString(str) && str.length() >= ln) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * <p>
	 * This will replace the date format 12-01-2013 to 12012013
	 */
	public static String replaceDateSeprator(String str) {
		return replace(str, "-", "");
	}

	/**
	 * <p>
	 * This will format the stopwatch summary to be shown in the log
	 * 
	 * @param msg
	 * @param stopWatch
	 * @return
	 */
	public static String formatStopSwatchSummery(String msg, StopWatch stopWatch) {

		StringBuilder sb = new StringBuilder(100);

		sb.append("\n" + msg + "\n");
		sb.append("\n****************************************************************\n");
		sb.append(stopWatch.prettyPrint());
		sb.append("\n***************************************************************\n");
		sb.append(stopWatch.shortSummary());
		sb.append("\n*************************************************************\n");
		sb.append("Time Taken In second: "
				+ stopWatch.getLastTaskInfo().getTimeSeconds());
		sb.append("\n*************************************************************\n");

		return sb.toString();
	}

}
