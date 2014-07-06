package com.uimirror.common.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class MathematicsHelperTest {
	private static String[] str;
	private static List<String> lt;
	/**
	 *<p>this will test the addStringMethod for valid data
	 *@author Gourang
	 *@return void
	 *@param
	 */
	@Test
	public void validTestForAddStringToInt(){
		str =new String[]{"10","20"};
		Assert.assertEquals("30", MathematicsHelper.addStringToInt(str));
	}
	/**
	 *<p>this is the empty test for the addStringMethod 
	 *@author Gourang
	 *@return void
	 *@param
	 */
	@Test
	public void emptyTestForAddStringToInt(){
		str =new String[]{"",""};
		Assert.assertEquals("0", MathematicsHelper.addStringToInt(str));
	}
	/**
	 *<p>this is the null test for the addStringMethod 
	 *@author Gourang
	 *@return void
	 *@param
	 */
	@Test
	public void nullTestForAddStringToInt(){
		//Assert.assertEquals(null, MathematicsHelper.addStringToInt(str));
	}
	/**
	 *<p>this will test the addStringMethod for invalid data
	 *@author Gourang
	 *@return void
	 *@param
	 */
	@Test
	public void testForAddStringToInt(){
		Assert.assertNotSame("0", MathematicsHelper.addStringToInt("bac"));
	}
	/**
	 *<p>this will test the getSum method for empty data
	 *@author Gourang
	 *@return void
	 *@param
	 */
	@Test
	public void emptyTestForGetSum(){
		lt = new ArrayList<String>();
		Assert.assertEquals(0, MathematicsHelper.getSum(lt));
	}
	/**
	 *<p>this will test the getSum Method for null data
	 *@author Gourang
	 *@return void
	 *@param
	 */
	@Test
	public void nullTestForTestGetSum(){
		lt =null;
		Assert.assertEquals(0, MathematicsHelper.getSum(lt));
	}
	/**
	 *<p>this will test the getSum Method for valid data
	 *@author Gourang
	 *@return void
	 *@param
	 */
	@Test
	public void validTestForGetSum(){
		lt = new ArrayList<String>();
		lt.add("10");
		lt.add("20");
		Assert.assertEquals(30, MathematicsHelper.getSum(lt));
	}
}
