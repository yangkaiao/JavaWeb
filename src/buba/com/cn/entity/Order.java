package buba.com.cn.entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @ClassName:order
 * @Auther: YooAo
 * @Description: 订单
 * @Date: 2022/10/13 11:01
 * @Version: v1.0
 */
public class Order {
    private Integer orderId;//订单id
    private Long orderNumber;//订单编号
    private Integer orderCount;//订单数量
    private BigDecimal orderAmount;//订单金额
    private Integer userId;//用户id
    private Integer orderStatus;//订单状态
    private Date createTime;//创建时间
    private Date UpdateTime;//更新时间
    private String comment;//备注

    public Order() {
    }

    public Order(Long orderNumber, Integer orderCount, BigDecimal orderAmount, Integer userId, Integer orderStatus) {
        this.orderNumber = orderNumber;
        this.orderCount = orderCount;
        this.orderAmount = orderAmount;
        this.userId = userId;
        this.orderStatus = orderStatus;
    }

    public Order(Integer orderId, Integer orderCount, BigDecimal orderAmount, Integer orderStatus) {
        this.orderId = orderId;
        this.orderCount = orderCount;
        this.orderAmount = orderAmount;
        this.orderStatus = orderStatus;
    }

    public Order(Integer orderId, Long orderNumber, Integer orderCount, BigDecimal orderAmount, Integer userId, Integer orderStatus, Date createTime, Date updateTime, String comment) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.orderCount = orderCount;
        this.orderAmount = orderAmount;
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.createTime = createTime;
        UpdateTime = updateTime;
        this.comment = comment;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Date updateTime) {
        UpdateTime = updateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderNumber=" + orderNumber +
                ", orderCount=" + orderCount +
                ", orderAmount=" + orderAmount +
                ", userId=" + userId +
                ", orderStatus=" + orderStatus +
                ", createTime=" + createTime +
                ", UpdateTime=" + UpdateTime +
                ", comment='" + comment + '\'' +
                '}';
    }
}
