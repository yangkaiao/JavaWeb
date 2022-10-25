package buba.com.cn.entity;


/**
 * @ClassName:CartItem
 * @Auther: YooAo
 * @Description: 购物车的图书项
 * @Date: 2022/10/21 10:59
 * @Version: v1.0
 */
public class CartItem {
    private Integer cartId;//购物车id
    private Book book;//图书信息
    private Integer bookId;
    private Integer bookCount;//购物车图书数量
    private User user;//用户
    private Integer userId;


    public CartItem() {
    }

    public CartItem(Integer cartId, Integer bookCount) {
        this.cartId = cartId;
        this.bookCount = bookCount;
    }

    public CartItem(Book book, Integer bookCount, User user) {
        this.book = book;
        this.bookCount = bookCount;
        this.user = user;
    }

    public CartItem(Integer cartId) {
        this.cartId = cartId;
    }

    public CartItem(Integer cartId, Book book, Integer cartCount, User user) {
        this.cartId = cartId;
        this.book = book;
        this.bookCount = cartCount;
        this.user = user;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartId=" + cartId +
                ", book=" + book +
                ", bookId=" + bookId +
                ", bookCount=" + bookCount +
                ", user=" + user +
                ", userId=" + userId +
                '}';
    }
}
