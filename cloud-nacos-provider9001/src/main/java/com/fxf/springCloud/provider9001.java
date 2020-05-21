package com.fxf.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class provider9001
{
    public static void main(String[] args) {
        SpringApplication.run(provider9001.class, args);
    }
}
