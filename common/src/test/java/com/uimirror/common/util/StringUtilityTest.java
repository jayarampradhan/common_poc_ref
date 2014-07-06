package com.uimirror.common.util;
import junit.framework.Assert;

import org.junit.Test;
import org.springframework.util.StopWatch;

public class StringUtilityTest {
	
	private String str;//String constant
	
	private String[] strs;//Array of string objects
	
	private StopWatch sw;//StopWatch object
	
	/**
	 *<p>This method tests a valid empty string
	 *<p>Here the string is empty intentionally
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void testEmptyString(){
		Assert.assertTrue(StringUtility.checkEmptyString(""));
	}
	/**
	 *<p>This method verify if the given string is null or not
	 *<p>Here the string is null intentionally.
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void testNullString(){
		str=null;
		Assert.assertTrue(StringUtility.checkEmptyString(str));
	}
	/**
	 *<p>This method tests a string of white spaces
	 *<p>Here the string consists of white spaces only
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void testWhiteSpace(){
		Assert.assertTrue(StringUtility.checkEmptyString("    "));
	}
	/**
	 *<p>This method will test a valid string
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void testValidString(){
		Assert.assertFalse(StringUtility.checkEmptyString("abc"));
	}
	/**
	 *<p>This method tests if the string array is empty or not
	 *<p>Here the string array is empty intentionally.
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void testEmptyStringArray(){
		strs =new String[]{"","",""};
		Assert.assertTrue(StringUtility.checkEmptyString(str));
	}
	/**
	 *<p>This method tests a valid array of strings
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void testValidStringArray(){
		strs =new String[]{"abc","def","ghi"};
		Assert.assertFalse(StringUtility.checkEmptyString(strs));
	}
	/**
	 *<p>This method tests the given string array is null or not
	 *<p>Here the String array is null intentionally.
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void testNullStringArray(){
		strs =null;
		Assert.assertTrue(StringUtility.checkEmptyString(str));
	}
	/**
	 *<p>This method tests if the length of a given string 
	 *is greater than or equal to the length specified.
	 *<p>Here the string length is equal to the length specified 
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void testValidStringLength(){
		str ="fgh";
		Assert.assertTrue(StringUtility.isLengthGreaterOrEqual(str, 3));
	}
	/**
	 *<p>This method tests if the length of a given string 
	 *is smaller than the length specified.
	 *<p>Here the string length is smaller than the length specified 
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void testGraterStringLength(){
		str ="fgh";
		Assert.assertFalse(StringUtility.isLengthGreaterOrEqual(str, 5));
	}
	/**
	 *<p>This method tests if the length of a given string 
	 *is greater than the length specified.
	 *<p>Here the string length is greater than the length specified 
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void testSmallerStringLength(){
		str ="fgh";
		Assert.assertTrue(StringUtility.isLengthGreaterOrEqual(str, 1));
	}
	/**
	 *<p>This method tests if the length of a given string 
	 *is greater than or equal to the length specified.
	 *<p>Here the string is null intentionally
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void testNullStringLength(){
		str =null;
		Assert.assertFalse(StringUtility.isLengthGreaterOrEqual(str, 3));
	}
	/**
	 *<p>This method tests if the length of a given string 
	 *is greater than or equal to the length specified.
	 *<p>Here the given string is empty intentionally  
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void testEmptyStringLength(){
		str ="";
		Assert.assertFalse(StringUtility.isLengthGreaterOrEqual(str, 3));
	}
	/**
	 *<p>This method removes the separators in a date string  
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void validTestRemoveDateSeparator(){
		str ="2014-04-02";
		Assert.assertEquals("20140402",StringUtility.replaceDateSeprator(str));
	}
	/**
	 *<p>This method removes the separators in a date string .
	 *<p>Here the String is null intentionally. 
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void nullTestRemoveDateSeparator(){
		str =null;
		Assert.assertEquals(null,StringUtility.replaceDateSeprator(str));
	}
	/**
	 *<p>This method removes the separators in a date string.
	 *<p>Here the String is empty intentionally.  
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void emptyTestRemoveDateSeparator(){
		str ="";
		Assert.assertEquals("",StringUtility.replaceDateSeprator(str));
	}
	/**
	 *<p>This method the formatStopwatchsummery method.  
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void testStopwatchSummery(){
		sw=new StopWatch();
		sw.start("new task started");
		sw.stop();
		System.out.println(StringUtility.formatStopSwatchSummery("Total time taken ", sw));
	}
}
