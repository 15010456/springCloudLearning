package com.macro.cloud.remote;

import com.macro.cloud.pojo.CommonResult;
import com.macro.cloud.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @InterfaceName UserService
 * @Description 添加UserService接口完成对user-service服务的接口绑定,负载均衡功能演示
 * @Author liuql
 * @Date 2023/4/7 16:41
 * Version 1.0
 **/
@FeignClient(value = "user-service",fallback = UserFallbackService.class)//表示这是对user-service服务的接口调用客户端,设置服务降级处理类为UserFallbackService
public interface UserService {
    @PostMapping("/user/create")
    CommonResult create(@RequestBody User user);

    @GetMapping("/user/{id}")
    CommonResult<User> getUser(@PathVariable Long id);

    @GetMapping("/user/getByUsername")
    CommonResult<User> getByUsername(@RequestParam String username);

    @PostMapping("/user/update")
    CommonResult update(@RequestBody User user);

    @PostMapping("/user/delete/{id}")
    CommonResult delete(@PathVariable Long id);
}
