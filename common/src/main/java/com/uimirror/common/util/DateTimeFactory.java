package com.uimirror.common.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.Minutes;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeFactory {

	protected static final DateTimeFormatter dateFormatter = DateTimeFormat
			.forPattern("yyyy-MM-dd HH:mm:ss");
	protected static final DateTimeFormatter dateFormatterYMD = DateTimeFormat
			.forPattern("yyyy-MM-dd");
	
	protected static final DateTimeFormatter dateFormatterDMY = DateTimeFormat
			.forPattern("dd-MM-yyyy");
	protected static final DateTimeFormatter dateFormatterMDY = DateTimeFormat
			.forPattern("MM-dd-yyyy");

	/**
	 * <p>
	 * Get the Current date with Time in UTC
	 */
	public static DateTime getCurrentDateTime() {
		return DateTime.now(DateTimeZone.UTC);
	}
	
	/**
	 * <p>
	 * This will convert from current date to equivalent String i.e yyyy-MM-dd
	 * HH:mm:ss
	 */
	public static String getCurrentDateTimeInString() {
		return dateFormatter.print(getCurrentDateTime());
	}

	/**
	 * <p>
	 * This will get the current date in yyyy-mm-dd format using timezone
	 * specified if timezone string is empty, then return null;
	 */
	public static LocalDate getCurrentDateInYMDForPerticularTimeZone(String tz) {
		return LocalDate.now(getTimeZoneFromString(tz));
	}

	/**
	 * <p>
	 * This will convert a string into yyyy-mm-dd format using the time zone
	 * specified.
	 */
	public static LocalDate convertToYMDDateFormatUsingTimeZone(String tz,String dateString) {
		return LocalDate.parse(dateString,
				dateFormatterYMD.withZone(getTimeZoneFromString(tz)));
	}
	/**
	 * <p>
	 * This will convert a string into mm-dd-yyyy format using the time zone
	 * specified.
	 */
	public static LocalDate convertToMDYDateFormatUsingTimeZone(String tz,String dateString) {
		try{
			return LocalDate.parse(dateString,dateFormatterMDY.withZone(getTimeZoneFromString(tz)));
		}catch(Exception e){
			try{
				return LocalDate.parse(dateString,dateFormatterDMY.withZone(getTimeZoneFromString(tz)));
			}catch(Exception ex){
				return LocalDate.parse(dateString,dateFormatterYMD.withZone(getTimeZoneFromString(tz)));
			}
			}
	}

	/**
	 * <p>This will convert String into dd-mm-yyyy format using time zone specified.
	 * @param tz
	 * @param dateString
	 * @return
	 */
	public static LocalDate convertToDMYDateFormatUsingTimeZone(String tz, String dateString){
		return LocalDate.parse(dateString, dateFormatterDMY.withZone(getTimeZoneFromString(tz)));
	}
	
	/**
	 * <p>
	 * Check the specified date in yyyy-mm-dd/dd-mm-yyyy is greater than and equal to range
	 * specified
	 */
	public static boolean isYearGreaterEqualWithTimeZone(String tz, String dateString, int range) {
		
		if(StringUtility.checkEmptyString(tz,dateString)){
			return Boolean.FALSE;
		}
		
		LocalDate paresdDate = null;
		try{
			paresdDate = convertToYMDDateFormatUsingTimeZone(tz, dateString);
		}catch(Exception e){
			try{
				paresdDate = convertToDMYDateFormatUsingTimeZone(tz, dateString);
			}catch(Exception ex){
				paresdDate = convertToMDYDateFormatUsingTimeZone(tz, dateString);
				}
		}

		Years yearDiff = Years.yearsBetween(getCurrentDateInYMDForPerticularTimeZone(tz),paresdDate);

		if (yearDiff.getYears() <= range) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * <p>
	 * Get the time zone object from the string literal.
	 */
	public static DateTimeZone getTimeZoneFromString(String tz) {
		if (StringUtility.checkEmptyString(tz)) {
			return null;
		}
		return DateTimeZone.forID(tz);
	}

	/**
	 * <p>
	 * This will give the current date in YYYY-MM-DD format
	 */
	public static LocalDate getCurrentDateInYMD() {
		return LocalDate.now(DateTimeZone.UTC);
	}

	/**
	 * <p>
	 * This will give the last date time of the current Month
	 */
	public static DateTime getLastDateTimeOfCurrentMonth() {
		return getCurrentDateTime().dayOfMonth().withMaximumValue();
	}

	/**
	 * <p>
	 * This will give the last date of the current Month
	 */
	public static LocalDate getLastDateOfCurrentMonth() {
		return getCurrentDateTime().dayOfMonth().withMaximumValue()
				.toLocalDate();
	}

//	/**
//	 * <p>
//	 * This will give the datetime converted to java util datetime
//	 */
//	public static Date getSqlConvertedDateFromDateTime(DateTime dt) {
//		return dt.toDate();
//	}
//
//	/**
//	 * <p>
//	 * This will convert local date to datetime
//	 */
//	public static Date getSqlConvertedDateFromLocalDate(LocalDate d) {
//		return d.toDate();
//	}

	/**
	 * <p>
	 * This will convert from String to dateTime i.e yyyy-MM-dd HH:mm:ss
	 */
	public static DateTime getCovertedDateFromString(String s) {
		return dateFormatter.parseDateTime(s);
	}

	/**
	 * <p>
	 * This will convert from date to equivalent String i.e yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringFromDate(DateTime date) {
		return dateFormatter.print(date);
	}

	/**
	 * <p>
	 * This will give the current year
	 */
	public static int getCurrentYear() {
		return getCurrentDateTime().getYear();
	}

//	/**
//	 * <p>
//	 * This will check the date time if it falls inside time range or not.
//	 */
//	public static boolean isTimeFallsInMinute(String date, int range) {
//		int timeDiff = Minutes.minutesBetween(getCovertedDateFromString(date),
//				getCurrentDateTime()).getMinutes();
//		if (timeDiff <= range) {
//			return Boolean.TRUE;
//		}
//		return Boolean.FALSE;
//	}
//
//	/**
//	 * <p>
//	 * This will check the date time if it falls inside time range or not.
//	 */
//	public static boolean isTimeFallsInMinute(DateTime date, int range) {
//		int timeDiff = Minutes.minutesBetween(date, getCurrentDateTime())
//				.getMinutes();
//		if (timeDiff <= range) {
//			return true;
//		}
//		return false;
//	}

	/**
	 * <p>
	 * This will compare the supplied date with current date
	 */
	public static int compareDateTimeWithCurrentDate(String dateWithCompare) {
		return getCovertedDateFromString(dateWithCompare).compareTo(getCurrentDateTime());
	}

	/**
	 * <p>
	 * This will get the current date time + interval to string format. i.e yyyy-MM-dd
	 * HH:mm:ss
	 * @param days
	 */
	public static String getDateAfterSomeDays(int days) {
		return getStringFromDate(getCurrentDateTime().plusDays(days));
	}
	
	/**
	 * <p>This will get the minute difference between now and time specified.
	 * @param time
	 * @return
	 */
	public static int getMinuteDifferance(String time){
		return  Minutes.minutesBetween(getCovertedDateFromString(time), getCurrentDateTime()).getMinutes();
	}
	
	/**
	 * <p>This will get the Hour difference between now and time specified.
	 * @param time
	 * @return
	 */
	public static int getHourDifferance(String time){
		return  Hours.hoursBetween(getCovertedDateFromString(time), getCurrentDateTime()).getHours();
	}
}
