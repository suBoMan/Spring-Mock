package com.joker.code.mock.service;

import com.joker.code.spring.Autowired;
import com.joker.code.spring.BeanNameAware;
import com.joker.code.spring.Component;
import com.joker.code.spring.InitializingBean;

/**
 * @Classname UserService
 * @Created by wangkx
 * @Date 5/4/21 1:28 AM
 * @Description TODO
 */

@Component("initService")
public class InitService implements InitializingBean {

    @Autowired
    private OrderService orderService;

    private String beanName;


    public void test(){
        System.out.println(orderService);
        System.out.println(beanName);
    }


    /***
     * Spring 提供的初始化的一种机制。你可以在这个方法中实现自己的逻辑
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化时实现自己的逻辑....");
    }
}
