package com.fanjie.service;

import com.fanjie.helloserviceapi.service.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author fanjie
 * @date 2022/3/28 18:00
 */
@FeignClient(name = "hello-service")
public interface RefactorHelloService extends HelloService {
}
