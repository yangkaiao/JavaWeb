package buba.com.cn.entity;

import java.sql.Date;

/**
 * @ClassName:BookLogin
 * @Auther: YooAo
 * @Description: 用户信息表
 * @Date: 2022/10/12 15:31
 * @Version: v1.0
 */
public class User {
    private  String userId;//用户id
    private String userName;//用户名
    private String userPassword;//用户密码
    private String email;//用户邮箱
    private Date createTime;
    private Date UpdateTime;
    private String comment;

    private Cart cart;  //一个用户一个购物车


    public User() {
    }



    public User(String userName, String userPassword, String email) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", UpdateTime=" + UpdateTime +
                ", comment='" + comment + '\'' +
                ", cart=" + cart +
                '}';
    }
}
