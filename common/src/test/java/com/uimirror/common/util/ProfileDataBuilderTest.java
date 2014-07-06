package com.uimirror.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.uimirror.common.CommonConstants;
import com.uimirror.common.LoadExternalJson;

public class ProfileDataBuilderTest {

	private List<Map<String, Object>> profileBuilderValidData;
	private List<Map<String, Object>> dobBuilderValidData;
	
	@SuppressWarnings("unchecked")
	@Before
    public void setUp() throws Exception {
		
	profileBuilderValidData = new ArrayList<Map<String,Object>>(10);
	dobBuilderValidData = new ArrayList<Map<String,Object>>(10);
	
	profileBuilderValidData = (List<Map<String,Object>>)LoadExternalJson.loadData("profiledatabuilder/profile_builder_valid_data_set.txt", List.class);
	dobBuilderValidData = (List<Map<String,Object>>)LoadExternalJson.loadData("profiledatabuilder/date_of_birth_valid_data_set.txt", List.class);
	}
	
	@Test
	public void buildName(){
		for(Map<String, Object> name :profileBuilderValidData){
			Object fName = (Object)name.get(CommonConstants.FL_FIRST_NAME);
			Object lName = (Object)name.get(CommonConstants.FL_LAST_NAME);
			Map<String,Object> res = ProfileDataBuilder.buildName(fName, lName);
			Assert.assertNotNull("response object cant be null.", res);
			System.out.println(res);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void buildDateOfBirth(){
		for (Map<String, Object> dateOfBirth :dobBuilderValidData){
			Object dob = (Object)dateOfBirth.get(CommonConstants.FL_DATE_OF_BIRTH);
			Map<String, Object> datePrivacy=(Map<String, Object>)dateOfBirth.get(CommonConstants.FL_BD_DATE_PRIVACY);
			Map<String, Object> yearPrivacy=(Map<String, Object>)dateOfBirth.get(CommonConstants.FL_BD_YEAR_PRIVACY);
			Map<String, Object> res = ProfileDataBuilder.buildDateOfBirth(dob, datePrivacy, yearPrivacy);
			Assert.assertNotNull("response object cant be null", res);
			System.out.println(res);
		}
	}
}