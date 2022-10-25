package buba.com.cn.controller;

import buba.com.cn.entity.Book;
import buba.com.cn.service.BookTypeService;
import buba.com.cn.service.impl.BookTypeServiceImpl;
import buba.com.cn.utils.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName:LateralServlet
 * @Auther: YooAo
 * @Description:
 * @Date: 2022/10/21 15:35
 * @Version: v1.0
 */
@WebServlet("/lateral")
public class LateralServlet extends ViewBaseServlet {
    private BookTypeService bookTypeService = new BookTypeServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("method").equals("lateral")){
            this.lateral(req,resp);
        }
    }

    private void lateral(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String item3 = req.getParameter("item3");
        HttpSession session = req.getSession();
        session.setAttribute("item3",item3);

        // 通过类型查找数据
        List<Book> book = bookTypeService.findBookByType(item3);
        session.setAttribute("bookTypeList",book);

        processTemplate("/pages/lateral/lateral",req,resp);
    }
}
