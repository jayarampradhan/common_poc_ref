package com.uimirror.encryption.bean;

public final class EncryptMessage {

    private String originalId, encryptdMsg, encryptFor;
	
    public EncryptMessage(String originalId, String encryptdMsg, String encryptFor) {
	super();
	this.originalId = originalId;
	this.encryptdMsg = encryptdMsg;
	this.encryptFor = encryptFor;
    }

    public String getOriginalId() {
        return originalId;
    }

    public String getEncryptdMsg() {
        return encryptdMsg;
    }

    public String getEncryptFor() {
        return encryptFor;
    }

}
