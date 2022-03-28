package com.fanjie.helloworld;

import java.util.concurrent.*;

//@SpringBootApplication
//@EnableEurekaClient
public class HelloWorldApplication {

    public static void main(String[] args) {
//        SpringApplication.run(HelloWorldApplication.class, args);
        Future<String> submit = Executors.newCachedThreadPool().submit(() -> {
            int i = 1;
            while (true) {
                Thread.sleep(1000);
                System.out.println(i++);
            }

        });
        try {
            Object o = submit.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
