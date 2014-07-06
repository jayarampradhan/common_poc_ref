package com.uimirror.api.challenge.base;

import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "classpath:challenge-test-beans.xml" })
public class BaseTest {

    @Before
    public void setUp() throws Exception {
    }

}
