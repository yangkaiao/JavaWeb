package buba.com.cn.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName:Filter_Login
 * @Auther: YooAo
 * @Description:过滤器
 * @Date: 2022/10/17 11:08
 * @Version: v1.0
 */
@WebFilter(urlPatterns = "/*") //  *：代表所有  限制于所有网站
public class Filter_Login implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;

        //无论是否登录过,都要放行的资源   登录页  登录校验Controller 和一些静态资源
        String requestURI = req.getRequestURI();
//        System.out.println(requestURI);
        if(requestURI.contains("index") || requestURI.contains("static")){
            // 直接放行
            filterChain.doFilter(req,resp);
            System.out.println("放行了");
            // 后续代码不再执行
            return;
        }

       if(req.getParameter("method")!=null){
           //登录跳转界面
           if(req.getParameter("method").equals("login")){
               filterChain.doFilter(req,resp);
               return;
           }

           //注册跳转界面
           if(req.getParameter("method").equals("regist")){
               filterChain.doFilter(req,resp);
               return;
           }

           //提交登录界面
           if(req.getParameter("method").equals("login_success")){
               filterChain.doFilter(req,resp);
               return;
           }

           //提交注册界面
           if(req.getParameter("method").equals("regist_success")){
               filterChain.doFilter(req,resp);
               return;
           }

           //注册界面中判断用户是否存在，如果存在就不能注册，如果不存在就能注册
           if(req.getParameter("method").equals("regist_username")){
               filterChain.doFilter(req,resp);
               return;
           }

       }

        // 需要登录之后才能访问的资源,如果没登录,重定向到原网页上,提示用户进行登录
        HttpSession session = req.getSession();
        Object user = session.getAttribute("username");
        System.out.println(user);
        if(null != user){// 如果登录过 就都放行了
            filterChain.doFilter(req,resp);
            return;

        }
        else{// 没有登录过,跳转至登录页,没有登录的，购物车和后台管理都不能点
            resp.sendRedirect("http://localhost:8080/JavaWeb/User?method=login");
        }
    }
}
