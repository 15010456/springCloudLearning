package com.macro.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConfigClientController
 * @Description TODO
 * @Author liuql
 * @Date 2023/4/11 12:06
 * Version 1.0
 **/
@RestController
@RefreshScope//用于刷新自动配置
public class ConfigClientController {
//    @RefreshScope 不能放在变量上
    @Value("${config.info}") //git配置文件中配置的参数
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}