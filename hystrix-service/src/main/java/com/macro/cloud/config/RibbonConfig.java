package com.macro.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName RibbonConfig
 * @Description TODO
 * @Author liuql
 * @Date 2023/3/21 16:11
 * Version 1.0
 **/
@Configuration
public class RibbonConfig {
    @Bean
    @LoadBalanced //负载均衡功能
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}