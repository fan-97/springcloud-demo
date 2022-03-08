package com.fanjie.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <b>eureka核心要素</b>：<br>
 * 1. 服务注册中心  :提供服务注册和服务发现的功能 { eureka-server} <br>
 * 2. 服务提供者   : 作为客户端在注册中心进行注册，并向服务消费者提供服务 , { hello-world}<br>
 * 3. 服务消费者   : 作为客户端在注册中心注册，可以发现在注册中心注册的其他客户端并进行消费（远程调用）{ ribbon-consumer}    eg: <b>Feign</b><br>
 * <br><br>
 * <b>服务发现</b> :
 * 将一个普通的 Spring Boot 应用注册到 Eureka Server 或是从 Eureka Server 中获
 * 取服务列表时， 主要就做了两件事:<br>
 * - 在应用主类配置@EnableDiscoryClient注解.<br>
 * - 在application.properties中使用 eureka.client.serviceUrl.defaultZone
 * 参数指定了服务注册中心的位置。
 *
 *
 *<br><br><br><br><br>
 * @author jie.fan
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
