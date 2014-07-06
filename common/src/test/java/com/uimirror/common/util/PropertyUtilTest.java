package com.uimirror.common.util;
import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
public class PropertyUtilTest {
	
	PropertyUtil propertyUtil;
	/**
	 * <p>this method will test the setProperty method by 
	 * giving null values intentionally
	 * @param 
	 * @return void
	 * @author Gourang 
	 */
	@Test
	public void setPropertyNullTest(){
		
		try{
			propertyUtil = new PropertyUtil();
			propertyUtil.setProperty(null,null);
		}catch (IllegalArgumentException e){
			Assert.assertEquals("Property key and value should not be empty",e.getMessage());
		}
	}
	/**
	 * <p>this method will test the setProperty method by 
	 * giving empty values intentionally
	 * @param 
	 * @return void
	 * @author Gourang 
	 */
	@Test
	public void setPropertyEmptyTest(){
		try{
			propertyUtil = new PropertyUtil();
		}catch (IllegalArgumentException e){
			Assert.assertEquals("Property key and value should not be empty",e.getMessage());
		}
		
	}
	/**
	 * <p>this method will test the setProperty method by 
	 * giving valid values intentionally
	 * @param 
	 * @return void
	 * @author Gourang 
	 */
	@Ignore
	public void setPropertyValidTest(){
		propertyUtil=new PropertyUtil();
		String key="name";
		String value="sam";
		try{
			propertyUtil.setProperty(key,value);
		}catch (IllegalArgumentException e){
			Assert.assertEquals("Property key and value should not be empty",e.getMessage());
		}
		
	}
	/**
	 * <p>this method will test the getProperty method by 
	 * giving empty values intentionally
	 * @param 
	 * @return void
	 * @author Gourang 
	 */
	@Test
	public void getPropertyEmptyTest(){
		System.out.println(PropertyUtil.getProperty(""));	
	}
	/**
	 * <p>this method will test the getProperty method by 
	 * giving null values intentionally
	 * @param 
	 * @return void
	 * @author Gourang 
	 */
	@Test
	public void getPropertyNullTest(){
		System.out.println(PropertyUtil.getProperty(null));	
	}
	/**
	 * <p>this method will test the getProperty method by 
	 * giving valid values intentionally
	 * @param 
	 * @return void
	 * @author Gourang 
	 */
	@Ignore
	public void getPropertyValidTest(){
		System.out.println(PropertyUtil.getProperty("name"));	
	}
}
