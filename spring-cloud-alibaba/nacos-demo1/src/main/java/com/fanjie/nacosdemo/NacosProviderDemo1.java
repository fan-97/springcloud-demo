package com.fanjie.nacosdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author fanjie
 * @date 2022/3/15 20:55
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderDemo1 {
    public static void main(String[] args) {
        SpringApplication.run(NacosProviderDemo1.class,args);
    }
}
