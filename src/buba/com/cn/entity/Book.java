package buba.com.cn.entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @ClassName:Book
 * @Auther: YooAo
 * @Description: 图书信息表
 * @Date: 2022/10/13 10:39
 * @Version: v1.0
 */
public class Book {
    private Integer bookId;//图书id
    private String name;//图书名称
    private Double price;//图书价格
    private String author;//图书作者
    private Integer sales;//图书销量
    private Integer stock;//图书库存
    private String imgPath;//图书图片
    private Date createTime;//创建时间
    private Date UpdateTime;//更新时间
    private String comment;//备注

    public Book() {
    }

    public Book(Integer bookId) {
        this.bookId = bookId;
    }

    public Book(Integer bookId, String name, Double price, String author, Integer sales, Integer stock) {
        this.bookId = bookId;
        this.name = name;
        this.price = price;
        this.author = author;
        this.sales = sales;
        this.stock = stock;
    }

    public Book(Integer bookId, String name, Double price, String author, Integer sales, Integer stock, String imgPath, Date createTime, Date updateTime, String comment) {
        this.bookId = bookId;
        this.name = name;
        this.price = price;
        this.author = author;
        this.sales = sales;
        this.stock = stock;
        this.imgPath = imgPath;
        this.createTime = createTime;
        UpdateTime = updateTime;
        this.comment = comment;
    }

    public Book(String name, Double price, String author, Integer sales, Integer stock) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.sales = sales;
        this.stock = stock;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
        return "Book{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                ", createTime=" + createTime +
                ", UpdateTime=" + UpdateTime +
                ", comment='" + comment + '\'' +
                '}';
    }
}
