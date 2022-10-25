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
    private Integer orderId;//订单编号
    private Integer orderCount;//订单数量
    private BigDecimal orderAmount;//订单金额
    private Integer userId;//用户id
    private Integer userStatus;//订单状态
    private Date createTime;//创建时间
    private Date UpdateTime;//更新时间
    private String comment;//备注

    public Order() {
    }

    public Order(Integer orderId, Integer orderCount, BigDecimal orderAmount, Integer userId, Integer userStatus, Date createTime, Date updateTime, String comment) {
        this.orderId = orderId;
        this.orderCount = orderCount;
        this.orderAmount = orderAmount;
        this.userId = userId;
        this.userStatus = userStatus;
        this.createTime = createTime;
        UpdateTime = updateTime;
        this.comment = comment;
    }

    public Order(Integer orderId, Integer orderCount, BigDecimal orderAmount, Integer userStatus) {
        this.orderId = orderId;
        this.orderCount = orderCount;
        this.orderAmount = orderAmount;
        this.userStatus = userStatus;
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

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
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

    @Override
    public String toString() {
        return "order{" +
                "orderId=" + orderId +
                ", orderCount=" + orderCount +
                ", orderAmount=" + orderAmount +
                ", userId=" + userId +
                ", userStatus=" + userStatus +
                ", createTime=" + createTime +
                ", UpdateTime=" + UpdateTime +
                ", comment='" + comment + '\'' +
                '}';
    }
}
