package com.uimirror.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.SystemException;

import com.uimirror.encryption.bean.DecryptMessage;
import com.uimirror.encryption.bean.EncryptMessage;

public class EncryptionTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void encryptionDecryptionTest() throws SystemException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidKeySpecException {
	String stringToencrypty = "361passion660";
	
	EncryptMessage encryptString = Encryptor.getInstance().encrypt(stringToencrypty, "test");
	System.out.println(encryptString.getEncryptdMsg());
	DecryptMessage decryptString = Decryptor.getInstance().decrypt(encryptString.getEncryptdMsg(),"test");
	System.out.println(decryptString.getOriginalId());
	Assert.assertEquals(stringToencrypty, decryptString.getOriginalId());
    }
    
    @Test
    public void encryptionDecryptionWithKeyTest() throws SystemException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidKeySpecException{
	String stringToencrypty = "Hello World";
	String key = "test";
	EncryptMessage encryptString = Encryptor.getInstance(key).encrypt(stringToencrypty,"test");
	DecryptMessage decryptString = Decryptor.getInstance(key).decrypt(encryptString.getEncryptdMsg(), "test");
	Assert.assertEquals(stringToencrypty, decryptString.getOriginalId());
    }
    
    @Test
    public void encryptionDecryptionWithKeyPublicandPrivateTest() throws SystemException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidKeySpecException{
	String stringToencrypty = "34d1";
	String privateKey = "uimirrorsecretcookielogin";
	String publicKey = "uimirrorpubliccookielogin";
	
	EncryptMessage privateEncryptString = Encryptor.getInstance(privateKey).encrypt(stringToencrypty,"test");
	System.out.println("private"+privateEncryptString.getEncryptdMsg());
	EncryptMessage publicEncryptString = Encryptor.getInstance(publicKey).encrypt(stringToencrypty,"test");
	System.out.println("public"+publicEncryptString.getEncryptdMsg());
	DecryptMessage privateDecryptString = Decryptor.getInstance(privateKey).decrypt(privateEncryptString.getEncryptdMsg(), "test");
	DecryptMessage publicDecryptString = Decryptor.getInstance(publicKey).decrypt(publicEncryptString.getEncryptdMsg(), "test");
	Assert.assertEquals(privateDecryptString.getOriginalId(), publicDecryptString.getOriginalId());
    }

}
