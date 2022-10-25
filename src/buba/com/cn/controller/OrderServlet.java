package buba.com.cn.controller;

import buba.com.cn.utils.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName:OrderServlet
 * @Auther: YooAo
 * @Description: 订单界面
 * @Date: 2022/10/17 16:39
 * @Version: v1.0
 */
public class OrderServlet extends ViewBaseServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //后台管理跳转界面里的 订单管理 跳转界面
        if(req.getParameter("method").equals("order_manager")){
            //请求转发跳转到/WEB-INF/view/pages/user/login.html
            processTemplate("pages/manager/order_manager",req, resp);
        }

        //购物车跳转界面后 我的订单 跳转界面
        if(req.getParameter("method").equals("order")){
            //请求转发跳转到/WEB-INF/view/pages/user/login.html
            processTemplate("pages/order/order",req, resp);
        }
    }
}
