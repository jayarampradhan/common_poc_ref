package com.uimirror.api;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : holds the session bean details of the user.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 03-Mar-201411:48:57 PM
 * @modifiedby Jayaram
 * @modifiedon 03-Mar-201411:48:57 PM
 * ***********************************************************************
 */
public class Session implements Serializable {

    private static final long serialVersionUID = -6785222566279581810L;
    protected static final Logger LOG = LoggerFactory.getLogger(Session.class);
    
    // id
    private String sessionId;   
    // user
    private final User user;      
    // session active?
    private boolean active;    
    // session secure?
    private boolean secure;    
    // session create time
    private DateTime createTime;    
    // session last use time
    private DateTime lastAccessedTime;
    
    public Session(User user) {
	this.user = user;
    }

    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean isSecure() {
        return secure;
    }
    public void setSecure(boolean secure) {
        this.secure = secure;
    }
    public DateTime getCreateTime() {
        return createTime;
    }
    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }
    public DateTime getLastAccessedTime() {
        return lastAccessedTime;
    }
    public void setLastAccessedTime(DateTime lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }
    public User getUser() {
        return user;
    }

}
