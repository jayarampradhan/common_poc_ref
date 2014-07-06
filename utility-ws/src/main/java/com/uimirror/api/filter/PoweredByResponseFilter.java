package com.uimirror.api.filter;

import static com.uimirror.api.CommonConstants.API_KEY;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uimirror.common.util.StringUtility;

@Provider
public class PoweredByResponseFilter implements ContainerResponseFilter{
    
    protected static final Logger LOG = LoggerFactory.getLogger(PoweredByResponseFilter.class);
    private static final String X_POWERED_BY = "X-Powered-By";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CHAR_SET = ";charset=UTF-8";
    private static final String ACCESS_C_A_O = "Access-Control-Allow-Origin";
    private static final String ACCESS_C_A_M = "Access-Control-Allow-Methods";
    private static final String ACCESS_C_A_H = "Access-Control-Allow-Headers";
    private static final String UIMIRROR = "UIMirror";
    private static final String GET_POST_DELETE = "GET, POST, DELETE, PUT, OPTIONS";
    private static final String CONT_ACC_AUTH_API = "Content-Type, Accept, Authorization, apikey";
    private static final String ALL = "*";

    public PoweredByResponseFilter() {
    }

	@Override
	public void filter(ContainerRequestContext cRequest, ContainerResponseContext cResponse) throws IOException {
		LOG.info("[START]-Adding the response body details to send back to the caller");
		cResponse.getHeaders().putSingle(ACCESS_C_A_O, ALL);
		
		cResponse.getHeaders().putSingle(ACCESS_C_A_M, GET_POST_DELETE);
		cResponse.getHeaders().putSingle(ACCESS_C_A_H, CONT_ACC_AUTH_API);
		cResponse.getHeaders().putSingle(X_POWERED_BY, UIMIRROR);
		
		//Append other details if present else don't
		MediaType contentType = cResponse.getMediaType();
		if (contentType != null){
			cResponse.getHeaders().putSingle(CONTENT_TYPE, contentType.toString() + CHAR_SET);
		}
		
		String apiKey = cRequest.getHeaderString(API_KEY);
		if(!StringUtility.checkEmptyString(apiKey)){
			cResponse.getHeaders().putSingle(API_KEY, apiKey);
		}
		
		LOG.info("[END]-Adding the response body details to send back to the caller");
	}


}
