package com.uimirror.encryption.callable;

import java.util.concurrent.Callable;

import com.uimirror.encryption.bean.EncryptMessage;
import com.uimirror.encryption.util.EncryptData;
import com.uimirror.encryption.util.EncryptionUtil;

public class EncryptTokenCallable implements Callable<Object>{

    private int size;
    private String key;
    private String encryptFor;
	
    public EncryptTokenCallable(int size, String key, String encryptFor){
	super();
	this.size = size;
	this.key = key;
	this.encryptFor = encryptFor;
    }

    @Override
    public EncryptMessage call() throws Exception {
	return EncryptData.encrypt(EncryptionUtil.getRandomId(size), key, encryptFor);
    }

}
