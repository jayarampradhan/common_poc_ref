/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.filter.dao
 * @createdOn 04-Mar-201412:59:57 AM
 * @modifiedby Jayaram
 * @modifiedon 04-Mar-201412:59:57 AM
 * ***********************************************************************
 */

package com.uimirror.api.filter.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.uimirror.api.User;
import com.uimirror.common.BaseRowMapper;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : $
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 04-Mar-201412:59:57 AM
 * @modifiedby Jayaram
 * @modifiedon 04-Mar-201412:59:57 AM
 * ***********************************************************************
 */
@Repository
public class UserAuthenticationDaoImpl implements UserAuthenticationDao {

    //private JdbcTemplate jdbcTemplate;
    
    public UserAuthenticationDaoImpl() {
    }

    @Override
    public User getUerByAPIKey(final Object[] args) throws SQLException{
	return null;//jdbcTemplate.queryForObject(_SL_GET_USER_BY_API_KEY, args, new UserRowMapper());
    }
    
    /**
     * <p>Inner class row mapper which will translate result set into dao bean.
     * 
     * @author Jayaram
     *
     */
    private static final class UserRowMapper extends BaseRowMapper<User>{
	@Override
	public User mapRowImpl(ResultSet rs, int rowNum) throws SQLException {
	    User user = new User();
	    user.setName("");
	    user.setEmailAddress("");
	    user.setRoles(null);
	    user.setUserId("");
	    return user;
	}
	
    }
    
//    @Autowired
//    public void setUimirrorDataSource(DataSource uimirrorDataSource) {
//        this.jdbcTemplate = new JdbcTemplate(uimirrorDataSource);
//    }

}


