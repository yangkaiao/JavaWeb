package buba.com.cn.service.impl;

import buba.com.cn.dao.OrderDao;
import buba.com.cn.dao.impl.OrderDaoImpl;
import buba.com.cn.entity.Order;
import buba.com.cn.entity.OrderDetail;
import buba.com.cn.service.OrderService;
import buba.com.cn.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName:OrderServiceImpl
 * @Auther: YooAo
 * @Description:
 * @Date: 2022/10/26 09:20
 * @Version: v1.0
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    //查询订单
    @Override
    public List<Order> findOrder(Integer userId) {
        return orderDao.findOrder(userId);
    }

    //添加订单
    @Override
    public int addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    //查询订单详情
    @Override
    public List<OrderDetail> findOrderDetail(Integer orderId) {
        return orderDao.findOrderDetail(orderId);
    }

    @Override
    public int addOrderDetail(OrderDetail detail) {
        return orderDao.addOrderDetail(detail);
    }


    //通过订单编号查询订单id
    @Override
    public Order findOrderByNum(Long Number) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
        String sql = "select * from t_order where order_number = ?";
        Order order = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Order.class), Number);
        return order;
    }


    //退款删除订单
    @Override
    public int delOrder(Integer orderId) {
        return orderDao.delOrder(orderId);
    }


    //删除订单详情
    @Override
    public int delOrderDetail(Integer orderId) {
        return orderDao.delOrderDetail(orderId);
    }



    //后台管理的订单查找全部
    @Override
    public List<Order> findOrder() {
        return orderDao.findOrder();
    }


}
