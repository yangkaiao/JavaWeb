package buba.com.cn.service.impl;

import buba.com.cn.dao.UserDao;
import buba.com.cn.dao.impl.UserDaoImpl;
import buba.com.cn.entity.User;
import buba.com.cn.service.UserService;

/**
 * @ClassName:BookLoginServiceImpl
 * @Auther: YooAo
 * @Description: 实现类
 * @Date: 2022/10/12 15:48
 * @Version: v1.0
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    //注册 增加用户
    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    //查找数据库中  用户名和密码 是否存在  用在登录界面
    @Override
    public int findUser(String username, String password) {
        return userDao.findUser(username,password);
    }

    //查找用户是否存在 用在注册界面中
    @Override
    public int findUserName(String username) {
        return userDao.findUserName(username);
    }

    //通过账号密码查user对象
    @Override
    public User selectUser(String username, String password) {
        return userDao.selectUser(username,password);
    }
}
