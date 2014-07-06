package com.uimirror.common.util;
import java.util.HashMap;
import java.util.Map;
import com.uimirror.common.util.GsonUtility;
import junit.framework.Assert;
import org.junit.Test;
public class GsonUtilityTest {
	
	@Test
	public void testForConvertionToGsonString(){
		Map<String, String> m =new HashMap<String, String>();
		m.put("name","gourang");
		Assert.assertEquals("{\"name\":\"gourang\"}",GsonUtility.convertToGsonString(m));	
	}
	@Test
	public void emptyTestForConvertionToGsonString1(){
		Map<String, String> m =new HashMap<String, String>();
		m.put("name","");
		Assert.assertEquals("{\"name\":\"\"}",GsonUtility.convertToGsonString(m));
	}
	@Test
	public void emptyTestForConvertionToGsonString2(){
		Map<String, String> m =new HashMap<String, String>();
		m.put("","gourang");
		Assert.assertEquals("{\"\":\"gourang\"}",GsonUtility.convertToGsonString(m));
	}
	@Test
	public void emptyTestForConvertionToGsonString3(){
		Map<String, String> m =new HashMap<String, String>();
		m.put("","");
		Assert.assertEquals("{\"\":\"\"}",GsonUtility.convertToGsonString(m));
	}
}
