package buba.com.cn.service;

import buba.com.cn.entity.Book;

import java.util.List;

public interface BookTypeService {
    List<String> findOneLevel();
    List<String> findTwoLevel(String ParentName);
    List<String> findThreeLevel(String ParentName);
    List<Book> findBookByType(String bookType);
}
