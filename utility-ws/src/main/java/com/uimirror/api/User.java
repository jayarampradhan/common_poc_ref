package com.uimirror.api;

import java.security.Principal;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : holds the user details.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 03-Mar-201411:52:27 PM
 * @modifiedby Jayaram
 * @modifiedon 03-Mar-201411:52:27 PM
 * ***********************************************************************
 */
public class User implements Principal {

    protected static final Logger LOG = LoggerFactory.getLogger(User.class);
    
    public User() {
    }

    // id
    private String userId;
    // name
    private String name;           
    // email
    private String emailAddress;   
    // roles
    private Set<Role> roles;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setName(String name) {
        this.name = name;
    }    

    @Override
    public String getName() {
	return name;
	//TODO check for null
    }

}
