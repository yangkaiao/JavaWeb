package buba.com.cn.service;

import buba.com.cn.entity.User;

public interface UserService {
    //增加用户
    int addUser(User user);

    //查找数据库中  用户名和密码 是否存在 用在登录界面
    int findUser(String username,String password);

    //查找用户是否存在 用在注册界面中
    int findUserName(String username);

    //通过账号密码查user对象
    User selectUser(String username,String password);

    //通过用户id查询
    User findUserId(Integer userId);
}
