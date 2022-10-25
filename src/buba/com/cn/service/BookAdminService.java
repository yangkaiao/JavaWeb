package buba.com.cn.service;

import buba.com.cn.entity.Book;

import java.util.List;

/*
 * 图书管理系统
 * */
public interface BookAdminService {
    //增加
    int addBook(Book book);

    //删除
    int delBook(Integer bookId);

    //修改
    int upBook(Book book);

    //后台图书管理分页查找 一页显示5条
    List<Book> findBook(Integer pageNo);

    //统计表中有多少条数据
    int BookCount();

    //统计表中有多少条数据
    int IndexBookCount(Integer min , Integer max);

    //主页的图书列表分页查找  因为主页一页显示10条  后台图书管理一页显示5条  所有要分开写
    List<Book> IndexFindBook(Integer priceleft,Integer priceright,Integer pageNo);

    //查找图书最高价格
    Double PriceMax();

    //通过id查询一条数据
    Book FindBookId(Integer bookId);
}
