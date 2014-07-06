package com.uimirror.api.common.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;

public class AnyCharecterTest {

	private static final String ANY = "as\nas";
	private static final String CHAR_LNGTH = "asas1";
	private static final Pattern ANY_PATTERN = Pattern.compile("(?s).+");
	private static final Pattern ANY_PATTERN_LNGTH = Pattern.compile(".{5}");
	@Test
	public void testAnyCharecter() {
		Matcher match = ANY_PATTERN.matcher(ANY);
		if(match.matches()){
			Assert.assertTrue(Boolean.TRUE);
		}else{
			Assert.assertFalse(Boolean.TRUE);
		}
	}
	
	@Test
	public void testAnyCharecterWithLength(){
		Matcher match = ANY_PATTERN_LNGTH.matcher(CHAR_LNGTH);
		if(match.matches()){
			Assert.assertTrue(Boolean.TRUE);
		}else{
			Assert.assertFalse(Boolean.TRUE);
		}
	}

}
