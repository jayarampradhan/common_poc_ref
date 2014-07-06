package com.uimirror.api.social.base;

import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "classpath:social-test-beans.xml" })
public class BaseTest {

    @Before
    public void setUp() throws Exception {
    }

}
