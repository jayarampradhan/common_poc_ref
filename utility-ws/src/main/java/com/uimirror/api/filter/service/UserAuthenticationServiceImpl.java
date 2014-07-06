/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.filter.service
 * @createdOn 04-Mar-201412:54:22 AM
 * @modifiedby Jayaram
 * @modifiedon 04-Mar-201412:54:22 AM
 * ***********************************************************************
 */

package com.uimirror.api.filter.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uimirror.api.Role;
import com.uimirror.api.Session;
import com.uimirror.api.User;
import com.uimirror.common.util.DateTimeFactory;
import com.uimirror.common.util.StringUtility;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : Implementation for creating a session 
 * for the the user makeing the API call.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 04-Mar-201412:54:22 AM
 * @modifiedby Jayaram
 * @modifiedon 04-Mar-201412:54:22 AM
 * ***********************************************************************
 */

public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    protected static final Logger LOG = LoggerFactory.getLogger(UserAuthenticationServiceImpl.class);
    
//    @Autowired
//    private UserAuthenticationDao userAuthenticationDao;
    
    public UserAuthenticationServiceImpl() {
    }

    @Override
    //@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
    public Session getUserSessionByAPIKey(String apiKey) {
	LOG.info("[START]-Getting the user and session details by API key.");
	//Step-1 if API key null return null
	if(StringUtility.checkEmptyString(apiKey)){
	    return null;
	}
	//Step-2 get the user details from data base
	//TODO un-comment the below line when data base call is ready.
	//User user = userAuthenticationDao.getUerByAPIKey(QueryBuilder.getInstance().buildPlaceHolderParameter(apiKey));
	//TODO FIXME DELETE this manual creation of user 
	User user = new User();
	user.setEmailAddress("Jayaramimca@gmail.com");
	user.setName("Jayaram Pradhan");
	Set<Role> roles = new HashSet<Role>(1);
	roles.add(Role.ADMIN);
	user.setRoles(roles);
	/////////////////////
	
	LOG.info("[END]-Getting the user and session details by API key.");
	return user == null ? null : buildSession(user);
    }
    
    private Session buildSession(final User user){
	Session session = new Session(user);
	session.setActive(Boolean.TRUE);
	session.setCreateTime(DateTimeFactory.getCurrentDateTime());
	session.setSecure(Boolean.TRUE);
	return session;
    }

}


