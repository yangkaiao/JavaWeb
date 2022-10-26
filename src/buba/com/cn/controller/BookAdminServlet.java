package buba.com.cn.controller;

import buba.com.cn.entity.Book;

import buba.com.cn.entity.User;
import buba.com.cn.service.BookAdminService;
import buba.com.cn.service.impl.BookAdminServiceImpl;
import buba.com.cn.utils.ViewBaseServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.Long.parseLong;

/*
* 图书管理Servlet
* */
public class BookAdminServlet extends ViewBaseServlet {
    BookAdminService bookAdminService = new BookAdminServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");



        //后台管理跳转界面
        if(req.getParameter("method").equals("book_manager")){
            this.book_manager(req, resp);

        }

        //后台管理跳转界面里的添加跳转界面
        if(req.getParameter("method").equals("book_add")){
            //请求转发跳转到/WEB-INF/view/pages/user/login.html
            processTemplate("pages/manager/book_edit",req, resp);
        }

        //后台管理跳转界面里的添加跳转界面里的提交后的
        if(req.getParameter("method").equals("book_addSubmit")){
            this.book_addSubmit(req, resp);
        }

        //后台管理跳转界面里的修改跳转界面
        if(req.getParameter("method").equals("book_update")){
            this.book_update(req, resp);
            //请求转发跳转到/WEB-INF/view/pages/user/login.html

        }

        //后台管理跳转界面里的修改提交后的跳转界面
        if(req.getParameter("method").equals("book_updateSubmit")){
            this.book_updateSubmit(req, resp);
        }

        //后台管理跳转界面里的删除图书
        if(req.getParameter("method").equals("book_delete")){
            this.book_delete(req, resp);
        }

    }




    //分页查找
    private void book_manager(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //页码
        int pageNo = 1;
        String pageNoStr = req.getParameter("pageNo");
        if(pageNoStr!=null){
            pageNo = Integer.parseInt(pageNoStr);
        }
        HttpSession session = req.getSession();
        session.setAttribute("pageNo",pageNo);

        //表中所有数据，总共条数，以下是设置的尾页
        int count = bookAdminService.BookCount(); //总条数
        System.out.println(count);
        int pageCount = (count+5-1)/5; //这个计算过程是，统计能够分出多少页 如果总条数有8条，（8+5-1）/5 = 2 ，意思就是最多有两页
        System.out.println(pageCount);
        session.setAttribute("pageCount",pageCount);
        session.setAttribute("count",count);

        //把页码整成数组形式
        List<Integer>  pageList = new ArrayList<>();
        for (int i = 1; i <= pageCount; i++){
            pageList.add(i);
        }
        req.setAttribute("pageList",pageList);


        List<Book> book = bookAdminService.findBook(pageNo);
        req.setAttribute("bookList",book);

        //请求转发跳转到/WEB-INF/view/pages/user/login.html
        processTemplate("pages/manager/book_manager",req, resp);
    }

    //添加图书
    private void book_addSubmit(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        System.out.println("提交");
        System.out.println("方法开始执行");
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        // 验证请求是否满足要求（post 请求 / enctype 是否以multipart打头
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        // 如果不满足要求就立即结束对该请求的处理
        if (!isMultipart) {
            return;
        }
        try {
            // FileItem 是表单中的每一个元素的封装
            // 创建一个 FileItem 的工厂类
            FileItemFactory factory = new DiskFileItemFactory();
            // 创建一个文件上传处理器（装饰设计模式）
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解析请求
            List<FileItem> items = upload.parseRequest(req);
            System.out.println(items);


            FileItem fileItem = items.get(0); //获取到的图片
            // 文件类型
            // 获取文件后缀名
            String imgtype = fileItem.getName().substring(fileItem.getName().lastIndexOf("."));
            // 给文件重新命名防止重复
            String imgName = UUID.randomUUID() + imgtype;
            System.out.println();
            //将要保存的文件夹
            String path="D:\\Java\\网上书店项目\\JavaWeb\\web\\static\\uploads";
            // 将上传的文件保存到服务器
            fileItem.write(new File(path, imgName));

            // 把服务器中的路径添加到数据库中
            String sqlPath=null;
            sqlPath = "static/uploads/" + imgName;

            Book book = new Book();
            book.setImgPath(sqlPath); //sqlPath是 static/uploads/路径 加图片名字添加到了数据库

            //普通数据
            book.setName(items.get(1).getString("UTF-8"));
            book.setPrice(new BigDecimal(items.get(2).getString("UTF-8")));
            book.setAuthor(items.get(3).getString("UTF-8"));
            book.setSales(Integer.valueOf(items.get(4).getString("UTF-8")));
            book.setStock(Integer.valueOf(items.get(5).getString("UTF-8")));
            System.out.println("访问路径：" + sqlPath);
            bookAdminService.addBook(book);
//            this.book_manager(req, resp);
            HttpSession session = req.getSession();
            Object pageCount = session.getAttribute("pageCount");
            resp.sendRedirect("/JavaWeb/Admin?method=book_manager&pageNo="+pageCount);//跳到最后一页
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //删除图书
    private void book_delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String bookId = req.getParameter("bookId");
        String pageNo = req.getParameter("pageNo");//当前页

        bookAdminService.delBook(Integer.valueOf(bookId));
        //查询当前页集合
        List<Book> book = bookAdminService.findBook(Integer.valueOf(pageNo));
        //如果当前页集合为空就让当前页-1到上一页，
        if(book.isEmpty()){
            //在如果页码在第一页就不让它-1 如果删完第一页就到前端页面设置了如果为空就显示 对不起库存为空了
            if(Integer.valueOf(pageNo)==1){
                resp.sendRedirect("/JavaWeb/Admin?method=book_manager&pageNo="+pageNo);
            }else{
                 resp.sendRedirect("/JavaWeb/Admin?method=book_manager&pageNo="+(Integer.valueOf(pageNo)-1));
            }
        }else {
            resp.sendRedirect("/JavaWeb/Admin?method=book_manager&pageNo="+pageNo);//跳到当前页
        }

    }

    //修改图书
    private void book_update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String bookId = req.getParameter("bookId");
        Book book = bookAdminService.FindBookId(Integer.valueOf(bookId));
        req.setAttribute("bookUpdateList",book);
        processTemplate("pages/manager/book_update",req, resp);
    }

    //修改图书提交后的
    private void book_updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String bookId = req.getParameter("id");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String author = req.getParameter("author");
        String sales = req.getParameter("sales");
        String stock = req.getParameter("stock");
        Object pageNo = req.getSession().getAttribute("pageNo");
        Book book = new Book(Integer.valueOf(bookId), name, new BigDecimal(price), author, Integer.valueOf(sales), Integer.valueOf(stock));
        int i = bookAdminService.upBook(book);
        System.out.println(i);
        System.out.println(book);
        resp.sendRedirect("/JavaWeb/Admin?method=book_manager&pageNo="+pageNo); //跳到当前页
    }
}
