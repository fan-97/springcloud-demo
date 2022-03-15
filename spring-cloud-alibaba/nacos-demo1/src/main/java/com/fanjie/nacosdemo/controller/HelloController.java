package com.fanjie.nacosdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanjie
 * @date 2022/3/15 21:03
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/payment/nacos/{id}")
    public String helloNacos(@PathVariable("id") Long id) {
        return "当前微服务的端口号：" + serverPort + "id:" + id;
    }
}
