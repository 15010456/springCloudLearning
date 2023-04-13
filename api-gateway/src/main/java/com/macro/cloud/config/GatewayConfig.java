package com.macro.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName GatewayConfig
 * @Description Gateway 提供了两种不同的方式用于配置路由，
 * 一种是通过yml文件来配置，另一种是通过Java Bean来配置，
 * 这里是通过javaBean来配置
 * @Author liuql
 * @Date 2023/4/12 15:33
 * Version 1.0
 **/
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route2", r -> r.path("/user/getByUsername")
                        .uri("http://localhost:8201/user/getByUsername"))
                .build();
    }
}