package buba.com.cn.controller;

import buba.com.cn.entity.Book;
import buba.com.cn.entity.Cart;
import buba.com.cn.entity.CartItem;
import buba.com.cn.entity.User;
import buba.com.cn.service.BookAdminService;
import buba.com.cn.service.CartItemService;
import buba.com.cn.service.impl.BookAdminServiceImpl;
import buba.com.cn.service.impl.CartItemServiceImpl;
import buba.com.cn.utils.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
* 购物车界面
* */
public class CartServlet extends ViewBaseServlet {
   private BookAdminService bookAdminService = new BookAdminServiceImpl();
    private CartItemService cartItemService = new CartItemServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //购物车跳转界面
        if(req.getParameter("method").equals("cart")){
            //请求转发跳转到/WEB-INF/view/pages/user/login.html
            processTemplate("pages/cart/cart",req, resp);
        }

        //购物车结账界面
        if(req.getParameter("method").equals("checkout")){
            //请求转发跳转到/WEB-INF/view/pages/user/login.html
            processTemplate("pages/cart/checkout",req, resp);
        }

        //主页加入购物车
        if(req.getParameter("method").equals("addCart")){
           this.addCart(req,resp);
        }

        //删除购物车
        if(req.getParameter("method").equals("delCart")){
            this.delCart(req,resp);
        }

        //清空购物车
        if(req.getParameter("method").equals("clearCart")){
            this.clearCart(req,resp);
        }

        //更改购物车图书项数量
        if(req.getParameter("method").equals("updateBookCount")){
            this.updateBookCount(req,resp);
        }


        //以下代码：主页每点一次加入购物车 导航栏的购物车的数量就会实时更新
        User user = (User)req.getSession().getAttribute("currUser");//登录成功后的用户
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        System.out.println(cart);
        req.getSession().setAttribute("currUser",user);

    }



    //主页加入购物车
    private void addCart(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String bookId = req.getParameter("bookId");
        HttpSession session = req.getSession();
        Book cartBook = bookAdminService.FindBookId(Integer.valueOf(bookId));
        User user = (User)session.getAttribute("currUser");//登录成功后的用户
        CartItem cartItem = new CartItem(cartBook,1,user);

        //将指定的图书添加到当前用户的购物车中
        cartItemService.addOrUpdateCartItem(cartItem,user.getCart());
        resp.sendRedirect("/JavaWeb/index");//跳到主页
    }

    //删除购物车项
    private void delCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cartId = req.getParameter("cartId");
        cartItemService.delCartItem(Integer.valueOf(cartId));

        resp.sendRedirect("/JavaWeb/Cart?method=cart");

    }

    //清空购物车
    private void clearCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("currUser");//登录成功后的用户
        cartItemService.clearCartItem(Integer.valueOf(user.getUserId()));
        resp.sendRedirect("/JavaWeb/Cart?method=cart");
    }

    //更改购物车图书项数量
    private void updateBookCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cartId = req.getParameter("cartId");
        String bookCount = req.getParameter("bookCount");
        CartItem cartItem = new CartItem(Integer.valueOf(cartId),Integer.valueOf(bookCount));
        cartItemService.updateCartItem(cartItem);
        resp.sendRedirect("/JavaWeb/Cart?method=cart");
    }

}
