package buba.com.cn.controller;

import buba.com.cn.entity.*;
import buba.com.cn.service.CartItemService;
import buba.com.cn.service.OrderService;
import buba.com.cn.service.impl.CartItemServiceImpl;
import buba.com.cn.service.impl.OrderServiceImpl;
import buba.com.cn.utils.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @ClassName:OrderServlet
 * @Auther: YooAo
 * @Description: 订单界面
 * @Date: 2022/10/17 16:39
 * @Version: v1.0
 */
public class OrderServlet extends ViewBaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    private CartItemService cartItemService = new CartItemServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //后台管理跳转界面里的 订单管理 跳转界面
        if(req.getParameter("method").equals("order_manager")){
            this.order_manager(req, resp);
            //请求转发跳转到/WEB-INF/view/pages/user/login.html
            processTemplate("pages/manager/order_manager",req, resp);
        }

        //购物车跳转界面后 我的订单 跳转界面
        if(req.getParameter("method").equals("order")){
            this.order(req, resp);
            //请求转发跳转到/WEB-INF/view/pages/user/login.html
            processTemplate("pages/order/order",req, resp);
        }

        //购物车结账界面
        if(req.getParameter("method").equals("checkout")){
            this.checkout(req, resp);

            //请求转发跳转到/WEB-INF/view/pages/user/login.html
            processTemplate("pages/cart/checkout",req, resp);
        }


        //购物车订单详情
        if(req.getParameter("method").equals("order_details")){
            this.order_details(req, resp);
            //请求转发跳转到/WEB-INF/view/pages/user/login.html
            processTemplate("pages/order/order_details",req, resp);
        }

        //购物车订单详情退款
        if(req.getParameter("method").equals("delorder")){
            this.delorder(req, resp);
        }

        //后台管理订单详情
        if(req.getParameter("method").equals("order_Admin_details")){
            this.order_Admin_details(req, resp);
            //请求转发跳转到/WEB-INF/view/pages/user/login.html
            processTemplate("pages/order/order_Admin_details",req, resp);
        }


    }




    //购物车结账界面 1、生成订单号  2、结完账就添加订单  点我的订单就查询 3、订单详情添加信息 点查看详情就查询
    private void checkout(HttpServletRequest req, HttpServletResponse resp) {
        String orderNumber = get16UUID();//随机生成订单
        System.out.println(orderNumber);
        HttpSession session = req.getSession();
        session.setAttribute("orderIdByTime",orderNumber);

        User user = (User)session.getAttribute("currUser");//登录成功后的用户
        //添加订单  去点结账的时候把购物车信息去添加到订单中
        Order order = new Order(Long.valueOf(orderNumber),user.getCart().getTotalBookCount(),user.getCart().getTotalMoney(),user.getUserId(),0);
        orderService.addOrder(order);//添加订单

        //通过订单编号 查询order
        Order orderByNum = orderService.findOrderByNum(Long.valueOf(orderNumber));

        //购物车我的订单 查看详情添加信息
        for (CartItem cartItem : user.getCart().getCartItemMap().values()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setBookId(cartItem.getBook().getBookId());
            orderDetail.setBookImg(cartItem.getBook().getImgPath());
            orderDetail.setBookName(cartItem.getBook().getName());
            orderDetail.setBookCount(cartItem.getBookCount());
            orderDetail.setAmount(cartItem.getBook().getPrice());
            orderDetail.setOrderId(orderByNum.getOrderId());
            orderService.addOrderDetail(orderDetail);
            System.out.println("c"+cartItem);
            System.out.println("o"+orderDetail);
        }





        //结完账清空购物车
        cartItemService.clearCartItem(user.getUserId());

        //更新数据 更新购物车
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        System.out.println(cart);
        req.getSession().setAttribute("currUser",user);//重新设置一下
    }




    //购物车跳转界面后 查询我的订单
    private void order(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("currUser");//登录成功后的用户

        List<Order> orderList = orderService.findOrder(user.getUserId());
        session.setAttribute("orderList",orderList);

    }


    //购物车订单详情 查询
    private void order_details(HttpServletRequest req, HttpServletResponse resp) {
        String orderId = req.getParameter("orderId");
        req.getSession().setAttribute("orderId",orderId);
        List<OrderDetail> orderDetail = orderService.findOrderDetail(Integer.valueOf(orderId));

        Integer count=0;
        BigDecimal price = BigDecimal.valueOf(0.0);
        for (OrderDetail detail : orderDetail) {
           count += detail.getBookCount();
           price = price.add(detail.getAmount().multiply(BigDecimal.valueOf(detail.getBookCount())));
        }

        HttpSession session = req.getSession();
        session.setAttribute("orderDetailsList",orderDetail);
        session.setAttribute("count",count);
        session.setAttribute("priceSum",price);
    }


    //购物车订单详情退款
    private void delorder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String orderId = req.getParameter("orderId");
        //先删子表数据，在删父表数据，因为有外键，先删订单详情表的数据，再删订单表的数据
        orderService.delOrderDetail(Integer.valueOf(orderId));
        orderService.delOrder(Integer.valueOf(orderId));
        resp.sendRedirect("/JavaWeb/Order?method=order");//跳到主页

    }


    //后台管理里面订单管理
    private void order_manager(HttpServletRequest req, HttpServletResponse resp) {
        List<Order> order = orderService.findOrder();
        req.setAttribute("orderList",order);
    }


    //后台管理订单详情 查询
    private void order_Admin_details(HttpServletRequest req, HttpServletResponse resp) {
        String orderId = req.getParameter("orderId");
        req.getSession().setAttribute("orderId",orderId);
        List<OrderDetail> orderDetail = orderService.findOrderDetail(Integer.valueOf(orderId));

        Integer count=0;
        BigDecimal price = BigDecimal.valueOf(0.0);
        for (OrderDetail detail : orderDetail) {
            count += detail.getBookCount();
            price = price.add(detail.getAmount().multiply(BigDecimal.valueOf(detail.getBookCount())));
        }

        HttpSession session = req.getSession();
        session.setAttribute("orderDetailsList",orderDetail);
        session.setAttribute("count",count);
        session.setAttribute("priceSum",price);
    }









    // 获取16位的订单编号 //随机生成订单编号
    public static String get16UUID(){
        // 1.开头两位，标识业务代码或机器代码（可变参数）
        String machineId = "11";
        // 2.中间四位整数，标识日期
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String dayTime = sdf.format(new Date());
        // 3.生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        // 4.可能为负数
        if(hashCode < 0){
            hashCode = -hashCode;
        }
        // 5.算法处理: 0-代表前面补充0; 10-代表长度为10; d-代表参数为正数型
        String value = machineId + dayTime + String.format("%010d", hashCode);
        System.out.println(value);
        return value;
    }



}
