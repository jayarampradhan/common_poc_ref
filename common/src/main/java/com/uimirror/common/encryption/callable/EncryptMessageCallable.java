package com.uimirror.common.encryption.callable;

import java.util.concurrent.Callable;

import com.uimirror.common.CommonConstants;
import com.uimirror.common.encryption.bean.EncryptMessage;
import com.uimirror.common.encryption.util.EncryptData;
import com.uimirror.common.util.StringUtility;

public class EncryptMessageCallable implements Callable<Object>{

    private final String rawData;
    private final String key;
    private final String encryptFor;
	
	public EncryptMessageCallable(String rawData, String key, String encryptFor) {
		super();
		if(StringUtility.checkEmptyString(rawData, key)){
			throw new IllegalArgumentException("Encryption reuest can't be submitted as data and key is empty");
		}
		this.rawData = rawData;
		this.key = key;
		this.encryptFor = encryptFor;
	}
	
	public EncryptMessageCallable(String rawData, String encryptFor) {
		super();
		if(StringUtility.checkEmptyString(rawData)){
			throw new IllegalArgumentException("Encryption reuest can't be submitted as data is empty");
		}
		this.rawData = rawData;
		this.key = CommonConstants.EMPTY_STRING;
		this.encryptFor = encryptFor;
	}

	@Override
	public EncryptMessage call() {
		return EncryptData.encrypt(rawData, key, encryptFor);
	}

}
