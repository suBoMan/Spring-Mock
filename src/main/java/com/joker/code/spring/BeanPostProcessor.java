package com.joker.code.spring;

/**
 * @Classname BeanPostProcessor
 * @Created by wangkx
 * @Date 5/7/21 5:25 AM
 * @Description Bean的后置处理器
 */
public interface BeanPostProcessor {

    // 初始化前
    Object postProcessorBeforeInitialization(Object bean, String beanName);

    // 初始化后
    Object postProcessorAfterInitialization(Object bean, String beanName);

}
