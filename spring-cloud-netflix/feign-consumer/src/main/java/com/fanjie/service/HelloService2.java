package com.fanjie.service;

import com.fanjie.helloserviceapi.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author fanjie
 * @date 2022/3/24 11:47
 */
@FeignClient(value = "hello-service", contextId = "old", fallback = FallBackHelloService.class)
public interface HelloService2 {
    @RequestMapping("/hello")
    String hello();

    @GetMapping("hello1")
    String hello(@RequestParam("name") String name);

    @GetMapping("hello2")
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @PostMapping("hello3")
    String hello(@RequestBody User user);
}
