package com.uimirror.common.util;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

public class DateTimeFactoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCurrentDateTime() {
    	Assert.assertNotNull(DateTimeFactory.getCurrentDateTime());//Current date Time can't be test
    }
    @Test
    public void getCurrentDateInYMDForPerticularTimeZoneTest(){
    	Assert.assertNotNull(DateTimeFactory.getCurrentDateInYMDForPerticularTimeZone("UTC"));
    }
    @Test
    public void testCurrentDateInYYYYMMDDUsingTz(){
    	Assert.assertEquals(DateTimeFactory.getCurrentDateInYMDForPerticularTimeZone("CET"), DateTimeFactory.getCurrentDateInYMDForPerticularTimeZone("CET"));
    }
    
    @Test
    public void testConvertDateFromStringInYMDUsingTz(){
    	Assert.assertEquals(DateTimeFactory.convertToYMDDateFormatUsingTimeZone("CET", "2014-03-10"),DateTimeFactory.convertToYMDDateFormatUsingTimeZone("CET", "2014-03-10"));
    }
    @Test
    public void test1ConvertDateFromStringInMDYUsingTz(){
    	Assert.assertEquals(DateTimeFactory.convertToMDYDateFormatUsingTimeZone("CET", "2014-03-10"),DateTimeFactory.convertToMDYDateFormatUsingTimeZone("CET", "2014-03-10"));
    }
    @Test
    public void test2ConvertDateFromStringInMDYUsingTz(){
    	Assert.assertEquals(DateTimeFactory.convertToMDYDateFormatUsingTimeZone("CET", "03-10-2014"),DateTimeFactory.convertToMDYDateFormatUsingTimeZone("CET", "03-10-2014"));
    }
    @Test
    public void test3ConvertDateFromStringInMDYUsingTz(){
    	Assert.assertEquals(DateTimeFactory.convertToMDYDateFormatUsingTimeZone("CET", "30-12-2014"),DateTimeFactory.convertToMDYDateFormatUsingTimeZone("CET", "30-12-2014"));
    }
    @Test
    public void testDateGreaterThanUsingTz(){
    	Assert.assertEquals(false , DateTimeFactory.isYearGreaterEqualWithTimeZone("CET", "2014-03-10", 18));
    }
    @Test
    public void dateGreaterThanUsingTzTrueTest1(){
    	Assert.assertEquals(true , DateTimeFactory.isYearGreaterEqualWithTimeZone("CET", "10-03-2030", 12));
    }
    @Test
    public void dateGreaterThanUsingTzTrueTest2(){
    	Assert.assertEquals(true , DateTimeFactory.isYearGreaterEqualWithTimeZone("CET", "2030-10-03", 12));
    }
    @Test
    public void dateGreaterThanUsingTzTrueTest3(){
    	Assert.assertEquals(true , DateTimeFactory.isYearGreaterEqualWithTimeZone("CET", "12-30-2030", 12));
    }
    @Test
    public void dateGreaterThanUsingTzNullTest(){
    	Assert.assertEquals(false , DateTimeFactory.isYearGreaterEqualWithTimeZone(null, null, 18));
    }
    @Test
    public void testCurrentDateInYMD() {
    	Assert.assertNotNull(DateTimeFactory.getCurrentDateInYMD());
    }
    
    @Test
    public void testLastDateOfCurrentMonth() {
    	Assert.assertEquals(DateTimeFactory.getCurrentDateTime().dayOfMonth().withMaximumValue().toLocalDate(), DateTimeFactory.getLastDateOfCurrentMonth());
    }
    
    @Test
    public void testLastDateTimeOfCurrentMonth() {
    	System.out.println(DateTimeFactory.getLastDateTimeOfCurrentMonth());
    	Assert.assertNotNull(DateTimeFactory.getLastDateTimeOfCurrentMonth());
    }
    
    @Test 
    public void convertToDMYDateFormatUsingTimeZoneTest(){
    	Assert.assertNotNull(DateTimeFactory.convertToDMYDateFormatUsingTimeZone("CET", "03-05-2014"));
    }
    @Test
    public void getMinuteDifferanceTest(){
    	Assert.assertNotNull(DateTimeFactory.getMinuteDifferance("08-04-14 12:00:00"));
    }
    @Test
    public void getHourDifferanceTest(){
    	Assert.assertNotSame("17584106",DateTimeFactory.getHourDifferance("08-04-14 12:00:00"));
    }
    @Test
    public void getDateAfterSomeDaysTest(){
    	Assert.assertNotNull(DateTimeFactory.getDateAfterSomeDays(2));
    }
    @Test
    public void getCurrentDateTimeInString(){
    	Assert.assertNotNull("convertion of current date to string failed",DateTimeFactory.getCurrentDateTimeInString());
    }
    @Test
    public void getCovertedDateFromStringTest(){
    	Assert.assertEquals("2014-05-03T12:00:00.000+05:30", DateTimeFactory.getCovertedDateFromString("2014-05-03 12:00:00").toString()); 
    			
    }
    @Test 
    public void getStringFromDateTest(){
    	Assert.assertNotNull(DateTimeFactory.getStringFromDate(DateTimeFactory.getCurrentDateTime()));
    }
    @Test 
    public void getCurrentYearTest(){
    	Assert.assertEquals(2014, DateTimeFactory.getCurrentYear());
    }
    @Test
    public void getCurrentDateTimeTest(){
    	Assert.assertNotNull(DateTimeFactory.getCurrentDateTime());
    }
    @Test
    public void compareDateTimeWithCurrentDateTest(){
    	Assert.assertEquals(1,DateTimeFactory.compareDateTimeWithCurrentDate("2014-05-03 12:00:20"));
    }
    @Test
    public void getTimeZoneFromStringTest(){
    	Assert.assertEquals(DateTimeZone.UTC, DateTimeFactory.getTimeZoneFromString("UTC"));
    }
    @Test
    public void getTimeZoneFromStringNullTest(){
    	Assert.assertNull(DateTimeFactory.getTimeZoneFromString(null));
    }
}	