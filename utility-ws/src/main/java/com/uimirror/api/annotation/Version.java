package com.uimirror.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Version
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Version {
    String value();
}
