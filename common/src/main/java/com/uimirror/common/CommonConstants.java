package com.uimirror.common;

public interface CommonConstants {

    /**
     * <p>
     * Number Constants
     */
    public static final int NUMBER_0 = 0;

    /**
     * <p>
     * This will append in case multiple get param
     */
    public static final String GET_PARAM = "&";
    /**
     * <p>
     * This will for ?
     */
    public static final String GET_ATTRIBUTE = "?";

    /**
     * <p>
     * Empty String
     */
    public static final String EMPTY_STRING = "";
    
    public static final String NEW_LINE = "\n";
    
    
    public static final int COOKIE_TOKEN_SIZE = 5;
    //Default token validity is one day
    public static final int COOKIE_TOKEN_INTERVAL = 60*24;
    public static final int COOKIE_TOKEN_INTERVAL_LONG = 60*24*14;
    
    //Matchematics helper
    public static final String STRING_0 = "0";
    public static final String YES = "Y";
    public static final String NO = "N";
    public static final String UTF_8 = "UTF-8";
    
    //Web Service Common message block
    public static final String AUTHENTICATION_BLOCK = "auth";
    public static final String REQ_FORM_BLOCK = "form";
    public static final String REQ_CLIENT_FORM_BLOCK = "client";
    public static final String RES_DATA_BLOCK = "rsdata";
    
    //Web Service Common Terminology
    public static final String COOKIE = "cookie";
    public static final String FORM = "form";
    public static final String SOCIAL = "social";
    public static final String TYPE = "type";
    public static final String BASIC = "basic";
    public static final String DOB_SEPRATOR = "-";
    public static final String PUBLIC = "public";
    public static final String ONLY_ME = "onlyme";
    public static final String FRIENDS = "friend";
    
    
    //Web Service Unauthorized response
    public static final String UN_AUTHORIZED_RS_CD = "401";
    public static final String FORBIDDEN_RS_CD = "403";
    public static final String UN_AUTHORIZED_RS_MSG = "Possibly User has logged out, re-login to access.";
    
    //Web Service Common response
    public static final String SUCESS_RESPONSE_CD = "200";
    public static final String UN_MODIFIED_RESPONSE_CD = "304";
    
    public static final String SUCESS_RESPONSE_MSG = "Request Completed successfully.";
    
    //WEB service profile id
    public static final String PRF_ID = "prfid";
    public static final String EMAIL = "email";
    public static final String USER_ID = "user_id";
    public static final String USER_ID_TYPE = "id_type";
    public static final String KEEP_ME_LGN = "keepmelogin";
    public static final String PASSWORD = "pwd";
    
    
    //Web service validation message
    public static final String VALIDATION_INVLDKEY = "INVLDKEY";
    public static final String VALIDATION_INVLDMSG = "INVLDMSG";
    public static final String VALIDATION_INVLDSUG = "INVLDSUG";
    public static final String VALIDATION_FAILD_RS_CD = "406";
    //WEB Service Response Message
    public static final String MSG = "MSG";
    //WebService Register validation failed constant
    public static final String FIELD_AND_SEP = " And ";
    public static final String FIELD_COMMA_SEP = ", ";
    public static final String INVALID_DATA_RS_CD = "400";
    public static final String INTERNAL_ERR_RS_CD = "500";
    public static final String INVALID_DATA_RS_MSG = "OOps Something went wrong!!! Suggestion try to change the data and try again.";
    public static final String INTERNAL_ERR_RS_MSG = "OOps Something went wrong!!! We are working on to get that for you ASAP.";
    public static final String REG_SUCESS_RS_CD = "200";
    public static final String REG_TMP_PRF_ID = "tmpprfid";
    public static final int REG_LNK_TOKEN_LN = 5;
    public static final String REG_LNK_TOKEN_LN_ENC_KEY = "uimirrorpubliclinkverify";
    public static final int REG_FORM_TOKEN_LN = 4;
    
    //Verification Map Field
    public static final String VRF_REF = "ref";
    public static final String VRF_TEMP_PRF_ID = "tmpprfid";
    public static final String VRF_TOKEN = "token";
    
    //Login Map Field
    public static final String CLIENT_META = "client_meta";
    public static final String IP = "ip";
    public static final String AUTH_TYPE = "type";
    public static final String AUTH_ID = "authid";
    public static final String PRV_AUTH_ID = "prvauthid";
    public static final String AUTH_TOKEN = "token";
    public static final String PRV_AUTH_TOKEN = "prvtoken";
    public static final String AUTH_TOKEN_EXPIRES_ON = "expiry";
    public static final String AUTH_TOKEN_INTERVAL = "interval";
    
    //WebService Verification validation failed constant
    public static final String VRFY_ERR_RS_CD = "400";
    public static final String VRFY_ERR_RS_MSG = "OOps Something went wrong!!! we are working on to get that for you ASAP.";
    public static final String VRFY_SUCESS_RS_CD = "200";
    
    //Webservice Resend email token validation failed constant
    public static final String RSEND_MAIL_SUCESS_RS_CD = "200";
    
    //Webservice Login validation failed constant
    //public static final String AUTHENTICATION_INVALID_DATA_RS_CD = "400";
    public static final String AUTHENTICATION_INTERNAL_ERR_RS_CD = "500";
    public static final String AUTHENTICATION_INTERNAL_ERR_RS_MSG = "OOps Something went wrong!!! We are working on to get that for you ASAP.";
    //public static final String AUTHENTICATION_INVALID_DATA_RS_MSG = "OOps Something went wrong!!! Suggestion try to change the data and try again.";
    public static final String AUTHENTICATION_SUCESS_RS_CD = "200";
    public static final String SAVE_SESSION = "Y";

    //Common Underscore
    public static final String UNDERSCORE = "_";
    
    //Common response code;
    public static final String RESPONSE_CODE = "RESCD";
    
    
    //DAO Sequence names
    public static final String USER_SEQUENCE = "user";
    public static final String TEMP_USER_SEQUENCE = "tempuser";
    public static final String SESSION_SEQUENCE = "session";
    
    //DAO ID Filed
    public static final String _ID = "_id";
    public static final String _POLTICAL_VIEW = "politicalview";
    public static final String _ABOUT_ME = "profile.social.bio";
    //ANy db fields processedded with FL_ and identifier for that perticular filed will be start with ID_
    public static final String FL_EMAIl = "email";
    public static final String ID_EMAIl = "E";
    public static final String FL_MOBILE = "mobile";
    public static final String ID_MOBILE = "M";
    public static final String FL_PASSWORD = "pwd";
    public static final String FL_USER_ID = "uid";
    public static final String FL_OBJECT = "object";
    public static final String FL_DEVICE = "device";
    public static final String ID_DEVICE = "devicemeta";
    public static final String FL_ON = "on";
    public static final String FL_REGISTRY = "registry";
    //REGISTERATION DB FIELDS
    public static final String FL_FIRST_NAME = "fname";
    public static final String FL_LAST_NAME = "lname";
    public static final String FL_LOCALE = "locale";
    public static final String FL_TIME_ZONE = "tz";
    public static final String FL_CURRENCY = "currency";
    public static final String FL_DATE_OF_BIRTH = "dob";
    public static final String FL_SEX = "sex";
    public static final String FL_ACTIVE = "active";
    public static final String FL_LN_TOKEN = "lntoken";
    public static final String FL_FORM_TOKEN = "token";
    public static final String FL_APP = "app";
    public static final String FL_DATE = "date";
    public static final String FL_MONTH = "month";
    public static final String FL_YEAR = "year";
    public static final String FL_BD_DATE_PRIVACY = "dateprivacy";
    public static final String FL_BD_YEAR_PRIVACY = "yearprivacy";
    
    //Privacy Fileds
    public static final String FL_PRIVACY = "privacy";
    public static final String FL_PRIVACY_CUSTOM = "custom";
    public static final String FL_PRIVACY_SHARE_WITH = "with";
    public static final String FL_PRIVACY_SHARE_NOT_WITH = "notwith";
    
    public static final String FL_ALLOW_FRIEND_REQ = "friendrequest";
    public static final String FL_ALLOW_INBOX_MSG = "msg";
    public static final String FL_SEARCH_ME = "search";
    public static final String FL_EMAIL_SEARCH_ME = "email";
    public static final String FL_MOBILE_SEARCH_ME = "mobile";
    public static final String FL_SEE_MY_POST = "post";
    

    //USER Basic Fields
    public static final String FL_NAME = "name";
    
    //MONGO COMMANDS
    public static final String _UNSET = "$unset";
    public static final String _SET = "$set";
    public static final String _EACH = "$each";
    public static final String _ADDTOSET = "$addToSet";
    public static final String _PULL = "$pull";
    public static final String COMMAND_PREFIX = "$";
    
    //Web-service Update DB fields
    public static final String COMMANDS = "commands";
    public static final String FIELDS = "fields";
    public static final String VALUES = "values";
    public static final String COMMAND_SEP = "separator";

}
