package com.uimirror.api.common.user;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.uimirror.api.common.base.BaseTest;
import com.uimirror.api.common.base.JUnit4ClassRunner;
import com.uimirror.api.common.user.bean.UserFieldDocument;
import com.uimirror.common.transformer.TransformerFactory;

@RunWith(JUnit4ClassRunner.class)
public class UserDocumentStateTransformerTest  extends BaseTest{
	@Autowired
	private TransformerFactory transformerFactory;
	private static final String USR_DOC_TRANFORMER = "usrDocTransformer";
	protected static final Logger LOG = LoggerFactory.getLogger(UserDocumentStateTransformerTest.class);

	@Test
	public void testDoTransform() {
		//TODO populate the bean to test
		UserFieldDocument fieldDocument = new UserFieldDocument(null);
		transformerFactory.getTransformer(USR_DOC_TRANFORMER).doTransform(fieldDocument);
		LOG.info("Transformed Objects are {}", fieldDocument.getStates());
		Assert.assertNotNull(fieldDocument);
	}
	
	@Test
	public void testInvalidFieldTransformer(){
		try{
			UserFieldDocument fieldDocument = new UserFieldDocument(null);
			transformerFactory.getTransformer(USR_DOC_TRANFORMER).doTransform(fieldDocument);
			Assert.fail("This test should have exception.");
			
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Facts Can't empty.", e.getMessage());
		}
	}
	
	@Test
	public void testNodFieldTransformer(){
		//TODO populate the bean to test
		UserFieldDocument fieldDocument = new UserFieldDocument(null);
		transformerFactory.getTransformer(USR_DOC_TRANFORMER).doTransform(fieldDocument);
		Assert.assertNull(fieldDocument.getStates());
	}

}
