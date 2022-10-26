package buba.com.cn.dao.impl;

import buba.com.cn.dao.BookAdminDao;
import buba.com.cn.dao.CartItemDao;
import buba.com.cn.entity.Book;
import buba.com.cn.entity.CartItem;
import buba.com.cn.entity.User;
import buba.com.cn.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName:CartItemDaoImpl
 * @Auther: YooAo
 * @Description:购物车实现类
 * @Date: 2022/10/23 20:53
 * @Version: v1.0
 */
public class CartItemDaoImpl implements CartItemDao {
   private BookAdminDao bookAdminDao = new BookAdminDaoImpl();

    //新增购物车项
    @Override
    public int addCartItem(CartItem cartItem) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "insert into t_cart_item values(0,?,?,?)";
        int update = jdbcTemplate.update(sql, cartItem.getBook().getBookId(), cartItem.getBookCount(), cartItem.getUser().getUserId());
        return update;
    }

    //修改特定的购物车项
    @Override
    public int updateCartItem(CartItem cartItem) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "update t_cart_item set book_count = ? where cart_id = ?";
        int update = jdbcTemplate.update(sql, cartItem.getBookCount(), cartItem.getCartId());
        return update;
    }

    //删除购物车项
    @Override
    public int delCartItem(Integer cartId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "delete from t_cart_item where cart_id = ?";
        int update = jdbcTemplate.update(sql, cartId);
        return update;
    }

    //清空购物车
    @Override
    public int clearCartItem(Integer userId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "delete from t_cart_item where user_id = ?";
        int update = jdbcTemplate.update(sql, userId);
        return update;
    }

    //获取特定用户的所有购物车项  通过每个用户的id查找cartItem实体类中信息
    @Override
    public List<CartItem> getCartItemList(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "select * from t_cart_item where user_id = ?";
        List<CartItem> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CartItem.class), user.getUserId());
        for (CartItem cartItem : query) {
            //通过加入购物车的那本书的id查整本书的信息
            Integer bookId = cartItem.getBookId();
            Book book = bookAdminDao.FindBookId(bookId);
            cartItem.setBook(book);
        }
        System.out.println(query);
        return query;
    }
}
