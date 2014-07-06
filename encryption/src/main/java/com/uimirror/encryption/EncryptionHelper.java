package com.uimirror.encryption;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public final class EncryptionHelper {

    public EncryptionHelper() {
    }

    /**
     * <p>This will be used by the encryptor and decryptor to genrate key 
     * to use for encryption or decryption
     * 
     * @param useKey
     * @param salt
     * @param ittreationCount
     * @param keyLength
     * @param algo
     * @return
     * @throws UnsupportedEncodingException
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public static Key generateKey(char[] useKey, byte[] salt, int ittreationCount, int keyLength, String algo) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException {
	SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	KeySpec spec = new PBEKeySpec(useKey, salt, ittreationCount, keyLength);
	SecretKey tmpKey = factory.generateSecret(spec);
	SecretKey secret = new SecretKeySpec(tmpKey.getEncoded(), algo);
	return secret;
    }

}
