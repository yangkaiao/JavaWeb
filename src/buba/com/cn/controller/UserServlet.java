package buba.com.cn.controller;

import buba.com.cn.entity.Cart;
import buba.com.cn.entity.User;
import buba.com.cn.service.CartItemService;
import buba.com.cn.service.UserService;
import buba.com.cn.service.impl.CartItemServiceImpl;
import buba.com.cn.service.impl.UserServiceImpl;
import buba.com.cn.utils.ViewBaseServlet;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName:JumpHtmlServlet
 * @Auther: YooAo
 * @Description: 用户界面
 * @Date: 2022/10/12 11:49
 * @Version: v1.0
 */
/*
* 用户界面
* */
public class UserServlet extends ViewBaseServlet {
    UserService userService = new UserServiceImpl();
    CartItemService cartItemService = new CartItemServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        //登录跳转界面
        if(req.getParameter("method").equals("login")){
            //请求转发跳转到/WEB-INF/view/pages/user/login.html
            processTemplate("pages/user/login",req, resp);
        }

        //注册跳转界面
        if(req.getParameter("method").equals("regist")){
            //请求转发跳转到/WEB-INF/view/pages/user/login.html
            processTemplate("pages/user/regist",req, resp);
        }

        //注销跳转界面
        if(req.getParameter("method").equals("logout")){
            //把username设置为空，到首页username就等于null 显示登录注册界面了
            req.getSession().setAttribute("username",null);

            req.getRequestDispatcher("index").forward(req, resp);

        }



        //提交注册界面
        if(req.getParameter("method").equals("regist_success")){
            this.regist_success(req, resp);

        }
        //提交登录界面
        if(req.getParameter("method").equals("login_success")){
            this.login_success(req, resp);

        }

        //注册界面中判断用户是否存在，如果存在就不能注册，如果不存在就能注册
        if(req.getParameter("method").equals("regist_username")){
            this.regist_username(req, resp);

        }
    }

    //注册方法
    private void regist_success(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        //给密码加密
        String hashedPwd1 = DigestUtils.md5DigestAsHex((password).getBytes());
        User user = new User(username, hashedPwd1, email);
        int i = userService.addUser(user);
        req.setAttribute("username",username);

        //请求转发跳转到/WEB-INF/view/pages/user/login.html
//        processTemplate("pages/user/regist_success",req, resp);
        processTemplate("pages/user/login",req, resp);
    }

    //登录校验方法
    private void login_success(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //给密码加密
        String hashedPwd1 = DigestUtils.md5DigestAsHex((password).getBytes());
        //拿到响应行数，如果是一行就说明有该用户登陆成功，如果是0就没有用户
        int bookLoginId = userService.findUser(username, hashedPwd1);
        //拿到登录成功后的用户

        System.out.println(bookLoginId);


        if (bookLoginId != 0) {
            User user = userService.selectUser(username, hashedPwd1);
            System.out.println("登录成功");
            //拿到登录成功后的用户名
            req.getSession().setAttribute("username",username);
            System.out.println(user);

            //一个用户一个购物车
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            System.out.println(cart);
//            //登录成功后把user对象存在会话域中,在购物车中有使用到
            req.getSession().setAttribute("currUser",user);

            req.getRequestDispatcher("index").forward(req, resp);

            //请求转发跳转到/WEB-INF/view/pages/user/login.html
//            processTemplate("index",req, resp);
        }else{
            resp.getWriter().write(""+bookLoginId);
            System.out.println("登录失败");
        }
    }


    //注册界面中判断用户是否存在，如果存在就不能注册，如果不存在就能注册
    private void regist_username(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        int bookname = userService.findUserName(username);
        System.out.println(bookname);
        resp.getWriter().write(""+bookname);
    }
}
