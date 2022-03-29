package com.fanjie;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author fanjie
 * @date 2022/3/24 11:26
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class FeignConsumerApplication {
    /**
     * feign客户端日志级别
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerApplication.class,args);
    }
}
