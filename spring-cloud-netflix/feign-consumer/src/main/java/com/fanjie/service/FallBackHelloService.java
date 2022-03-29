package com.fanjie.service;

import com.fanjie.helloserviceapi.dto.User;
import org.springframework.stereotype.Service;

/**
 * 服务降级的处理
 * @author fanjie
 * @date 2022/3/29 14:39
 */
@Service
public class FallBackHelloService implements HelloService2 {
    private static final String ERROR = "error";

    @Override
    public String hello() {
        return ERROR;
    }

    @Override
    public String hello(String name) {
        return ERROR;
    }

    @Override
    public User hello(String name, Integer age) {
        return new User("未知", 0);
    }

    @Override
    public String hello(User user) {
        return ERROR;
    }
}
