package com.uimirror.common.encryption;

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

import com.uimirror.common.encryption.bean.DecryptMessage;

public final class Decryptor {

    private Key key;
    protected static final Logger LOG = LoggerFactory.getLogger(Encryptor.class);
    
    public static Decryptor getInstance() throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException{
	return new Decryptor();
    }
    
    public static Decryptor getInstance(String useKey) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException{
	return new Decryptor(useKey.toCharArray());
    }
    
    private Decryptor() throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException {
	this(EncryptionConstant._DEFULAT_ENCRYP_PWD);
    }

    private Decryptor(char[] defulatEncrypPwd) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException {
	this.key = generateKey(defulatEncrypPwd, EncryptionConstant._ENCRYPT_DECRYPT_SALT, EncryptionConstant._ITTREATION_COUNT, EncryptionConstant._KEY_LENGTH, EncryptionConstant._ENCRYPT_DECRYPT_ALGO);
    }
    
    /**
     * <p>
     * This will decrypt the encrypted data using AES
     * 
     * @param encryptedData
     * @return
     */
    public DecryptMessage decrypt(String encryptedData, String decryptedFor){

	Cipher cipher;
	try {
	    cipher = Cipher.getInstance(EncryptionConstant._ENCRYPT_DECRYPT_ALGO);
	    cipher.init(Cipher.DECRYPT_MODE, key);
	    return new DecryptMessage(new String(cipher.doFinal(new Base64().decode(Base64.decodeBase64(encryptedData))), "UTF-8"), encryptedData, decryptedFor) ;
	} catch (NoSuchAlgorithmException e) {
	    LOG.error("Exception Happened During decryption {}",e);
	} catch (NoSuchPaddingException e) {
	    LOG.error("Exception Happened During decryption {}",e);
	} catch (InvalidKeyException e) {
	    LOG.error("Exception Happened During decryption {}",e);
	} catch (UnsupportedEncodingException e) {
	    LOG.error("Exception Happened During decryption {}",e);
	} catch (IllegalBlockSizeException e) {
	    LOG.error("Exception Happened During decryption {}",e);
	} catch (BadPaddingException e) {
	    LOG.error("Exception Happened During decryption {}",e);
	}
	return new DecryptMessage(EMPTY_STRING, EMPTY_STRING, decryptedFor);

    }
    

    private Key generateKey(char[] useKey, byte[] salt,
	    int ittreationCount, int keyLength, String algo) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException {
	return EncryptionHelper.generateKey(useKey, salt, ittreationCount, keyLength, algo);
    }

}
