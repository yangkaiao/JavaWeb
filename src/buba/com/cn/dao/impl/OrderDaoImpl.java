package buba.com.cn.dao.impl;

import buba.com.cn.dao.OrderDao;
import buba.com.cn.entity.Order;
import buba.com.cn.entity.OrderDetail;
import buba.com.cn.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName:OrderDaoImpl
 * @Auther: YooAo
 * @Description: 订单实现类
 * @Date: 2022/10/26 09:12
 * @Version: v1.0
 */
public class OrderDaoImpl implements OrderDao {
    //查找全部订单
    @Override
    public List<Order> findOrder(Integer userId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "select * from t_order where user_id = ?";
        List<Order> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class),userId);
        return query;
    }

    //去结账把购物车信息添加到订单
    @Override
    public int addOrder(Order order) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "insert into t_order(order_number,order_count,order_amount,user_id,order_status) values(?,?,?,?,?)";
        int update = jdbcTemplate.update(sql,order.getOrderNumber(),order.getOrderCount(),order.getOrderAmount(),order.getUserId(),order.getOrderStatus());
        return update;
    }


    //订单详情查询
    @Override
    public List<OrderDetail> findOrderDetail(Integer orderId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "select * from t_order_detail where order_id = ?";
        List<OrderDetail> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderDetail.class),orderId);
        return query;
    }

    //订单详情添加
    @Override
    public int addOrderDetail(OrderDetail detail) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "insert into t_order_detail(book_img,book_id,book_name,book_count,amount,order_id) values(?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, detail.getBookImg(), detail.getBookId(), detail.getBookName(), detail.getBookCount(), detail.getAmount(), detail.getOrderId());
        return update;
    }


    //退款删除订单
    @Override
    public int delOrder(Integer orderId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "delete from t_order where order_id = ?";
        int update = jdbcTemplate.update(sql, orderId);
        return update;
    }

    //删除详情订单信息
    @Override
    public int delOrderDetail(Integer orderId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "delete from t_order_detail where order_id = ?";
        int update = jdbcTemplate.update(sql, orderId);
        return update;
    }


    //后台管理的订单查找全部
    @Override
    public List<Order> findOrder() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "select * from t_order";
        List<Order> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));
        return query;
    }


    //修改订单状态 是否发货
    @Override
    public int updateOrderStatus(Integer orderId, Integer status) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "update t_order set order_status = ? where order_id = ?";
        int update = jdbcTemplate.update(sql, status,orderId);
        return update;
    }


}
