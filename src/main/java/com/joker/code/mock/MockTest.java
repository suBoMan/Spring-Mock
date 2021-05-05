package com.joker.code.mock;

import com.joker.code.spring.SpringMockApplicationContext;

/**
 * @Classname MockTest
 * @Created by wangkx
 * @Date 5/4/21 1:19 AM
 * @Description TODO
 */
public class MockTest {

    public static void main(String[] args) {
        SpringMockApplicationContext applicationContext = new SpringMockApplicationContext(AppConfig.class);

        System.out.println(applicationContext.getBean("userService"));
    }

}
