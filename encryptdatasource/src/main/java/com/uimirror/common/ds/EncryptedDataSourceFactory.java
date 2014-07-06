package com.uimirror.common.ds;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.naming.Context;
import javax.sql.DataSource;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.apache.tomcat.jdbc.pool.XADataSource;

public class EncryptedDataSourceFactory extends DataSourceFactory {

    private static final String ENCRYPT_STRING = "rorrimsdneirf";//bdaa131e6b176a16394c753ff36b2976 
    private static final Log log = LogFactory.getLog(EncryptedDataSourceFactory.class);

    private Encryptor encryptor = null;

    public EncryptedDataSourceFactory() {
	try {
	    encryptor = new Encryptor(ENCRYPT_STRING); // If you've used your own secret key,
	} catch (InvalidKeyException e) {
	    log.fatal("Error instantiating decryption class.", e);
	    throw new RuntimeException(e);
	} catch (NoSuchAlgorithmException e) {
	    log.fatal("Error instantiating decryption class.", e);
	    throw new RuntimeException(e);
	} catch (NoSuchPaddingException e) {
	    log.fatal("Error instantiating decryption class.", e);
	    throw new RuntimeException(e);
	} catch (UnsupportedEncodingException e) {
	    log.fatal("Error instantiating decryption class.", e);
	    throw new RuntimeException(e);
	}
    }

    @Override
    public DataSource createDataSource(Properties properties, Context context,
	    boolean XA) throws InvalidKeyException, IllegalBlockSizeException,
	    BadPaddingException, SQLException, NoSuchAlgorithmException,
	    NoSuchPaddingException {
	// Here we decrypt our password.
	PoolConfiguration poolProperties = EncryptedDataSourceFactory
		.parsePoolProperties(properties);
	poolProperties.setDefaultAutoCommit(Boolean.FALSE);
	poolProperties.setDefaultReadOnly(Boolean.FALSE);
	poolProperties.setTimeBetweenEvictionRunsMillis(5000);
	poolProperties.setMinEvictableIdleTimeMillis(5000);
	poolProperties.setMinIdle(0);
	poolProperties.setPassword(encryptor.decrypt(poolProperties
		.getPassword()));

	// The rest of the code is copied from Tomcat's DataSourceFactory.
	if (poolProperties.getDataSourceJNDI() != null
		&& poolProperties.getDataSource() == null) {
	    performJNDILookup(context, poolProperties);
	}
	org.apache.tomcat.jdbc.pool.DataSource dataSource = XA ? new XADataSource(
		poolProperties) : new org.apache.tomcat.jdbc.pool.DataSource(
		poolProperties);
	dataSource.createPool();

	return dataSource;
    }

}
