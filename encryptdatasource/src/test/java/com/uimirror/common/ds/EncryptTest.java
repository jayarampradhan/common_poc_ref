package com.uimirror.common.ds;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.uimirror.common.ds.Encryptor;

public class EncryptTest {

    private Encryptor encryptor;
    @Before
    public void setUp() throws Exception {
	encryptor = new Encryptor("rorrimsdneirf");
    }

    @Test
    public void testEncrypt() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
	String encryptedText = encryptor.encrypt("root");
	Assert.assertEquals("bdaa131e6b176a16394c753ff36b2976", encryptedText);
    }
    
    @Test
    public void testDecrypt() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
	String plainText = encryptor.decrypt("bdaa131e6b176a16394c753ff36b2976");
	Assert.assertEquals("root", plainText);
    }

}
