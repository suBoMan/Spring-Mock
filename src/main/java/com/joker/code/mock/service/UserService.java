package com.joker.code.mock.service;

import com.joker.code.spring.Autowired;
import com.joker.code.spring.BeanNameAware;
import com.joker.code.spring.Component;

/**
 * @Classname UserService
 * @Created by wangkx
 * @Date 5/4/21 1:28 AM
 * @Description TODO
 */

@Component("userService")
public class UserService implements BeanNameAware {

    @Autowired
    private OrderService orderService;

    private String beanName;

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    public void test(){
        System.out.println(orderService);
        System.out.println(beanName);
    }


}
