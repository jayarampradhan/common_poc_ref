package com.uimirror.common.encryption.util;

import static com.uimirror.common.CommonConstants.EMPTY_STRING;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uimirror.common.encryption.Encryptor;
import com.uimirror.common.encryption.bean.EncryptMessage;
import com.uimirror.common.object.ClassUtil;
import com.uimirror.common.util.StringUtility;

public final class EncryptData {

    protected static final Logger LOG = LoggerFactory.getLogger(EncryptData.class);
    public EncryptData() {
	LOG.debug("Instance of {} has been created",this.getClass());
    }
    
    /**
     * <p>Helper method to encrypt the information
     * 
     * @param rawData
     * @param key
     * @return
     */
    public static EncryptMessage encrypt(String rawData, String key, String encryptFor){
	
	EncryptMessage encryptMsg = null;
	try {
		Encryptor encryptor = StringUtility.checkEmptyString(key) ? Encryptor.getInstance() : Encryptor.getInstance(key);
	    encryptMsg = encryptor.encrypt(rawData, encryptFor);
	} catch (NoSuchAlgorithmException e) {
	    LOG.error("Encryption Failed {}.",e);
	} catch (UnsupportedEncodingException e) {
	    LOG.error("Encryption Failed {}.",e);
	} catch (InvalidKeySpecException e) {
	    LOG.error("Encryption Failed {}.",e);
	}
	
	return ClassUtil.isNullObject(encryptMsg) ? new EncryptMessage(EMPTY_STRING, EMPTY_STRING, encryptFor) : encryptMsg;
	
    }

}
