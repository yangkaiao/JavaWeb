package buba.com.cn.service.impl;

import buba.com.cn.dao.BookTypeDao;
import buba.com.cn.dao.impl.BookTypeDaoImpl;
import buba.com.cn.entity.Book;
import buba.com.cn.service.BookTypeService;

import java.util.List;

/**
 * @ClassName:BookTypeServiceImpl
 * @Auther: YooAo
 * @Description:
 * @Date: 2022/10/21 15:13
 * @Version: v1.0
 */
public class BookTypeServiceImpl implements BookTypeService {
    BookTypeDao bookTypeDao = new BookTypeDaoImpl();
    @Override
    public List<String> findOneLevel() {
        return bookTypeDao.findOneLevel();
    }

    @Override
    public List<String> findTwoLevel(String ParentName) {
        return bookTypeDao.findTwoLevel(ParentName);
    }

    @Override
    public List<String> findThreeLevel(String ParentName) {
        return bookTypeDao.findThreeLevel(ParentName);
    }

    @Override
    public List<Book> findBookByType(String bookType) {
        return bookTypeDao.findBookByType(bookType);
    }
}
