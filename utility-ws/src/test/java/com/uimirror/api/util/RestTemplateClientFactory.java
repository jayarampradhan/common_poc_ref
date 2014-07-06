/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.util
 * @createdOn 27-Feb-20141:47:59 PM
 * @modifiedby Jayaram
 * @modifiedon 27-Feb-20141:47:59 PM
 * ***********************************************************************
 */

package com.uimirror.api.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : $
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 27-Feb-20141:47:59 PM
 * @modifiedby Jayaram
 * @modifiedon 27-Feb-20141:47:59 PM
 * ***********************************************************************
 */

public class RestTemplateClientFactory extends RestTemplate{
    
    protected static final Logger LOG = LoggerFactory.getLogger(RestTemplateClientFactory.class);
    
    public RestTemplateClientFactory(String apiKey, int connectionTimeOut, int readTimeOut) {
	super(buildRequestFactory(apiKey, connectionTimeOut, readTimeOut));
	addMessageConverters();
	
    }
    
    protected void addMessageConverters(){
	// empty list created
	List<HttpMessageConverter<?> > messageConverters = new ArrayList< HttpMessageConverter<?> >(2);   
	//messageConverters.add( new GsonHttpMessageConverter() );      
	messageConverters.add( new StringHttpMessageConverter() );
	messageConverters.add( new ByteArrayHttpMessageConverter() );
	messageConverters.add(new FormHttpMessageConverter());
	this.setMessageConverters(messageConverters);
    }
    
    protected static SimpleClientHttpRequestFactory buildRequestFactory(final String apiKey,final int connectionTimeOut,final int readTimeOut){
	SimpleClientHttpRequestFactory s = new SimpleClientHttpRequestFactory() {
		
	    @Override
	    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {

		super.prepareConnection(connection, httpMethod);
		connection.setRequestProperty("apiKey", null);
		connection.setConnectTimeout(connectionTimeOut);
		connection.setReadTimeout(readTimeOut);
		connection.setUseCaches(Boolean.TRUE);
		connection.setRequestProperty("Accept-Encoding", "gzip,deflate");
		//connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
	    }
		
	};
	return s;
    }
    
    

}


