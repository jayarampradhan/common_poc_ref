package com.uimirror.api.expense.base;

import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "classpath:expense-test-beans.xml" })
public class BaseTest {

    @Before
    public void setUp() throws Exception {
    }

}
