package com.fanjie.helloworld.controller;

import com.fanjie.helloserviceapi.dto.User;
import com.fanjie.helloserviceapi.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@Slf4j
public class RefactorHelloController implements HelloService {
    @Override
    public String hello(@RequestParam("name") String name) {
        Random random = new Random();
        int i = random.nextInt(3000);
        try {
            log.info("sleep Time {}",i);
            Thread.sleep(i);
            log.info("/hello4");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello" + name;
    }

    @Override
    public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
        return new User(name, age);
    }

    @Override
    public String hello(@RequestBody User user) {
        return "Hello " + user.getName() + ", " + user.getAge();
    }

}
