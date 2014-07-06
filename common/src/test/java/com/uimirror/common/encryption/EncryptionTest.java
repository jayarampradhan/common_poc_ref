package com.uimirror.common.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uimirror.common.LoadExternalJson;
import com.uimirror.common.base.BaseTest;
import com.uimirror.common.base.JUnit4ClassRunner;
import com.uimirror.common.encryption.bean.DecryptMessage;
import com.uimirror.common.encryption.bean.EncryptMessage;
import com.uimirror.common.util.StringUtility;

@RunWith(JUnit4ClassRunner.class)
public class EncryptionTest extends BaseTest{
	
	protected static final Logger LOG = LoggerFactory.getLogger(EncryptionTest.class);
	private List<Map<String, Object>> validData;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		validData = (List<Map<String, Object>>) LoadExternalJson.loadData("encryption/en_valid_data_set_1.txt", List.class);
	}

	@Test
	public void encryptionDecryptionTest() throws SystemException,
			InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException,
			InvalidKeySpecException {
		for(Map<String, Object> map : validData){
			EncryptMessage encryptString = null;
			DecryptMessage decryptString = null;
			if(StringUtility.checkEmptyString((String)map.get("key"))){
				encryptString = Encryptor.getInstance().encrypt((String)map.get("plain"), "test");
				decryptString = Decryptor.getInstance().decrypt((String)map.get("encrypt"), "test");
			}else{
				encryptString = Encryptor.getInstance((String)map.get("key")).encrypt((String)map.get("plain"), "test");
				decryptString = Decryptor.getInstance((String)map.get("key")).decrypt((String)map.get("encrypt"), "test");
			}
			Assert.assertNotNull(encryptString);
			Assert.assertNotNull(decryptString);
			Assert.assertEquals(encryptString.getOriginalId(), decryptString.getOriginalId());
			
		}
	
	}

}
