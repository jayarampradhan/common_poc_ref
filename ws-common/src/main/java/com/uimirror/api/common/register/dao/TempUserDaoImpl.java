/**
 * ***********************************************************************
 * This file is developed by UIMirror Team.
 * Details of class goes like :$
 * 
 * @copyright UIMirror
 * @see com.uimirror.api.common.register.dao
 * @createdOn 09-Mar-20148:39:35 pm
 * @modifiedby Jayaram
 * @modifiedon 09-Mar-20148:39:35 pm
 * ***********************************************************************
 */

package com.uimirror.api.common.register.dao;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.uimirror.common.CommonConstants;
import com.uimirror.common.dao.SequenceDao;
import com.uimirror.common.dao.util.CommonDaoHelper;

/**
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : implemation of {@code RegisterationDao}
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 09-Mar-20148:39:35 pm
 * @modifiedby Jayaram
 * @modifiedon 09-Mar-20148:39:35 pm
 * ***********************************************************************
 */
public class TempUserDaoImpl implements TempUserDao {
    
	protected static final Logger LOG = LoggerFactory.getLogger(TempUserDaoImpl.class);
	
    @Autowired
    private DBCollection tempUserCollection;
    
    @Autowired
    private SequenceDao sequenceDao;

    public TempUserDaoImpl() {
    }
    
    /* (non-Javadoc)
     * @see com.uimirror.api.common.register.dao.TempUserDao#getUserByEmail(java.lang.String)
     */
    @SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getUserByEmail(String email)throws MongoException {
    	LOG.info("[START]- Getting user from temp collection");
    	//Where condition
    	DBObject query = new BasicDBObject(CommonConstants.EMAIL, email);
    	//Fire Query
    	DBObject res = tempUserCollection.findOne(query);
    	LOG.info("[END]- Getting user from temp collection");
		return res == null ? null : res.toMap();
	}
    
    /* (non-Javadoc)
     * @see com.uimirror.api.common.register.dao.TempUserDao#deleteUser(java.lang.String)
     */
    @Override
	public void deleteUser(String tempId) throws MongoException {
    	LOG.info("[START]- Deleting Temp user from temp collection");
    	DBObject query = new BasicDBObject(CommonConstants._ID, tempId);
    	tempUserCollection.remove(query);
    	LOG.info("[END]- Deleting Temp user from temp collection");
	}
    
    /* (non-Javadoc)
     * @see com.uimirror.api.common.register.dao.TempUserDao#createUser(java.util.Map)
     */
    @Override
	public void createUser(Map<String, Object> registerMap) throws MongoException {
    	LOG.info("[START]- Saving User into Temp collection");
    	DBObject db = new BasicDBObject();
    	db.putAll(registerMap);
    	tempUserCollection.save(db);
    	LOG.info("[END]- Saving user into Temp Collection.");
	}
    
    /* (non-Javadoc)
     * @see com.uimirror.api.common.register.dao.TempUserDao#getUser(java.lang.String)
     */
    @SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getUser(String profileId) throws MongoException {
    	LOG.info("[START]- getting user by Profile Id");
    	DBObject query = new BasicDBObject(CommonConstants._ID, profileId);
    	DBObject res = tempUserCollection.findOne(query);
    	LOG.info("[END]- getting user by Profile Id");
		return res == null ? null : res.toMap();
	}
    
    /* (non-Javadoc)
     * @see com.uimirror.api.common.register.dao.TempUserDao#getNextProfileId()
     */
    @Override
	public String getNextProfileId() throws MongoException {
		return sequenceDao.getNextSequence(CommonConstants.TEMP_USER_SEQUENCE);
	}
    
    /* (non-Javadoc)
     * @see com.uimirror.api.common.register.dao.TempUserDao#updateUserByProfileId(java.util.Map, java.lang.String)
     */
    @Override
	public void updateUserByProfileId(Map<String, Object> updates, String profileId) throws MongoException {
		LOG.info("[START]- Updating user details by profile id");
		//Build query map
		DBObject selectFields = new BasicDBObject(CommonConstants._ID, profileId);
		//Fire Update query to set fields
		tempUserCollection.findAndModify(selectFields, CommonDaoHelper.getSetQuery(updates));
		LOG.info("[END]- Updating user details by profile id");
	}
    
    @SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getUserSpecificFields(final Map<String, Object> fields, final String profileId) throws MongoException {
    	LOG.info("[START]- getting user by Profile Id");
    	//Query
    	DBObject query = new BasicDBObject(CommonConstants._ID, profileId);
    	
    	//Build Projections
    	DBObject projs = new BasicDBObject(fields.size());
    	projs.putAll(fields);
    	
    	DBObject res = tempUserCollection.findOne(query, projs);
    	LOG.info("[END]- getting user by Profile Id");
		return res == null ? null : res.toMap();
	}

    /**
     * <p>Implemntation of {@code RegisterationDao} {@link reflectiveCalls checkUserExistanceByEmail}
     */
    //TODO build the projection for singel coloumn
    @Override
    public boolean checkUserExistanceByEmail(final String email, final int hours) throws MongoException{
    	//return jdbcTemplate.queryForObject(_SL_GET_USER_BY_EMAIL_ID, args,
    	return tempUserCollection.findOne(RegisterQueries.getQueryForEmailExistanceInTemp(email, hours)) != null ? Boolean.TRUE : Boolean.FALSE;
    }
	
	public void setTempUserCollection(DBCollection tempUserCollection) {
		this.tempUserCollection = tempUserCollection;
	}

	public void setSequenceDao(SequenceDao sequenceDao) {
		this.sequenceDao = sequenceDao;
	}

}


