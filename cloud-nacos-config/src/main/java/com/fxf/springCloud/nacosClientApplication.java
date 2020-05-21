package com.fxf.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author FXF
 * @create 2020-05-15 13:19
 */
@SpringBootApplication
@EnableDiscoveryClient
public class nacosClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(nacosClientApplication.class, args);
    }
}
