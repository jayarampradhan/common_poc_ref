package com.uimirror.common.encryption.util;

import static com.uimirror.common.CommonConstants.EMPTY_STRING;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uimirror.common.encryption.Decryptor;
import com.uimirror.common.encryption.bean.DecryptMessage;
import com.uimirror.common.object.ClassUtil;
import com.uimirror.common.util.StringUtility;

public final class DecryptData {

    protected static final Logger LOG = LoggerFactory.getLogger(DecryptData.class);
    public DecryptData() {
	LOG.debug("Instance of {} has been created",this.getClass());
    }
    
    /**
     * <p>Helper class to decrypt the encrypted message
     * on the basics of decryption key and value
     * @param encryptedText
     * @param key
     * @param decryptFor
     * @return
     */
    public static DecryptMessage decrypt(String encryptedText, String key, String decryptFor){
	
	DecryptMessage decryptMessage = null;
	try {
		Decryptor decryptor = StringUtility.checkEmptyString(key) ? Decryptor.getInstance() : Decryptor.getInstance(key);
	    decryptMessage = decryptor.decrypt(encryptedText, decryptFor);
	} catch (NoSuchAlgorithmException e) {
	    LOG.error("Decryption Failed {}.",e);
	} catch (UnsupportedEncodingException e) {
	    LOG.error("Decryption Failed {}.",e);
	} catch (InvalidKeySpecException e) {
	    LOG.error("Decryption Failed {}.",e);
	}
	return ClassUtil.isNullObject(decryptMessage) ? new DecryptMessage(EMPTY_STRING, EMPTY_STRING, decryptFor) : decryptMessage;
	
    }

}
