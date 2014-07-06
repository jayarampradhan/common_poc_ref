package com.uimirror.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.uimirror.common.CommonConstants;
import com.uimirror.common.LoadExternalJson;

public class PrivacyBuilderTest {
	private List<Map<String, Object>> privacyBuilderValidData;
	private List<Map<String, Object>> privacyBuilderInValidData;
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception{
		privacyBuilderValidData = new ArrayList<Map<String,Object>>(10);
		privacyBuilderInValidData=new ArrayList<Map<String,Object>>(10);
		privacyBuilderValidData = (List<Map<String,Object>>)LoadExternalJson.loadData("privacy/privacy_builder_valid_data_set.txt", List.class);
		privacyBuilderInValidData = (List<Map<String,Object>>)LoadExternalJson.loadData("privacy/privacy_builder_invalid_data_set.txt", List.class);
	}
	@Test
	@SuppressWarnings("unchecked")
	public void buildPrivacyValidTest(){
		for(Map<String, Object> privacyTest:privacyBuilderValidData){
			String privacyName = (String)privacyTest.get(CommonConstants.FL_PRIVACY);
			List<Map<String, String>> shareWith = (List<Map<String,String>>)privacyTest.get(CommonConstants.FL_PRIVACY_SHARE_WITH);
			List<Map<String, String>> notShareWith = (List<Map<String,String>>)privacyTest.get(CommonConstants.FL_PRIVACY_SHARE_NOT_WITH);
			Map<String,Object> res = PrivacyBuilder.buildPrivacy(privacyName, shareWith, notShareWith);
			Assert.notNull(res,"The Class must not be null");
		}
		}
	@Test
	@SuppressWarnings("unchecked")
	public void buildPrivacyInValidTest(){
		for(Map<String, Object> privacyTest:privacyBuilderInValidData){
			String privacyName = null;
			List<Map<String, String>> shareWith = (List<Map<String,String>>)privacyTest.get(CommonConstants.FL_PRIVACY_SHARE_WITH);
			List<Map<String, String>> notShareWith = (List<Map<String,String>>)privacyTest.get(CommonConstants.FL_PRIVACY_SHARE_NOT_WITH);
			Map<String,Object> res = PrivacyBuilder.buildPrivacy(privacyName, shareWith, notShareWith);
			Assert.notNull(res,"The Class must not be null");
		}
		}
}
