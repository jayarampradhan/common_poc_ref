package com.uimirror.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.uimirror.common.CommonConstants;
import com.uimirror.common.LoadExternalJson;

public class CollectionUtilsTest {
	
	private List<Map<String, Object>> decodeValidUpdateData;
	private List<Map<String, Object>> decodeInValidUpdateData;
	private List<MultivaluedMap<String, String>> multivaluedValidData;
	
	@SuppressWarnings("unchecked")
	@Before
    public void setUp() throws Exception {
		decodeValidUpdateData = new ArrayList<Map<String,Object>>(20);
		decodeInValidUpdateData = new ArrayList<Map<String,Object>>(20);
		
		decodeValidUpdateData=(List<Map<String, Object>>) LoadExternalJson.loadData("collection/decode_valid_data_set_1.txt", List.class);
		decodeInValidUpdateData =  (List<Map<String, Object>>) LoadExternalJson.loadData("collection/decode_invalid_data_set.txt", List.class);
		//multivaluedValidData.add((MultivaluedMap<String, String>) LoadExternalJson.loadData("multivaluedtest/multivalued_valid_data_set_1.txt", MultivaluedMap.class));
    }
	
	
	//TODO implement the test case
	/*@Test
	public void testGetMapFromMultiMap() {
	for (MultivaluedMap<String, String> map : multivaluedValidData){
			Assert.assertTrue(CollectionUtils.getInstance().getMapFromMultiMap(map) instanceof Map<?, ?>);
		}
	}*/
	
	/*private List<MultivaluedMap<String, String>> testMultiValuedData() {
    	List<MultivaluedMap<String, String>> list = new ArrayList<MultivaluedMap<String, String>>(5);
    	MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>(1);
    	map.add(CommonConstants.FL_FIRST_NAME, "Jayaram");
    	map.add(CommonConstants.FL_LAST_NAME, "Pradhan");
    	map.add(CommonConstants.FL_LOCALE, "en_US");
    	map.add(CommonConstants.FL_TIME_ZONE, "CET");
    	map.add(CommonConstants.FL_DATE_OF_BIRTH, "1988-03-10");
    	map.add(CommonConstants.EMAIL, "jayaramimca@gmail.com");
    	map.add(CommonConstants.PASSWORD, "12345");
    	map.add(CommonConstants.FL_SEX, "@");
        list.add(map);
        
        MultivaluedMap<String, String> map1 = new MultivaluedHashMap<String, String>(1);
        map1.add(CommonConstants.FL_FIRST_NAME, "D'Souza");
    	map1.add(CommonConstants.FL_LAST_NAME, "Pradhan");
    	map1.add(CommonConstants.FL_LOCALE, "en_US");
    	map1.add(CommonConstants.FL_TIME_ZONE, "CET");
    	map1.add(CommonConstants.FL_DATE_OF_BIRTH, "1988-03-10");
    	map1.add(CommonConstants.EMAIL, "jayaramimca@gmail.com");
    	map1.add(CommonConstants.PASSWORD, "zsxq4520");
    	map1.add(CommonConstants.FL_SEX, "123");
        list.add(map1);
        
        MultivaluedMap<String, String> map2 = new MultivaluedHashMap<String, String>(1);
        map2.add(CommonConstants.FL_FIRST_NAME, "Jani");
    	map2.add(CommonConstants.FL_LAST_NAME, "Test");
    	map2.add(CommonConstants.FL_LOCALE, "en_US");
    	map2.add(CommonConstants.FL_TIME_ZONE, "CET");
    	map2.add(CommonConstants.FL_DATE_OF_BIRTH, "1988-03-10");
    	map2.add(CommonConstants.EMAIL, "jayaramimca@gmail.com");
    	map2.add(CommonConstants.PASSWORD, "zsxq4520");
    	map2.add(CommonConstants.FL_SEX, "asdf3");
        list.add(map2);
        
        MultivaluedMap<String, String> map3 = new MultivaluedHashMap<String, String>(1);
        map3.add(CommonConstants.FL_FIRST_NAME, "Jani");
    	map3.add(CommonConstants.FL_LAST_NAME, "Test");
    	map3.add(CommonConstants.FL_LOCALE, "en_US");
    	map3.add(CommonConstants.FL_TIME_ZONE, "CET");
    	map3.add(CommonConstants.FL_DATE_OF_BIRTH, "1988-03-10");
    	map3.add(CommonConstants.EMAIL, "jayaramimca@gmail.com");
    	map3.add(CommonConstants.PASSWORD, "zsxq4520");
    	map3.add(CommonConstants.FL_SEX, "w");
        list.add(map3);
        
        MultivaluedMap<String, String> map4 = new MultivaluedHashMap<String, String>(1);
        map4.add(CommonConstants.FL_FIRST_NAME, "Jani");
    	map4.add(CommonConstants.FL_LAST_NAME, "Test");
    	map4.add(CommonConstants.FL_LOCALE, "en_US");
    	map4.add(CommonConstants.FL_TIME_ZONE, "CET");
    	map4.add(CommonConstants.FL_DATE_OF_BIRTH, "1988-03-10");
    	map4.add(CommonConstants.EMAIL, "jayaramimca@gmail.com");
    	map4.add(CommonConstants.PASSWORD, "zsxq4520");
    	map4.add(CommonConstants.FL_SEX, "");
        list.add(map4);
        
        return list;

    }
*/	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void getDecodeUpdateMapValidTest(){
		
		for(Map<String, Object> testData : decodeValidUpdateData){
			
			List<String> commands = (List<String>)testData.get(CommonConstants.COMMANDS);
			List<String> fields = (List<String>)testData.get(CommonConstants.FIELDS);
			List<String> values = (List<String>)testData.get(CommonConstants.VALUES);
			List<String> seprator = (List<String>)testData.get(CommonConstants.COMMAND_SEP);
			
			Map<String, Object> res = CollectionUtils.decodeUpdateMap(commands, fields, values, seprator);
			
			Assert.assertNotNull(res);
			System.out.println(res);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getDecodeUpdateMapInvalidTest(){
		
		for(Map<String, Object> testData : decodeInValidUpdateData){
			try{
				List<String> commands = (List<String>) testData.get(CommonConstants.COMMANDS);
				List<String> fields = (List<String>) testData.get(CommonConstants.FIELDS);
				List<String> values = (List<String>) testData.get(CommonConstants.VALUES);
				List<String> seprator = (List<String>) testData.get(CommonConstants.COMMAND_SEP);
				Map<String, Object> res = CollectionUtils.decodeUpdateMap(commands, fields, values, seprator);
				Assert.fail("All Data Should have invalid");
				
			}catch(Exception e){
				Assert.assertEquals(testData.get("exception"), e.getMessage());
			}
			
		}
	}
	
	
	@Test
	public void decodeToListOfStringFromListTest(){
		List<Object> lst = new ArrayList<Object>();
		lst.add("Test");
		lst.add(2);
		lst.add("Test2");
		lst.add(12.20);
		lst.add(545454L);
		lst.add(CollectionUtils.getInstance());
		System.out.println(CollectionUtils.decodeToListOfString(lst, "Lst Not Valid"));
		Assert.assertNotNull(CollectionUtils.decodeToListOfString(lst, "Lst Not Valid"));
	}
	@Test
	public void decodeToListOfStringTest(){
		List<Object> lst = new ArrayList<Object>();
		lst.add("Test");
		lst.add(2);
		lst.add("Test2");
		lst.add(12.20);
		lst.add(545454L);
		lst.add(CollectionUtils.getInstance());
		System.out.println(CollectionUtils.decodeToListOfString(lst));
		Assert.assertNotNull(CollectionUtils.decodeToListOfString(lst));
	}
	
	@Test
	public void nullTestForFailFastDecodeToListOfString(){
		try{
			
			CollectionUtils.decodeToListOfString(null, "Lst Not Valid");
		}catch(Exception e){
			Assert.assertEquals("Lst Not Valid", e.getMessage());
		}
	}

	@Test
	public void failTestForDecodeToListOfString(){
			
			Assert.assertNull(CollectionUtils.decodeToListOfString(null));
	}
	@Test
	public void testDecodeToListOfStringFromString(){
		try{
			System.out.println(CollectionUtils.decodeToListOfString("test", "Lst Not Valid"));
			Assert.assertNotNull(CollectionUtils.decodeToListOfString("test", "Lst Not Valid"));
		}catch(Exception e){
			Assert.fail(e.getMessage());
		}
	}
}
	

