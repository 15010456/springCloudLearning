package com.macro.cloud.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.macro.cloud.pojo.CommonResult;
import com.macro.cloud.pojo.User;
import com.macro.cloud.remote.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName UserHystrixController
 * @Description 用于调用user-service服务
 * @Author liuql
 * @Date 2023/3/23 16:14
 * Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserHystrixController {
    @Resource
    private UserService userService;

    /**
     * 用于测试服务降级的接口
     * @param id
     * @return
     */
    @GetMapping("/testFallback/{id}")
    public CommonResult testFallback(@PathVariable Long id) {
        return userService.getUser(id);
    }

    /**
     * 设置命令、分组及线程池名称
     * @param id
     * @return
     */
    @GetMapping("/testCommand/{id}")
    public CommonResult testCommand(@PathVariable Long id) {
        return userService.getUserCommand(id);
    }

    /**
     * 使用ignoreExceptions忽略某些异常降级
     * @param id
     * @return
     */
    @GetMapping("/testException/{id}")
    public CommonResult testException(@PathVariable Long id) {
        return userService.getUserException(id);
    }


    /**
     * Hystrix的请求缓存,当系统并发量越来越大时，我们需要使用缓存来优化系统，达到减轻并发请求线程数，提供响应速度的效果
     *
     * 缓存使用过程中的问题
     * 在缓存使用过程中，我们需要在每次使用缓存的请求前后对HystrixRequestContext进行初始化和关闭，否则会出现如下异常：
     *
     * java.lang.IllegalStateException: Request caching is not available. Maybe you need to initialize the HystrixRequestContext?
     * 	at com.netflix.hystrix.HystrixRequestCache.get(HystrixRequestCache.java:104) ~[hystrix-core-1.5.18.jar:1.5.18]
     * 	at com.netflix.hystrix.AbstractCommand$7.call(AbstractCommand.java:478) ~[hystrix-core-1.5.18.jar:1.5.18]
     * 	at com.netflix.hystrix.AbstractCommand$7.call(AbstractCommand.java:454) ~[hystrix-core-1.5.18.jar:1.5.18]
     *
     * 	解决：这里我们通过使用过滤器，在每个请求前后初始化和关闭HystrixRequestContext来解决该问题
     * @param id
     * @return
     */
    @GetMapping("/testCache/{id}")
    public CommonResult testCache(@PathVariable Long id) {
        CommonResult userCache1 = userService.getUserCache(id);
        System.out.println("userCache1:"+userCache1.toString());

        CommonResult userCache2 = userService.getUserCache(id);
        System.out.println("userCache2:"+userCache1.toString());

        CommonResult userCache3 = userService.getUserCache(id);
        System.out.println("userCache3:"+userCache1.toString());
        return new CommonResult("操作成功", 200);
    }

    /**
     * 清除hystrix缓存
     * @param id
     * @return
     */
    @GetMapping("/testRemoveCache/{id}")
    public CommonResult removeCache(Long id) {
         CommonResult commonResult = userService.removeCache(id);
         return commonResult;
    }

    /**
     *合并请求
     * 微服务系统中的服务间通信，需要通过远程调用来实现，随着调用次数越来越多，占用线程资源也会越来越多。
     * Hystrix中提供了@HystrixCollapser用于合并请求，从而达到减少通信消耗及线程数量的效果。
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/testCollapser")
    public CommonResult testCollapser() throws ExecutionException, InterruptedException {
        Future<User> future1 = userService.getUserFuture(1L);
        Future<User> future2 = userService.getUserFuture(2L);
        future1.get();
        System.out.println("future1:"+future1.get());
        future2.get();
        System.out.println("future2:"+future2.get());

        ThreadUtil.safeSleep(200);
        Future<User> future3 = userService.getUserFuture(3L);
        future3.get();
        System.out.println("future3:"+future3.get());
        return new CommonResult("操作成功", 200);
    }
}