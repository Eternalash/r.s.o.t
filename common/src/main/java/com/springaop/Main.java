package com.springaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by Bryan.C on 2017/1/5.
 */

public class Main {
    static class Shop{
        private int sleepTime;
        public Shop(int sleepTime){
            this.sleepTime=sleepTime;
        }

        public double getPrice(){
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1.1;
        }

    }
    public static void main(String[] args){
        List<Shop> shops=new ArrayList<>();
        for(int i=1;i<=10;i++){
            Shop shop=new Shop(100);
            shops.add(shop);
        }
        LocalDateTime localDateTime=LocalDateTime.now();
        shops.parallelStream().map(Shop::getPrice).collect(Collectors.toList());
        System.out.println(Duration.between(localDateTime,LocalDateTime.now()).toMillis());
        localDateTime=LocalDateTime.now();
        shops.stream().map(Shop::getPrice).collect(Collectors.toList());
        System.out.println(Duration.between(localDateTime,LocalDateTime.now()).toMillis());

        ExecutorService executorService= Executors.newFixedThreadPool(shops.size(), r -> {
            Thread thread=new Thread(r);
            thread.setDaemon(true);
            return thread;
        });
        localDateTime=LocalDateTime.now();
        shops.stream().map(shop -> CompletableFuture.supplyAsync(shop::getPrice)).collect(Collectors.toList()).stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(Duration.between(localDateTime,LocalDateTime.now()).toMillis());

        localDateTime=LocalDateTime.now();
        shops.stream().map(shop -> CompletableFuture.supplyAsync(shop::getPrice,executorService)).collect(Collectors.toList()).stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(Duration.between(localDateTime,LocalDateTime.now()).toMillis());

        ApplicationContext appContext = new ClassPathXmlApplicationContext("Spring-Customer.xml");
        PersonService customer = (PersonService) appContext.getBean("man");
        customer.sleep(2,6);
    }
}
