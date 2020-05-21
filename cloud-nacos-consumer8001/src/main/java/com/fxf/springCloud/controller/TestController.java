package com.fxf.springCloud.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fxf.springCloud.service.feignTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author FXF
 * @create 2020-05-15 13:32
 */
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private feignTest feignTest;

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @GetMapping("/config/info")
    public String getConfigInfo(HttpServletRequest aa){
        System.out.println(aa);
        return restTemplate.getForObject(serverUrl+"/config/info",String.class);
    }

    /**
     * 测试openfegin
     * @return
     */
    @GetMapping("/config/info2")
    public String getConfigInfo2(){
        return feignTest.getConfigInfo();
    }


}
