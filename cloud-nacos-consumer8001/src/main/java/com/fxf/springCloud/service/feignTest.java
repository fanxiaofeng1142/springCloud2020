package com.fxf.springCloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author FXF
 * @create 2020-05-20 14:46
 */

@Component
@FeignClient(value="${service-url.nacos-user-service}")
public interface feignTest {

    @GetMapping("/config/info")
    public String getConfigInfo();

}
