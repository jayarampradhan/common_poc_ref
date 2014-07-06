package com.uimirror.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import com.uimirror.common.LoadExternalJson;
import com.uimirror.common.authentication.AuthenticationBean;
import com.uimirror.common.base.BaseTest;
import com.uimirror.common.base.JUnit4ClassRunner;
@RunWith(JUnit4ClassRunner.class)
public class ResponseHelperTest extends BaseTest {
	
	private String rCode;
	private String block;
	private Map<String,Object> msg;
	private Map<String, Object> invalidFormData;
	static String TEST_DATA = "testdata";
	@Autowired
	private Validator validator;
	
	@SuppressWarnings("unchecked")
	@Before
    public void setUp() throws Exception {
    	invalidFormData = (Map<String, Object>) LoadExternalJson.loadData("authentication/invalid_data_set_1.txt", Map.class);
	}
	@Test
	public void buildResponseMapRcodeNullTest(){
		msg=new HashMap<String, Object>();
		msg.put("name", "gourang");
		try{
			ResponseHelper.buildResponseMap(rCode, msg);
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			Assert.assertEquals("Response Code shouldn't be empty.",e.getMessage());
		}
	}
	@Test
	public void buildResponseMapMsgNullTest(){
		rCode = "hello!";
		try{
			ResponseHelper.buildResponseMap(rCode, msg);
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			Assert.assertTrue("Response Message Can't be null.",e.equals(e));
		}
	}
	@Test 
	public void emptyMsgTestBuildResponseMap(){
		rCode="abc";
		msg=new HashMap<String, Object>();
		try{
			ResponseHelper.buildResponseMap(rCode, msg);
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Response Message Can't be null.",e.getMessage());
		}
	}
	@Test 
	public void emptyCodeTestBuildResponseMap(){
		rCode="";
		msg=new HashMap<String, Object>();
		msg.put("ldg", "kldjkf");
		try{
			ResponseHelper.buildResponseMap(rCode, msg);
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Response Code shouldn't be empty.",e.getMessage());
		}
	}
	 	
	@Test
	public void validTestBuildResponseMap(){
		rCode="code";
		msg= new HashMap<String, Object>();
		msg.put("test", "this is for test");
		try{
			Assert.assertTrue("Map is null",(ResponseHelper.buildResponseMap(rCode, msg)!=null));
		}catch(IllegalArgumentException e){
		System.out.println(e);
		}
	}
	
	@Test
	public void nullTestForRcodeBuildResponseMap(){
		block="block";
		msg=new HashMap<String, Object>();
		try{
			ResponseHelper.buildResponseMap(rCode,block,msg);
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Response Code shouldn't be empty.", e.getMessage());
		}
	}
	@Test
	public void nullTestForBlockBuildResponseMap(){
		rCode="code";
		msg=new HashMap<String, Object>();
		try{
			ResponseHelper.buildResponseMap(rCode,block,msg);
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Response Block shouldn't be empty.", e.getMessage());
		}
	}
	@Test
	public void nullTestForMsgBuildResponseMap(){
		rCode="code";
		block="block";
		try{
			ResponseHelper.buildResponseMap(rCode,block,msg);
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Response Message Can't be null.", e.getMessage());
		}
	}
	@Test
	public void emptyTestForRcodeBuildResponseMap(){
		rCode="";
		block="block";
		msg= new HashMap<String, Object>();
		msg.put("test", "this is for test");
		try{
			ResponseHelper.buildResponseMap(rCode,block,msg);
			}catch(IllegalArgumentException e){
				Assert.assertEquals("Response Code shouldn't be empty.", e.getMessage());
			}
	}
	@Test
	public void emptyTestForBlockBuildResponseMap(){
		rCode="code";
		block="";
		msg=new HashMap<String, Object>();
		msg.put("test", "this is for test");
		try{
			ResponseHelper.buildResponseMap(rCode,block,msg);
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Response Block shouldn't be empty.", e.getMessage());
		}
	}
	@Test
	public void emptyTestForMsgBuildResponseMap(){
		rCode="code";
		block="block";
		msg = new HashMap<String, Object>();
		try{
			ResponseHelper.buildResponseMap(rCode,block,msg);
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Response Message Can't be null.", e.getMessage());
		}
	}
	
	@Test
	public void validTestForBuildResponseMap(){
		rCode="code";
		block="test block";
		msg=new HashMap<String, Object>();
		msg.put("rscode", "this is response code");
		Assert.assertTrue("Map is not null",(ResponseHelper.buildResponseMap(rCode,block,msg)!=null));
	}
	
	@Test
	public void editResponseMapRcodeNullTest(){
		block="sdsd";
		msg=new HashMap<String, Object>();
		try{
			ResponseHelper.editResponseMap(msg,block,rCode);
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Response Message Can't be null.", e.getMessage());
		}
	}
	@Test
	public void editResponseMapMsgNullTest(){
		rCode="code";
		block="sdsd";
		try{
			ResponseHelper.editResponseMap(msg,block,rCode);
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Map Object Can't be null.", e.getMessage());
		}
	}
	@Test
	public void editResponseMapRcodeEmptyTest(){
		rCode="   ";
		block="block";
		msg=new HashMap<String, Object>();
		msg.put("key", "value");
		try{	
			ResponseHelper.editResponseMap(msg,block,rCode);
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Response Message Can't be null.", e.getMessage());
		}
	}
	
	@Test
	public void editResponseMapValidTest(){
		block="block as key";
		msg=new HashMap<String, Object>();
		msg.put("adsd", "vgfg");
		rCode="block as value";
		Assert.assertTrue("Map is null",(ResponseHelper.editResponseMap(msg,block,rCode)!=null));
	}
	
	@Test
	public void buildValidationFaildResponseTest(){
		StopWatch stopWatch = new StopWatch("authenticationInvalidForm");
    	stopWatch.start();
    	AuthenticationBean auth = null;
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataSets = (List<Map<String, Object>>) invalidFormData.get(TEST_DATA);
    	for (Map<String, Object> authMap : dataSets){
    		auth = new AuthenticationBean(authMap);
    		Set<ConstraintViolation<AuthenticationBean>> violations = validator.validate(auth);
    		Map<String, Object> map = ResponseHelper.buildValidationFaildResponse(violations);
    		Assert.assertNotNull("response object cant be null",map);
    		System.out.println(map);
    		}
		}
	@Test
	public void buildValidationFaildResponse(){
		Map<String, Object> map =ResponseHelper.buildValidationFaildResponse();
		System.out.println(map);
	}
}