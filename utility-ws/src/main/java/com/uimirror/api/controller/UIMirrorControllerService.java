/**
 * 
 */
package com.uimirror.api.controller;

import java.io.Serializable;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.uimirror.common.util.GsonUtility;

/**
 * <p>This is the main controller for the Calendar application
 * <p>This will map all the CRUD operations mapped from UI
 * @author Jayaram
 * @version 1.0
 */
@Component
@Path("/cal")
@Singleton
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class UIMirrorControllerService implements Serializable{

    private static final long serialVersionUID = 1777794661207826301L;
    protected static final Logger LOG = LoggerFactory.getLogger(UIMirrorControllerService.class);
    
    private UriInfo uriInfo;
   // private Request request;
    
//    private CalendarEventService calendarEventService;
    
    /**
     * <p>This will load the initial calendar entries 
     * of the user by profile ID, this will load for the current month
     * @param pId
     * @return
     */
    @GET
    @Path("{pId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "TEST", "TEST" })
    public String loadCalendar(@PathParam("pId") String pId){
	StopWatch stopWatch = new StopWatch();
	stopWatch.start();
	LOG.debug("Request Recived from {}, with Profile ID: {}", uriInfo.getBaseUri(), pId);
	
	//Before Calling This Initial validation of Profile ID is already done
//	List<CalendarBean> calendarBeans = calendarEventService.loadInitalCEventsByProfileId(pId);
//	CalendarBean cBean = new CalendarBean();
	//cBean.setEventId("test");
//	CalendarBean cBean1 = new CalendarBean();
	//cBean1.setEventId("test1");
//	calendarBeans.add(cBean1);
//	calendarBeans.add(cBean);
	
	//Convert and send
	String response = GsonUtility.convertToGsonString("123こんにちは");
	return response;
    }

//    @InjectParam
//    public void setCalendarEventService(CalendarEventService calendarEventService) {
//        this.calendarEventService = calendarEventService;
//    }
    @Context
    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }
//    @Context
//    public void setRequest(Request request) {
//        this.request = request;
//    }
    
}
