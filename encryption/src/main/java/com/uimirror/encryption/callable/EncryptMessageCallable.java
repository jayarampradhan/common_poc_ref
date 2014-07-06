package com.uimirror.encryption.callable;

import java.util.concurrent.Callable;

import com.uimirror.encryption.bean.EncryptMessage;
import com.uimirror.encryption.util.EncryptData;

public class EncryptMessageCallable implements Callable<Object>{

    private String rawData;
    private String key;
    private String encryptFor;
	
    public EncryptMessageCallable(String rawData, String key, String encryptFor){
	super();
	this.rawData = rawData;
	this.key = key;
	this.encryptFor = encryptFor;
    }

    @Override
    public EncryptMessage call() {
	return EncryptData.encrypt(rawData, key, encryptFor);
    }

}
