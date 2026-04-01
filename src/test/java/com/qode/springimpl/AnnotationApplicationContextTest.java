package com.qode.springimpl;

import com.qode.springimpl.context.AnnotationApplicationContext;
import com.qode.springimpl.context.IApplicationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnnotationApplicationContextTest {

    @Test
    public void testCreateContext_expectSuccessGetBean() {
        IApplicationContext context = new AnnotationApplicationContext(AppConfig.class);
        Assertions.assertEquals("testValue", context.getBean("testValue"));
        Assertions.assertEquals("testValue2", context.getBean("useBeanValue"));
    }
}
