/**
 * 
 */
package com.uimirror.common;

import static com.uimirror.common.CommonConstants.EMPTY_STRING;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : This is the base class for 
 * all the row mapper
 * <p>This class is currently not in use as we are not using MYSQL->MONGO DB
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 04-Mar-20141:06:03 AM
 * @modifiedby Jayaram
 * @modifiedon 04-Mar-20141:06:03 AM
 * ***********************************************************************
 */
@Deprecated
public abstract class BaseRowMapper<T extends Object> implements RowMapper<T> {

    public BaseRowMapper() {
	prefix=EMPTY_STRING;
    }
    
    private Set<String> setAvailableColumns;
    private ResultSet rs;
    private final String prefix;
    
    public BaseRowMapper(String prefix) {
        this.prefix = prefix;
    }
    
    private void init(ResultSet rs) throws SQLException {
        this.rs = rs;
        setAvailableColumns = new HashSet<String>(0);
        ResultSetMetaData meta = rs.getMetaData();
        for (int i = 1, n = meta.getColumnCount() + 1; i < n; i++)
            setAvailableColumns.add(meta.getColumnLabel(i));
    }
    
    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        if (setAvailableColumns == null)
            init(rs);
        return mapRowImpl(rs, rowNum);
    }
 
    public abstract T mapRowImpl(ResultSet rs, int rowNum) throws SQLException;
 
    public boolean column(String sName) {
        return (setAvailableColumns.contains(sName));
    }
 
    public Long getLong(String sName) throws SQLException {
        if (column(prefix + sName))
            return rs.getLong(prefix + sName);
        else
            return new Long(0l);
    }
    
    public DateTime getTimeStamp(String sName) throws SQLException {
        if (column(prefix + sName)){
            Timestamp timeStamp =   rs.getTimestamp(prefix+sName);
            if(timeStamp != null){
        	return new DateTime(timeStamp.getTime());
            }
            return null;
        }
        else{
            return null;
        }
            
    }
 
    public Integer getInteger(String sName) throws SQLException {
        if (column(prefix + sName))
            return rs.getInt(prefix + sName);
        else
            return new Integer(0);
    }
    
    /**
     * <p>this will return the column value whose data type is 
     * String or Char
     * 
     * @param sName
     * @return
     * @throws SQLException
     */
    public String getString(String sName) throws SQLException {
        if (column(prefix + sName))
            return rs.getString(prefix + sName);
        else
            return EMPTY_STRING;
    }

}
