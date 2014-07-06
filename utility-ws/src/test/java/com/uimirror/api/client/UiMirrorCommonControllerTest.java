/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.client
 * @createdOn 05-Mar-20141:30:29 AM
 * @modifiedby Jayaram
 * @modifiedon 05-Mar-20141:30:29 AM
 * ***********************************************************************
 */

package com.uimirror.api.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.uimirror.api.util.RestTemplateClientFactory;
import com.uimirror.common.util.DateTimeFactory;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : will test all the behaviors of the common
 * service api.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 05-Mar-20141:30:29 AM
 * @modifiedby Jayaram
 * @modifiedon 05-Mar-20141:30:29 AM
 * ***********************************************************************
 */
//@RunWith(JUnit4ClassRunner.class)
public class UiMirrorCommonControllerTest {
    
    protected static final Logger LOG = LoggerFactory.getLogger(UiMirrorCommonControllerTest.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void registerUserTest() throws IOException {
	RestTemplateClientFactory template = new RestTemplateClientFactory("123", 1200, 12000);
	
	HttpHeaders  headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	MultiValueMap<String, Object> user = new LinkedMultiValueMap<String,Object>(1);
	user.add("fName", "Jayaram");
	user.add("lName", "Pradhan");
	user.add("lName", "Pradhan1");
	user.add("test", DateTimeFactory.getCurrentDateTimeInString());
	HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(user, headers);
	byte[] res = template.postForObject("http://192.168.1.3/register",httpEntity, byte[].class);
	//byte[] res = template.postForObject("http://127.0.0.1:8080/uimirror-utilityws/register",httpEntity, byte[].class);
	GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(res));
	BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        String outStr = "";
        String line;
        while ((line=bf.readLine())!=null) {
          outStr += line;
        }
        LOG.debug("Output String lenght : {}", outStr);
    }

}


