/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.client
 * @createdOn 27-Feb-20142:53:58 PM
 * @modifiedby Jayaram
 * @modifiedon 27-Feb-20142:53:58 PM
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
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;

import com.uimirror.api.base.JUnit4ClassRunner;
import com.uimirror.api.util.RestTemplateClientFactory;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : $
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 27-Feb-20142:53:58 PM
 * @modifiedby Jayaram
 * @modifiedon 27-Feb-20142:53:58 PM
 * ***********************************************************************
 */
//@RunWith(JUnit4ClassRunner.class)
public class RestProtocolTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() throws IOException {
	RestTemplateClientFactory template = new RestTemplateClientFactory("123", 1200, 12000);
	//byte[] res = template.getForObject("http://127.0.0.1:8080/uimirror-utilityws/cal/12", byte[].class, new Object[1]);
	byte[] res = template.getForObject("http://192.168.1.3:8181/uimirrorws-0.0.1/cal/12", byte[].class, new Object[1]);
	
	GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(res));
	BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        String outStr = "";
        String line;
        while ((line=bf.readLine())!=null) {
          outStr += line;
        }
        System.out.println("Output String lenght : " + outStr);
	System.out.println(res);
	
    }
    
    @Test
    public void testGZip() {
	RestTemplateClientFactory template = new RestTemplateClientFactory("123", 1200, 12000);
	ResponseEntity<String> res = template.getForEntity("http://127.0.0.1:8080/uimirror-utilityws/cal/12", String.class);
	System.out.println(res.getHeaders());
	System.out.println(res.getHeaders().get("Content-Encoding").toString());
	
    }

}


