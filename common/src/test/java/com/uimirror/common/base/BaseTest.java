package com.uimirror.common.base;

import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "classpath:common-test-beans.xml" })
public class BaseTest {

	static {
		// final SimpleNamingContextBuilder builder = new
		// SimpleNamingContextBuilder();

		//final Logger logger = LoggerFactory.getLogger(BaseTest.class);
		// try {
		//
		// uimirrorDataSource.setDriverClass("com.mysql.jdbc.Driver");
		// } catch (PropertyVetoException e) {
		//
		// logger.debug("Creating the data source and error occoured" + e);
		// }
		//
		// uimirrorDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/uimirror");
		// uimirrorDataSource.setUser("root");
		// uimirrorDataSource.setPassword("root");
		// uimirrorDataSource.setMaxPoolSize(1);
		// uimirrorDataSource.setMaxPoolSize(15);
		// uimirrorDataSource.setAcquireIncrement(3);
		// uimirrorDataSource.setMaxStatementsPerConnection(100);
		// uimirrorDataSource.setAutomaticTestTable("c3p0_test_table");
		// uimirrorDataSource.setNumHelperThreads(20);
		// builder.bind("java:comp/env/jdbc/uimirror", uimirrorDataSource);

		// try {
		// builder.activate();
		// } catch (IllegalStateException e) {
		// logger.debug(e.toString());
		// } catch (NamingException e) {
		// logger.debug(e.toString());
		// }

	}

	@Before
	public void setUp() throws Exception {
	}

}
