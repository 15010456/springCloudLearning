package om.macro.cloud.service;

import om.macro.cloud.pojo.User;

import java.util.List;

/**
 * @InterfaceName UserService
 * @Description TODO
 * @Author liuql
 * @Date 2023/3/21 16:26
 * Version 1.0
 **/
public interface UserService {
    void create(User user);

    User getUser(Long id);

    void update(User user);

    void delete(Long id);

    User getByUsername(String username);

    List<User> getUserByIds(List<Long> ids);
}
