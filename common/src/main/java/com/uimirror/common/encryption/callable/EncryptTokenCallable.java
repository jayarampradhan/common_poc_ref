package com.uimirror.common.encryption.callable;

import java.util.concurrent.Callable;

import com.uimirror.common.CommonConstants;
import com.uimirror.common.encryption.bean.EncryptMessage;
import com.uimirror.common.encryption.util.EncryptData;
import com.uimirror.common.encryption.util.EncryptionUtil;
import com.uimirror.common.util.StringUtility;

public class EncryptTokenCallable implements Callable<Object>{

    private final int size;
    private final String key;
    private final String encryptFor;
	
	public EncryptTokenCallable(int size, String key, String encryptFor) {
		super();
		if(StringUtility.checkEmptyString(key) || size < 0){
			throw new IllegalArgumentException("Encryption reuest can't be submitted as data and key is empty");
		}
		this.size = size;
		this.key = key;
		this.encryptFor = encryptFor;
	}
	
	public EncryptTokenCallable(int size, String encryptFor) {
		super();
		if(size < 0){
			throw new IllegalArgumentException("Encryption reuest can't be submitted as data and key is empty");
		}
		this.size = size;
		this.key = CommonConstants.EMPTY_STRING;
		this.encryptFor = encryptFor;
	}

	@Override
	public EncryptMessage call() throws Exception {
		return EncryptData.encrypt(EncryptionUtil.getRandomId(size), key, encryptFor);
	}

}
