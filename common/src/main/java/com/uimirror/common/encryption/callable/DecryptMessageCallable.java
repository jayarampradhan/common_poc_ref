package com.uimirror.common.encryption.callable;

import java.util.concurrent.Callable;

import com.uimirror.common.CommonConstants;
import com.uimirror.common.encryption.bean.DecryptMessage;
import com.uimirror.common.encryption.util.DecryptData;
import com.uimirror.common.util.StringUtility;

public class DecryptMessageCallable implements Callable<Object>{

    private final String encryptText;
    private final String key;
    private final String decryptFor;
	
	public DecryptMessageCallable(String encryptText, String key, String decryptFor) {
		super();
		if(StringUtility.checkEmptyString(encryptText, key)){
			throw new IllegalArgumentException("Decryption reuest can't be submitted as data and key is empty");
		}
		this.encryptText = encryptText;
		this.key = key;
		this.decryptFor = decryptFor;
	}
	
	public DecryptMessageCallable(String encryptText, String decryptFor) {
		super();
		if(StringUtility.checkEmptyString(encryptText)){
			throw new IllegalArgumentException("Decryption reuest can't be submitted as data is empty");
		}
		this.encryptText = encryptText;
		this.key = CommonConstants.EMPTY_STRING;
		this.decryptFor = decryptFor;
	}

	@Override
	public DecryptMessage call() {
		return DecryptData.decrypt(encryptText, key, decryptFor);
	}

}
