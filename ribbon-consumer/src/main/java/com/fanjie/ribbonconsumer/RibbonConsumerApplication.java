package com.fanjie.ribbonconsumer;

import com.fanjie.ribbonconsumer.controller.ConsumerController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/***
 * 使用ribbon来实现负载均衡 <br>
 * 1. 通过EnableDiscoveryClient注解注册为eureka的客户端应用，让应用能够发现服务<br>
 * 2. 开启客户端的负载均衡支持 @LoadBalanced<br>
 * 3. 负载均衡的实现 {@link ConsumerController}<br>
 *
 *
 *
 *
 * @author fanjie
 * @date 14:55 2022/3/8
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RibbonConsumerApplication {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(RibbonConsumerApplication.class, args);
    }

}
