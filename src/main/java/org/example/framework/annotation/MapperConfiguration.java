package org.example.framework.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapperConfiguration {
    // default is used to change the order of mapper::configure execution.
    // HIGHEST_ORDER = -INT.MAX
    int order() default 0;
}
