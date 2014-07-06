package com.uimirror.common.encryption;

public interface EncryptionConstant {

    public static final char[] _DEFULAT_ENCRYP_PWD = {'T','H','I','S',' ','I','S',' ','A',' ','L','O','N','G',' ','T','E','S','T',' ','C','A','S','E'};
    public static final String _ENCRYPT_DECRYPT_ALGO = "AES";
    public static final byte[] _ENCRYPT_DECRYPT_SALT = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y'};
    public static final int _KEY_LENGTH = 128;
    public static final int _ITTREATION_COUNT = 67531;

}
