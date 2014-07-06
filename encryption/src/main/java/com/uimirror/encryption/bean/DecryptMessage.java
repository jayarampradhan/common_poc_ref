package com.uimirror.encryption.bean;

public final class DecryptMessage {

    private String originalId, encryptdMsg, decryptFor;
	
    public DecryptMessage(String originalId, String encryptdMsg, String decryptFor) {
	super();
	this.originalId = originalId;
	this.encryptdMsg = encryptdMsg;
	this.decryptFor = decryptFor;
    }

    public String getOriginalId() {
        return originalId;
    }

    public String getEncryptdMsg() {
        return encryptdMsg;
    }

    public String getDecryptFor() {
        return decryptFor;
    }

}
