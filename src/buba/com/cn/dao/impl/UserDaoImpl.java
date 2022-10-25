package buba.com.cn.dao.impl;

import buba.com.cn.dao.UserDao;
import buba.com.cn.entity.User;
import buba.com.cn.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName:BookDaoImpl
 * @Auther: YooAo
 * @Description: Book实现类
 * @Date: 2022/10/12 15:40
 * @Version: v1.0
 */
public class UserDaoImpl implements UserDao {
    //注册  增加用户
    @Override
    public int addUser(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "INSERT INTO t_user(user_name,user_password,email) values(?,?,?)";
        int update = jdbcTemplate.update(sql,user.getUserName(),user.getUserPassword(),user.getEmail());
        return update;
    }




    //查找数据库中  用户名和密码 是否存在  用在登录界面
    @Override
    public int findUser(String username, String password) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "select count(*) from t_user where user_name = ? and user_password = ?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, username, password);
        return integer;
    }

    //查找用户是否存在 用在注册界面中
    @Override
    public int findUserName(String username) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "select count(*) from t_user where user_name = ?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, username);

        return integer;
    }

    //通过账号密码查找user对象
    @Override
    public User selectUser(String username, String password) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "select * from t_user where user_name = ? and user_password = ?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        return user;
    }
}
