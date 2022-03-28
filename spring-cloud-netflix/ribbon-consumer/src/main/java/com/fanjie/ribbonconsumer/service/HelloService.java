package com.fanjie.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * @author fanjie
 * @date 2022/3/15 16:10
 */
@Service
public class HelloService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String hello() {
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
    }

    /**
     * 异步返回结果
     * @return
     */
    @HystrixCommand
    public Future<String> getResultAsync() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
            }
        };
    }

    /**
     * 熔断回调函数
     *
     * @return
     */
    public String helloFallback() {
        return "error";
    }

}
