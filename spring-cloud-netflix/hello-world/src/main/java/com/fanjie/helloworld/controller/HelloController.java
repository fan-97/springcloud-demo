package com.fanjie.helloworld.controller;

import com.fanjie.helloworld.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

/**
 * @author fanjie
 * @date 2022/3/8 10:50
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @Autowired
    private DiscoveryClient client;
    @Autowired
    private Registration registration;

    @GetMapping("hello")
    public String hello() {
        return String.format("hello eureka serviceId: %s  host: %s  port:%s", registration.getServiceId(), registration.getHost(), registration.getPort());
    }

    @GetMapping("hello1")
    public String hello(@RequestParam String name) {
        return "Hello" + name;
    }

    @GetMapping("hello2")
    public User hello(@RequestHeader String name, @RequestHeader Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @PostMapping("hello3")
    public String hello(@RequestBody User user) {
        return "hello " + user.getName() + " , " + user.getAge();
    }

}
