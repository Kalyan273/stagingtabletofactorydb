package com.factory.appraisal.factoryService;

import java.lang.annotation.*;

@Author("Yogesh Kumar V")
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Author {
    String value();
}
