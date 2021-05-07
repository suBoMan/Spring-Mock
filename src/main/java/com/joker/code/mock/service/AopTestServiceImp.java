package com.joker.code.mock.service;

import com.joker.code.spring.Autowired;
import com.joker.code.spring.Component;

/**
 * @Classname AopTestServiceImp
 * @Created by wangkx
 * @Date 5/7/21 6:06 AM
 * @Description 模拟AOP
 */
@Component("aopTestService")
public class AopTestServiceImp implements AopTestService {

    @Autowired
    private OrderService orderService;

    @Override
    public void test() {
        System.out.println(orderService);

    }
}
