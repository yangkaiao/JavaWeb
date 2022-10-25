package buba.com.cn.dao.impl;

import buba.com.cn.dao.BookAdminDao;
import buba.com.cn.entity.Book;
import buba.com.cn.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName:AdminDaoImol
 * @Auther: YooAo
 * @Description: 图书管理实现类
 * @Date: 2022/10/17 18:39
 * @Version: v1.0
 */
/*
 * 图书管理系统
 * */
public class BookAdminDaoImpl implements BookAdminDao {
    @Override
    public int addBook(Book book) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "insert into t_book(name, author, price, sales, stock, img_path) values(?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
        return update;
    }

    @Override
    public int delBook(Integer bookId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "delete from t_book where book_id = ?";
        int update = jdbcTemplate.update(sql, bookId);
        return update;
    }

    @Override
    public int upBook(Book book) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "update t_book set name = ?,price = ? , author = ? , sales = ? , stock = ? where book_id = ?";
        int update = jdbcTemplate.update(sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getBookId());
        return update;
    }

    ////通过id查询一条数据
    @Override
    public Book FindBookId(Integer bookId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "select * from t_book where book_id = ?";
        Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), bookId);
        return book;
    }

    //后台图书管理分页查找
    @Override
    public List<Book> findBook(Integer pageNo) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "select * from t_book limit ? , 5";
        List<Book> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class),(pageNo-1)*5);
        return query;
    }

    //后台图书系统统计表中所有数据 一页显示5条
    @Override
    public int BookCount() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "select count(*) from t_book";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        return integer;
    }


    ///价格筛选查询一共多少条数据
    @Override
    public int IndexBookCount(Integer min , Integer max) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "select count(*) from t_book where price between ? and ?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class , min,max);
        return integer;
    }


    //主页的图书列表分页查找  因为主页一页显示10条  后台图书管理一页显示5条  所有要分开写   price价格筛选查询
    @Override
    public List<Book> IndexFindBook(Integer priceleft,Integer priceright,Integer pageNo) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "select * from t_book where price between ? and ? limit ? , 10";
        List<Book> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class),priceleft,priceright,(pageNo-1)*10);
        return query;
    }

    //查找图书最高价格
    @Override
    public Double PriceMax() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "select max(price) from t_book";
        Double aDouble = jdbcTemplate.queryForObject(sql, Double.class);
        return aDouble;
    }


}
