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
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Autowired {

}
