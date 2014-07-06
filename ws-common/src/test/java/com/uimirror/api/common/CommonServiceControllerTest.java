/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common
 * @createdOn 10-Mar-20141:22:51 am
 * @modifiedby Jayaram
 * @modifiedon 10-Mar-20141:22:51 am
 * ***********************************************************************
 */

package com.uimirror.api.common;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import com.uimirror.api.common.base.BaseTest;
import com.uimirror.api.common.base.JUnit4ClassRunner;
import com.uimirror.api.common.exception.CommonApiSystemException;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.util.StringUtility;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Test class for all the test case of 
 * CommonServiceController
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 10-Mar-20141:22:51 am
 * @modifiedby Jayaram
 * @modifiedon 10-Mar-20141:22:51 am
 * ***********************************************************************
 */
@RunWith(JUnit4ClassRunner.class)
public class CommonServiceControllerTest extends BaseTest{
    
	protected static final Logger LOG = LoggerFactory.getLogger(CommonServiceControllerTest.class);
    @Autowired
    private CommonServiceController commonServiceController;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void registerUserTest() throws CommonApiSystemException {
    	
    	for (MultivaluedMap<String, Object> rgMap : prepareRegisterTestData()){
    		Object obj = null;//commonServiceController.registerUser(rgMap);
    		Assert.assertNotNull(obj);
    	}
    	
    }
    
    @Test
    public void verifyUserTest() throws CommonApiSystemException {
    	
    	StopWatch stopWatch = new StopWatch("verificationcontroller");
    	stopWatch.start();
    	
    	for (MultivaluedMap<String, Object> vrfMap : prepareVerifyTestData()){
    		Object obj = null;//commonServiceController.verifyUser(vrfMap);
    		Assert.assertNotNull(obj);
    	}
    	
    	stopWatch.stop();
		LOG.debug("Verification process for 5 records {}", StringUtility.formatStopSwatchSummery("verificationcontroller", stopWatch));
    }
    
    private List<MultivaluedMap<String, Object>> prepareRegisterTestData(){
    	List<MultivaluedMap<String, Object>> rgList = new ArrayList<MultivaluedMap<String, Object>>(5);
    	
    	MultivaluedMap<String, Object> rqMap = new MultivaluedHashMap<String, Object>(1);
    	MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>(1);
    	
    	map.add(CommonConstants.FL_FIRST_NAME, "Jayaram");
    	map.add(CommonConstants.FL_LAST_NAME, "Pradhan");
    	map.add(CommonConstants.FL_LOCALE, "en_US");
    	map.add(CommonConstants.FL_TIME_ZONE, "CET");
    	map.add(CommonConstants.FL_DATE_OF_BIRTH, "1988-03-10");
    	map.add(CommonConstants.EMAIL, "jayaramimca@gmail.com");
    	map.add(CommonConstants.PASSWORD, "12345");
    	map.add(CommonConstants.FL_SEX, "M");
    	
    	rqMap.add(CommonConstants.REQ_FORM_BLOCK, map);
    	rgList.add(rqMap);
    	
    	MultivaluedMap<String, Object> rqMap1 = new MultivaluedHashMap<String, Object>(1);
    	MultivaluedMap<String, Object> map1 = new MultivaluedHashMap<String, Object>(1);
    	
    	map1.add(CommonConstants.FL_FIRST_NAME, "123");
    	map1.add(CommonConstants.FL_LAST_NAME, "Pradhan");
    	map1.add(CommonConstants.FL_LOCALE, "en_US");
    	map1.add(CommonConstants.FL_TIME_ZONE, "CET");
    	map1.add(CommonConstants.FL_DATE_OF_BIRTH, "1988-03-10");
    	map1.add(CommonConstants.EMAIL, "jayaramimca@gmail.com");
    	map1.add(CommonConstants.PASSWORD, "12345");
    	map1.add(CommonConstants.FL_SEX, "M");
    	
    	rqMap1.add(CommonConstants.REQ_FORM_BLOCK, map1);
    	rgList.add(rqMap1);
    	
    	MultivaluedMap<String, Object> rqMap2 = new MultivaluedHashMap<String, Object>(1);
    	MultivaluedMap<String, Object> map2 = new MultivaluedHashMap<String, Object>(1);
    	
    	map2.add(CommonConstants.FL_FIRST_NAME, "123");
    	map2.add(CommonConstants.FL_LAST_NAME, "Pradhan");
    	map2.add(CommonConstants.FL_LOCALE, "en_US");
    	map2.add(CommonConstants.FL_TIME_ZONE, "CET");
    	map2.add(CommonConstants.FL_DATE_OF_BIRTH, "1988-03-10");
    	map2.add(CommonConstants.EMAIL, "jayaramimca@gmail.com");
    	map2.add(CommonConstants.PASSWORD, "12345");
    	map2.add(CommonConstants.FL_SEX, "M");
    	
    	rqMap2.add(CommonConstants.REQ_FORM_BLOCK, map2);
    	rgList.add(rqMap2);
    	
    	MultivaluedMap<String, Object> rqMap3 = new MultivaluedHashMap<String, Object>(1);
    	MultivaluedMap<String, Object> map3 = new MultivaluedHashMap<String, Object>(1);
    	
    	map3.add(CommonConstants.FL_FIRST_NAME, "123");
    	map3.add(CommonConstants.FL_LAST_NAME, "Pradhan");
    	map3.add(CommonConstants.FL_LOCALE, "en_US");
    	map3.add(CommonConstants.FL_TIME_ZONE, "CET");
    	map3.add(CommonConstants.FL_DATE_OF_BIRTH, "1988-03-10");
    	map3.add(CommonConstants.EMAIL, "jayaramimca@gmail.com");
    	map3.add(CommonConstants.PASSWORD, "12345");
    	map3.add(CommonConstants.FL_SEX, "M");
    	
    	rqMap3.add(CommonConstants.REQ_FORM_BLOCK, map3);
    	rgList.add(rqMap3);
    	
    	MultivaluedMap<String, Object> rqMap4 = new MultivaluedHashMap<String, Object>(1);
    	MultivaluedMap<String, Object> map4 = new MultivaluedHashMap<String, Object>(1);
    	
    	map4.add(CommonConstants.FL_FIRST_NAME, "123");
    	map4.add(CommonConstants.FL_LAST_NAME, "Pradhan");
    	map4.add(CommonConstants.FL_LOCALE, "en_US");
    	map4.add(CommonConstants.FL_TIME_ZONE, "CET");
    	map4.add(CommonConstants.FL_DATE_OF_BIRTH, "1988-03-10");
    	map4.add(CommonConstants.EMAIL, "jayaramimca@gmail.com");
    	map4.add(CommonConstants.PASSWORD, "12345");
    	map4.add(CommonConstants.FL_SEX, "M");
    	
    	rqMap4.add(CommonConstants.REQ_FORM_BLOCK, map4);
    	rgList.add(rqMap4);
    	
    	return rgList;
    }
    
    private List<MultivaluedMap<String, Object>> prepareVerifyTestData(){
    	List<MultivaluedMap<String, Object>> vrfyList = new ArrayList<MultivaluedMap<String, Object>>(5);
    	
    	MultivaluedMap<String, Object> map = new MultivaluedHashMap<String, Object>(1);
    	
    	map.add(CommonConstants.FL_FIRST_NAME, "123");
    	map.add(CommonConstants.FL_LAST_NAME, "Pradhan");
    	map.add(CommonConstants.FL_LOCALE, "en_US");
    	map.add(CommonConstants.FL_TIME_ZONE, "CET");
    	map.add(CommonConstants.FL_DATE_OF_BIRTH, "1988-03-10");
    	map.add(CommonConstants.EMAIL, "jayaramimca@gmail.com");
    	map.add(CommonConstants.PASSWORD, "12345");
    	map.add(CommonConstants.FL_SEX, "M");
    	
    	vrfyList.add(map);
    	
    	MultivaluedMap<String, Object> map1 = new MultivaluedHashMap<String, Object>(1);
    	
    	map1.add(CommonConstants.FL_FIRST_NAME, "123");
    	map1.add(CommonConstants.FL_LAST_NAME, "Pradhan");
    	map1.add(CommonConstants.FL_LOCALE, "en_US");
    	map1.add(CommonConstants.FL_TIME_ZONE, "CET");
    	map1.add(CommonConstants.FL_DATE_OF_BIRTH, "1988-03-10");
    	map1.add(CommonConstants.EMAIL, "jayaramimca@gmail.com");
    	map1.add(CommonConstants.PASSWORD, "12345");
    	map1.add(CommonConstants.FL_SEX, "M");
    	
    	vrfyList.add(map1);
    	
    	MultivaluedMap<String, Object> map2 = new MultivaluedHashMap<String, Object>(1);
    	
    	map2.add(CommonConstants.FL_FIRST_NAME, "123");
    	map2.add(CommonConstants.FL_LAST_NAME, "Pradhan");
    	map2.add(CommonConstants.FL_LOCALE, "en_US");
    	map2.add(CommonConstants.FL_TIME_ZONE, "CET");
    	map2.add(CommonConstants.FL_DATE_OF_BIRTH, "1988-03-10");
    	map2.add(CommonConstants.EMAIL, "jayaramimca@gmail.com");
    	map2.add(CommonConstants.PASSWORD, "12345");
    	map2.add(CommonConstants.FL_SEX, "M");
    	
    	vrfyList.add(map2);
    	
    	MultivaluedMap<String, Object> map3 = new MultivaluedHashMap<String, Object>(1);
    	
    	map3.add(CommonConstants.FL_FIRST_NAME, "123");
    	map3.add(CommonConstants.FL_LAST_NAME, "Pradhan");
    	map3.add(CommonConstants.FL_LOCALE, "en_US");
    	map3.add(CommonConstants.FL_TIME_ZONE, "CET");
    	map3.add(CommonConstants.FL_DATE_OF_BIRTH, "1988-03-10");
    	map3.add(CommonConstants.EMAIL, "jayaramimca@gmail.com");
    	map3.add(CommonConstants.PASSWORD, "12345");
    	map3.add(CommonConstants.FL_SEX, "M");
    	
    	vrfyList.add(map3);
    	
    	MultivaluedMap<String, Object> map4 = new MultivaluedHashMap<String, Object>(1);
    	
    	map4.add(CommonConstants.FL_FIRST_NAME, "123");
    	map4.add(CommonConstants.FL_LAST_NAME, "Pradhan");
    	map4.add(CommonConstants.FL_LOCALE, "en_US");
    	map4.add(CommonConstants.FL_TIME_ZONE, "CET");
    	map4.add(CommonConstants.FL_DATE_OF_BIRTH, "1988-03-10");
    	map4.add(CommonConstants.EMAIL, "jayaramimca@gmail.com");
    	map4.add(CommonConstants.PASSWORD, "12345");
    	map4.add(CommonConstants.FL_SEX, "M");
    	
    	vrfyList.add(map4);
    	
    	return vrfyList;
    }

}


