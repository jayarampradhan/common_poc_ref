package com.uimirror.api.calendar.base;

import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "classpath:calendar-test-beans.xml" })
public class BaseTest {

    @Before
    public void setUp() throws Exception {
    }

}
