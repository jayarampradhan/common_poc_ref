package com.uimirror.api.common.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import junit.framework.Assert;

import org.junit.Test;

public class NameRegexTest {
	
	private static final String NAME = "Jayaram's";
	private static final Pattern NAME_PATTERN = Pattern.compile("(?u)^[A-Za-zàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð,.'-]+$");

	@Test
	public void nameRegextest() {
		Matcher match = NAME_PATTERN.matcher(NAME);
		if(match.matches()){
			Assert.assertTrue(Boolean.TRUE);
		}else{
			Assert.assertFalse(Boolean.TRUE);
		}
		
	}
	
	@Test
	public void multiValuedMapTest(){
		MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>(1);
		map.add("test", "test");
		map.add("test", "test");
		System.out.println(map.get("test"));
	}
	
	@Test
	public void testDevide(){
		System.out.println(4%1);
	}
	

}
