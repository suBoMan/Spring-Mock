package com.joker.code.spring;

/**
 * @Classname BeanDefinition
 * @Created by wangkx
 * @Date 5/4/21 11:43 PM
 * @Description Bean的定义类，描述这个Bean
 */
public class BeanDefinition {

    private Class clazz;

    private String scope;


    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
