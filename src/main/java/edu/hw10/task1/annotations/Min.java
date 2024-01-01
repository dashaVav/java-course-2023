package edu.hw10.task1.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE,
    ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME) public @interface Min {
    int value() default Integer.MIN_VALUE;

    Class<? extends Exception> exception() default Exception.class;
}

