package buba.com.cn.dao;

import buba.com.cn.entity.Order;
import buba.com.cn.entity.OrderDetail;

import java.util.List;

public interface OrderDao {
    //查找全部
    List<Order> findOrder(Integer userId);

    //添加订单
    int addOrder(Order order);

    //订单详情查询
    List<OrderDetail> findOrderDetail(Integer orderId);

    //订单详情添加
    int addOrderDetail(OrderDetail detail);

    //退款删除订单
    int delOrder(Integer orderId);

    //退款删除详情订单
    int delOrderDetail(Integer orderId);

    //后台管理的订单查找全部
    List<Order> findOrder();
}
