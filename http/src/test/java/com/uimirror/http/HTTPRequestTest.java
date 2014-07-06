package com.uimirror.http;

import java.io.IOException;
import java.net.MalformedURLException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class HTTPRequestTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testJsonResponse() throws MalformedURLException, IOException {
	String response = HTTPRequest.getString("http://api.openweathermap.org/data/2.5/weather?q=BANGALORE");
	Assert.assertNotNull(response);
    }

}
