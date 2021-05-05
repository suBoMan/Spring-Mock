package com.joker.code.spring;

/**
 * @Classname InitializingBean
 * @Created by wangkx
 * @Date 5/6/21 12:38 AM
 * @Description TODO
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;

}
