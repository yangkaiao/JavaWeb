package buba.com.cn.controller;

import buba.com.cn.entity.Book;
import buba.com.cn.entity.Cart;
import buba.com.cn.entity.User;
import buba.com.cn.service.BookAdminService;
import buba.com.cn.service.BookTypeService;
import buba.com.cn.service.CartItemService;
import buba.com.cn.service.impl.BookAdminServiceImpl;
import buba.com.cn.service.impl.BookTypeServiceImpl;
import buba.com.cn.service.impl.CartItemServiceImpl;
import buba.com.cn.utils.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndexServlet extends ViewBaseServlet {
    BookAdminService bookAdminService = new BookAdminServiceImpl();
    BookTypeService  bookTypeService = new BookTypeServiceImpl();
    CartItemService cartItemService = new CartItemServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //测行栏
        List bookType = this.findBookType();
        HttpSession session = req.getSession();
        session.setAttribute("bookTypeList",bookType);

//        User user = (User)session.getAttribute("currUser");//登录成功后的用户
//        Cart cart = cartItemService.getCart(user);
//        user.setCart(cart);
//        System.out.println(cart);
////      登录成功后把user对象存在会话域中,在购物车中有使用到
//        req.getSession().setAttribute("currUser",user);


        this.selectFindBook(req, resp);
        processTemplate("index",req, resp);


    }


    /**
     * 价格筛选 查找图书
     * */
    protected void selectFindBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //获取筛选价格 的两个input框
        String minprice = req.getParameter("minprice");
        String maxprice = req.getParameter("maxprice");
        System.out.println("最小值"+minprice);
        System.out.println("最da值"+maxprice);
        session.setAttribute("minprice",minprice);
        session.setAttribute("maxprice",maxprice);

        //图书最高价格
        Double pricemax = bookAdminService.PriceMax();
        System.out.println("最大价格"+pricemax);
        if(pricemax==null){
            pricemax=0.0;
        }

        //一开始进网址就是空的，把表中所以数据都查询出来 显示到页面中  显示全部就是 价格从0 到最大价格 然后调用分页功能
        if(minprice == null || maxprice == null){
            this.limitFindBook(0, (int) Math.ceil(pricemax),req,resp);
            System.out.println("向上取整"+(int) Math.ceil(pricemax));
        }
        //如果用了价格筛选功能 input框就不为空了，获取两个input框值 把两个value值传参调用分页查询limitFindBook()
        else{

            this.limitFindBook(Integer.parseInt(minprice),Integer.parseInt(maxprice),req,resp);
        }


    }


/**
* 分页查询图书
* */
    //因为所有数据要一套分页查询，而且价格筛选晒又是另一套分页，所有要准备两套分页，所有把这个分页封装成公共方法 到时候分别调用就行
    protected void limitFindBook(Integer min , Integer max , HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //触发那个点击事件 先到js里 然后再到这
        //页码
        int pageNo = 1;
        String pageNoStr = req.getParameter("pageNo");
        if(pageNoStr!=null){
            pageNo = Integer.parseInt(pageNoStr);
        }
        HttpSession session = req.getSession();
        session.setAttribute("pageNo",pageNo);

        //表中所有数据，总共条数，以下是设置的尾页
        int count = bookAdminService.IndexBookCount(min,max); //总条数
        System.out.println(count);
        int pageCount = (count+10-1)/10; //这个计算过程是，统计能够分出多少页 如果总条数有8条，（8+5-1）/5 = 2 ，意思就是最多有两页
        System.out.println(pageCount);
        session.setAttribute("pageCount",pageCount);//总页数
        session.setAttribute("count",count);//总条数

        //把页码整成数组形式
        List<Integer>  pageList = new ArrayList<>();
        for (int i = 1; i <= pageCount; i++){
            pageList.add(i);
        }
        req.setAttribute("pageList",pageList);


        //这是查询语句，要是不操作价格筛选的话传进来的是 0 和 最大价格，就把表中所有数据都显示页面了，
        //有可能传的是要筛选的价格的范围 比如想要10到20元的价格 就把10和20传进去了查询
        List<Book> book = bookAdminService.IndexFindBook(min,max,pageNo);
        System.out.println(book);
        req.setAttribute("bookList",book);

    }


    // 首页侧行栏查询书籍数据
    public List findBookType(){
        // 创建一级集合
        ArrayList<Object> list1 = new ArrayList<>();
        // 先调用方法查询所有一级类型
        List<String> oneLevel = bookTypeService.findOneLevel();
        // 循环一级,查询二级
        for (String one : oneLevel) {
            // 创建一个二级集合
            ArrayList<Object> list2 = new ArrayList<>();
            // 使用一级的内容,调用二级方法,去查找所有的二级
            List<String> twoLevel = bookTypeService.findTwoLevel(one);
            list2.add(one);  // 一级内容
            list2.add(twoLevel); // 一级对应的所有二级内容
            ArrayList<Object> list3 = new ArrayList<>(); // 再建新的三级集合
            for (String three : twoLevel) { // 循环二级内容
                List<String> threeLevel = bookTypeService.findTwoLevel(three); // 通过二级里边的内容,查找三级内容
                list3.add(threeLevel);
            }
            list2.add(list3);
            list1.add(list2);
        }
        return list1;


        /*
         * var arr = [
         *       [
         *           一级内容,
         *           [一级对应的二级内容],
         *           [
         *               [二级对应的三级内容],
         *               [二级对应的三级内容],
         *               [二级对应的三级内容]
         *           ]
         *       ],
         *       [
         *           一级内容,
         *           [一级对应的二级内容],
         *           [
         *               [二级对应的三级内容],
         *               [二级对应的三级内容],
         *               [二级对应的三级内容]
         *           ]
         *       ],
         *       ........
         *     ]
         *
         * */
    }


}
