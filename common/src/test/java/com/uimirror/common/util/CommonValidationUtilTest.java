package com.uimirror.common.util;
import org.junit.Test;
import junit.framework.Assert;

public class CommonValidationUtilTest {
	
	/**
	 *<p>This method tests if the Ip address given is a valid ip or not.
	 *<p>Here the given Ip address is valid.   
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void checkValidIp(){
		Assert.assertTrue(CommonValidationUtil.isIpValid("192.168.1.5"));
	}
	/**
	 *<p>This method tests if the Ip address given is empty or not.
	 *<p>Here the given Ip address is empty intentionally.   
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test 
	public void checkEmptyIp(){
		Assert.assertFalse(CommonValidationUtil.isIpValid(""));
	}
	/**
	 *<p>This method tests if the Ip address given is null.
	 *<p>Here the given Ip address is null intentionally.   
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test 
	public void checkNullIp(){
		Assert.assertFalse(CommonValidationUtil.isIpValid(null));
	}
	/**
	 *<p>This method tests if the Ip address given is a valid ip or not.
	 *<p>Here the given Ip address is invalid(alphabatic) intentionally.   
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void checkAlphabaticInvalidIp(){
		Assert.assertFalse(CommonValidationUtil.isIpValid("abc.def.s.g"));
	}
	/**
	 *<p>This method tests if the Ip address given is a valid ip or not.
	 *<p>Here the given Ip address is invalid intentionally.   
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test 
	public void checkSpecialCharInvalidIP(){
		Assert.assertFalse(CommonValidationUtil.isIpValid("********"));
	}
	/**
	 *<p>This method tests if the given user id is valid or not.
	 *<p>User id should be of email format.any email id is a valid user id.
	 *<p>Here the user id is empty intentionally.   
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void checkEmptyUserId(){
		Assert.assertFalse(CommonValidationUtil.isValidUserId(""));
	}
	/**
	 *<p>This method tests if the given user id is valid or not.
	 *<p>User id should be of email format.any email id is a valid user id.
	 *<p>Here the user id is null intentionally.   
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void checkNullUserId(){
		Assert.assertFalse(CommonValidationUtil.isValidUserId(null));
	}
	/**
	 *<p>This method tests if the given user id is valid or not.
	 *<p>User id should be of email format.any email id is a valid user id.
	 *<p>Here the user id is wrong intentionally.   
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void checkInvalidUserId(){
		Assert.assertFalse(CommonValidationUtil.isValidUserId("@.com"));
	}
	/**
	 *<p>This method tests if the given user id is valid or not.
	 *<p>User id should be of email format.any email id is a valid user id.
	 *<p>Here the user id is a valid email.   
	 *@param	
	 *@return	void
	 *@author Gourang 
	 */
	@Test
	public void checkValidUserId(){
		Assert.assertTrue(CommonValidationUtil.isValidUserId("gourangamallick@ymail.com"));
	}
}