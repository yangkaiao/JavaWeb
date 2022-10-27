package buba.com.cn.service;

import buba.com.cn.entity.Order;
import buba.com.cn.entity.OrderDetail;

import java.util.List;

public interface OrderService {
    //查找全部订单
    List<Order> findOrder(Integer userId);

    //添加订单
    int addOrder(Order order);

    //订单详情查询
    List<OrderDetail> findOrderDetail(Integer orderId);

    //订单详情添加
    int addOrderDetail(OrderDetail detail);

    //通过订单编号查询订单id
    Order findOrderByNum(Long Number);

    //退款删除订单
    int delOrder(Integer orderId);

    //退款删除详情订单
    int delOrderDetail(Integer orderId);

    //后台管理的订单查找全部
    List<Order> findOrder();

    //修改订单状态 是否发货
    int updateOrderStatus(Integer orderId,Integer status);
}
