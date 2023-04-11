package com.macro.cloud.remote;

import com.macro.cloud.pojo.CommonResult;
import com.macro.cloud.pojo.User;

import java.util.concurrent.Future;

/**
 * @InterfaceName UserService
 * @Description TODO
 * @Author liuql
 * @Date 2023/3/23 16:16
 * Version 1.0
 **/
public interface UserService {
    CommonResult getUser(Long id);

    CommonResult getUserCommand(Long id);

    CommonResult getUserException(Long id);

    CommonResult getUserCache(Long id);

    CommonResult removeCache(Long id);

    Future<User> getUserFuture(Long l);
}
