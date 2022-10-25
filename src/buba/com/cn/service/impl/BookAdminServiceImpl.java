package buba.com.cn.service.impl;

import buba.com.cn.dao.BookAdminDao;
import buba.com.cn.dao.impl.BookAdminDaoImpl;
import buba.com.cn.entity.Book;
import buba.com.cn.service.BookAdminService;

import java.util.List;

/**
 * @ClassName:BookAdminServiceImpl
 * @Auther: YooAo
 * @Description: 图书管理系统 实现类
 * @Date: 2022/10/17 18:44
 * @Version: v1.0
 */
public class BookAdminServiceImpl implements BookAdminService {
    BookAdminDao bookAdminDao = new BookAdminDaoImpl();
    @Override
    public int addBook(Book book) {
        int i = bookAdminDao.addBook(book);
        return i;
    }

    @Override
    public int delBook(Integer bookId) {
        return bookAdminDao.delBook(bookId);
    }

    @Override
    public int upBook(Book book) {
        return bookAdminDao.upBook(book);
    }

    //分页查找
    @Override
    public List<Book> findBook(Integer pageNo) {
        return bookAdminDao.findBook(pageNo);
    }

    //统计表中有多少条数据
    @Override
    public int BookCount() {
        return bookAdminDao.BookCount();
    }

    @Override
    public int IndexBookCount(Integer min, Integer max) {
        return bookAdminDao.IndexBookCount(min, max);
    }

    //主页的图书列表分页查找  因为主页一页显示10条  后台图书管理一页显示5条  所有要分开写
    @Override
    public List<Book> IndexFindBook(Integer priceleft,Integer priceright,Integer pageNo) {
        return bookAdminDao.IndexFindBook(priceleft,priceright,pageNo);
    }

    //查找图书最高价格
    @Override
    public Double PriceMax() {
        return bookAdminDao.PriceMax();
    }

    @Override
    public Book FindBookId(Integer bookId) {
        return bookAdminDao.FindBookId(bookId);
    }
}
