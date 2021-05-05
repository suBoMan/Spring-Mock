package com.joker.code.spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname ComponentScan
 * @Created by wangkx
 * @Date 5/4/21 1:22 AM
 * @Description Spring的包扫描注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface Component {

    String value() default "";
}
