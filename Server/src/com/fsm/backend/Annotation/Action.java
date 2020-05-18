package com.fsm.backend.Annotation;

import com.fsm.backend.Enums.TYPE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    String path() default "index";
    TYPE type() default TYPE.GET;
}
