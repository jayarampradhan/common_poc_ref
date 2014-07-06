package com.uimirror.encryption.callable;

import java.util.concurrent.Callable;

import com.uimirror.encryption.bean.DecryptMessage;
import com.uimirror.encryption.util.DecryptData;

public class DecryptMessageCallable implements Callable<Object>{

    private String encryptText;
    private String key;
    private String decryptFor;
	
    public DecryptMessageCallable(String encryptText, String key, String decryptFor){
	super();
	this.encryptText = encryptText;
	this.key = key;
	this.decryptFor = decryptFor;
    }

    @Override
    public DecryptMessage call(){
	return DecryptData.decrypt(encryptText, key, decryptFor);
    }

}
