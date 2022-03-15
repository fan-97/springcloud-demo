# 服务治理： Spring Cloud Eureka
## eureka核心要素： 
1. 服务注册中心 :提供服务注册和服务发现的功能 [eureka-server](eureka-server)
2. 服务提供者 : 作为客户端在注册中心进行注册，并向服务消费者提供服务 , [hello-world](hello-world) 
3. 服务消费者 : 作为客户端在注册中心注册，可以发现在注册中心注册的其他客户端并进行消费（远程调用）[ribbon-consumer](ribbon-consumer) 以及 **feign**

## 使用

引入依赖：

```xml
 <dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
 </dependency>
```

启动类加上注解开启服务:

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
```

配置注册中心：

```
server.port=1111
eureka.instance.hostname=localhost
# 不会启动注册操作 自己不会注册自己
eureka.client.register-with-eureka=false  
eureka.client.fetch-registry=false
# 服务中心的地址 其他客户端需要填写这个地址才能进行注册
eureka.client.serviceUrl.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/
# 续约任务间隔时间
eureka.instance.lease-renewal-interval-in-seconds=30
# 服务失效时间
eureka.instance.lease-expiration-duration-in-seconds=90
```



## 原理
将一个普通的 Spring Boot 应用注册到 Eureka Server 或是从 Eureka Server 中获 取服务列表时， 主要就做了两件事: 
- 在应用主类配置@EnableDiscoryClient注解. 
- 在application.properties中使用 eureka.client.serviceUrl.defaultZone 参数指定了服务注册中心的位置。

![DicoveryClient](../resources/discoveryClient.png)

**com.netflix.discovery.DiscoveryClient**: 

- 向Eureka Server注册服务实例

- 向Eureka Server服务租约

- 当服务关闭期间， 向Eureka Server取消租约

- 查询Eureka Server中的服务实例列表

Eureka Client还需要配置一个Eureka Server的 URL列表。


# 客户端负载均衡:Spring Cloud Ribbon

> Spring Cloud Ribbon 是一个基于HTTP 和TCP的客户端负载均衡工具。

- **负载均衡**: 简单来说就是将所有的请求先汇聚在一起，然后通过一系列的算法对请求进行分发，让服务器达到最高的处理效率，降低系统的压力。<br>
- **服务端负载均衡**:  需要通过专门的一台提供负载均衡的服务器来实现。负载均衡服务器通过均衡算法将所有请求服务器的请求分发到不同的服务器上。<br>
- **客户端负载均衡** : 客户端本身就能够实现。客户端本身从注册中心获取所有的服务，然后通过均衡算法将请求的分发到对应的服务上<br>



## 使用
引入依赖：
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```
加上注解：
```java
@SpringBootApplication
@EnableEurekaClient
public class HelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }

}
```
配置注册中心：
```properties
# 实例名称（注册到注册中心的名称)
spring.application.name=hello-service
# 注册中心地址
eureka.client.service-url.defaultZone=http://localhost:1111/eureka/
```
## 原理

核心类
![](../resources/ribbon-core-uml.png)

## 负载均衡策略、算法



-  RandomRule: 从服务列表中随机选择服务

-  RoundRobinRule: 以轮询的方式选择服务

- RetryRule: 以轮询的方式选择服务，会在约定时间内重试直到选择到该服务（内部用RoundRobinRule实现）

- **WeightedResponseTimeRule**: 对RoundRobinRule的扩展，增加了根据实例的运行情况来计算权重， 并

  根据权重来挑选实例， 以达到更优的分配效果。核心组件：

    - 定时任务：启动一个定时任务，用来为每一个服务实例计算权重，每30s执行一次。
    - 权重计算：计算的是每个实例的权重区间，并非实例的优先级。每个实例的区间下限是上一个实例的区间上限 ， 而每个实例的区间上限则是 我们上面计算并存储于`ListaccumulatedWeights`中的权重值， 其中第一个实例的下限默认为零。
        - 根据`LoadBalancerStats`中记录的每个实例的统计信息， 累加所有 实例的平均响应时间， 得到总平均响应时间`totalResponseTime`, 该值会用于后续的计算。
        - 为负载均衡器中维护的实例清单逐个计算权重（从第 一个开始）， 计算规则为(`weightSoFar`+`totalResponseTime` - `实例的平均响应时间`)，其中`weightSoFar`初始化为零， 并且每 计算好一个权重需要累加到 `weightSoFar`上供下一次计算使用。
    - 实例选择：
        - 生成一个[0,最大权重值)的随机数  `Random.nextDouble`
        - 遍历通过`权重计算` 计算出来的权重列表,比较权重的大小，如果权重值大于等千随机数， 就拿当前权重列表的索引值去服务实例列表中获取具体的实例

**示例**：[ribbon-consumer](/ribbon-consumer)


# 服务容错保护：Spring Cloud Hystrix

> Hystrix具备 服务降级、服务熔断、线程和信号隔离、请求缓存、请求合并已经服务监控功能。

## 使用

- 引入依赖

引入了`spring-cloud-starter-netflix-eureka-client` ,就不用单独引入hystrix

要使用**@HystrixCommand注解**，需要引入

```xml-dtd
<dependency>
    <groupId>com.netflix.hystrix</groupId>
    <artifactId>hystrix-javanica</artifactId>
</dependency>
```



- 添加注解，启用熔断功能

```java
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
// 上面三个注解可以用 @SpringCloudApplication替换
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
```





