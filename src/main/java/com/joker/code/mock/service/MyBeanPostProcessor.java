package com.joker.code.mock.service;

import com.joker.code.spring.BeanPostProcessor;
import com.joker.code.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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

        if ("userService".equals(beanName)) {

            ((UserService) bean).setName("BeanPostProcessor后置处理器赋值：Joker");
        }

        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) {

        System.out.println("初始化后...." + beanName);
        // 模拟AOP功能
        if ("aopTestService".equals(beanName)) {
            System.out.println("需要代理的类：" + beanName);
            Object proxyInstance = Proxy.newProxyInstance(MyBeanPostProcessor.class.getClassLoader(),
                    bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("先执行代理逻辑");

                    return  method.invoke(bean, args);
                }
            });

            return proxyInstance;
        }
        return bean;
    }
}
