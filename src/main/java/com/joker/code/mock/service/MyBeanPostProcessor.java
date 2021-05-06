package com.joker.code.mock.service;

import com.joker.code.spring.BeanPostProcessor;
import com.joker.code.spring.Component;

/**
 * @Classname MyBeanPostProcessor
 * @Created by wangkx
 * @Date 5/7/21 5:31 AM
 * @Description TODO
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) {
        System.out.println("初始化前...." + beanName);

        if (beanName.equals("userService")) {

            ((UserService) bean).setName("BeanPostProcessor后置处理器赋值：Joker");
        }

        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) {

        System.out.println("初始化后...." + beanName);
        return bean;
    }
}
