package com.uimirror.encryption;

import static com.uimirror.encryption.EncryptionConstant._DEFULAT_ENCRYP_PWD;
import static com.uimirror.encryption.EncryptionConstant._ENCRYPT_DECRYPT_ALGO;
import static com.uimirror.encryption.EncryptionConstant._ENCRYPT_DECRYPT_SALT;
import static com.uimirror.encryption.EncryptionConstant._ITTREATION_COUNT;
import static com.uimirror.encryption.EncryptionConstant._KEY_LENGTH;
import static com.uimirror.common.CommonConstants.EMPTY_STRING;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uimirror.encryption.bean.EncryptMessage;

public final class Encryptor {

    private Key key;
    protected static final Logger LOG = LoggerFactory.getLogger(Encryptor.class);
    
    public static Encryptor getInstance() throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException{
	return new Encryptor(); 
    }
    
    public static Encryptor getInstance(final String encryptPwd) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException{
	return new Encryptor(encryptPwd.toCharArray()); 
    }
    
    private Encryptor() throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException {
	this(_DEFULAT_ENCRYP_PWD);
    }

    private Encryptor(char[] defulatEncrypPwd) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException {
	this.key = generateKey(defulatEncrypPwd, _ENCRYPT_DECRYPT_SALT, _ITTREATION_COUNT, _KEY_LENGTH, _ENCRYPT_DECRYPT_ALGO);
    }

    /**
     * <p>This will help to encrypt the data using AES
     * @param data
     * @param encryptFor
     * @return
     */
    public EncryptMessage encrypt(final String data, final String encryptFor){

	try {
	    Cipher cipher = Cipher.getInstance(_ENCRYPT_DECRYPT_ALGO);
	    cipher.init(Cipher.ENCRYPT_MODE, key);
	    return new EncryptMessage(data, Base64.encodeBase64URLSafeString(new Base64().encode(cipher.doFinal(data.getBytes()))), encryptFor);
	} catch (InvalidKeyException e) {
	    LOG.error("Encryption error happened {}",e);
	} catch (NoSuchAlgorithmException e) {
	    LOG.error("Encryption error happened {}",e);
	} catch (NoSuchPaddingException e) {
	    LOG.error("Encryption error happened {}",e);
	} catch (IllegalBlockSizeException e) {
	    LOG.error("Encryption error happened {}",e);
	} catch (BadPaddingException e) {
	    LOG.error("Encryption error happened {}",e);
	}
	return new EncryptMessage(EMPTY_STRING, EMPTY_STRING, encryptFor);

    }

    private Key generateKey(char[] useKey, byte[] salt,
	    int ittreationCount, int keyLength, String algo) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException {
	return EncryptionHelper.generateKey(useKey, salt, ittreationCount, keyLength, algo);
    }

}
