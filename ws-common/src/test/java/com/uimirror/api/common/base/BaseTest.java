package com.uimirror.api.common.base;

import java.beans.PropertyVetoException;

import javax.naming.NamingException;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@ContextConfiguration(locations = { "classpath:common-test-beans.xml" })
public class BaseTest {
    
    static {
	final SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
	final ComboPooledDataSource uimirrorDataSource = new ComboPooledDataSource();
	
	final Logger logger = LoggerFactory.getLogger(BaseTest.class);
	try {

	    uimirrorDataSource.setDriverClass("com.mysql.jdbc.Driver");
	} catch (PropertyVetoException e) {

	    logger.debug("Creating the data source and error occoured" + e);
	}

	uimirrorDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/uimirror");
	uimirrorDataSource.setUser("root");
	uimirrorDataSource.setPassword("root");
	uimirrorDataSource.setMaxPoolSize(1);
	uimirrorDataSource.setMaxPoolSize(15);
	uimirrorDataSource.setAcquireIncrement(3);
	uimirrorDataSource.setMaxStatementsPerConnection(100);
	uimirrorDataSource.setAutomaticTestTable("c3p0_test_table");
	uimirrorDataSource.setNumHelperThreads(20);
	builder.bind("java:comp/env/jdbc/uimirror", uimirrorDataSource);

	try {
	    builder.activate();
	} catch (IllegalStateException e) {
	    logger.debug(e.toString());
	} catch (NamingException e) {
	    logger.debug(e.toString());
	}

    }

    @Before
    public void setUp() throws Exception {
    }

}
