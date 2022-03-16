package com.fanjie.helloworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
        return String.format("hello eureka serviceId: %s  host: %s  port:%s", registration.getServiceId(), registration.getHost(),registration.getPort());
    }

}
