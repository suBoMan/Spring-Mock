package com.joker.code.spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname Scope
 * @Created by wangkx
 * @Date 5/4/21 1:22 AM
 * @Description Spring 的作用域注解
 *
 * 原型Bean与单例Bean区别：
 * 原型Bean在创建的Bean的时候每次都是不一样的
 * 单例Bean在创建的Bean的时候每次都是一样的。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface Scope {

    String value();
}
