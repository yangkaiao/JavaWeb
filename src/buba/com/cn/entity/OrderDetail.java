package buba.com.cn.entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @ClassName:OederDetail
 * @Auther: YooAo
 * @Description: 订单明细
 * @Date: 2022/10/13 11:07
 * @Version: v1.0
 */
public class OrderDetail {
    private Integer ItemId;//主键
    private Integer bookId;//图书id
    private String bookName;//图书名字
    private Integer bookCount;//图书数量
    private BigDecimal amount;//金额
    private Integer orderId;//订单编号
    private Date createTime;//创建时间
    private Date UpdateTime;//更新时间
    private String comment;//备注

    public OrderDetail() {
    }

    public OrderDetail(Integer itemId, Integer bookId, String bookName, Integer bookCount, BigDecimal amount, Integer orderId, Date createTime, Date updateTime, String comment) {
        ItemId = itemId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookCount = bookCount;
        this.amount = amount;
        this.orderId = orderId;
        this.createTime = createTime;
        UpdateTime = updateTime;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "ItemId=" + ItemId +
                ", bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookCount=" + bookCount +
                ", amount=" + amount +
                ", orderId=" + orderId +
                ", createTime=" + createTime +
                ", UpdateTime=" + UpdateTime +
                ", comment='" + comment + '\'' +
                '}';
    }
}
