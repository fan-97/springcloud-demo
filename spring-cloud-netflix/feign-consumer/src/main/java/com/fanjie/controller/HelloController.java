package com.fanjie.controller;

import com.fanjie.helloserviceapi.dto.User;
import com.fanjie.service.HelloService;
import com.fanjie.service.RefactorHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanjie
 * @date 2022/3/24 11:57
 */
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;
    @Autowired
    private RefactorHelloService refactorHelloService;

    @GetMapping("/feign-consumer")
    public String helloConsumer() {
        return helloService.hello();
    }

    @RequestMapping(value = "/feign-consumer2", method = RequestMethod.GET)
    public String helloConsumer2() {
        return helloService.hello() + "\n" +
                helloService.hello("DIDI") + "\n" +
                helloService.hello("DIDI", 30) + "\n" +
                helloService.hello(new User("DIDI", 30)) + "\n";
    }

    @RequestMapping(value = "/feign-consumer3", method = RequestMethod.GET)
    public String helloConsumer3() {
        return refactorHelloService.hello("DIDI") + "\n" +
                refactorHelloService.hello("DIDI", 30) + "\n" +
                refactorHelloService.hello(new User("DIDI", 30)) + "\n";
    }
}
