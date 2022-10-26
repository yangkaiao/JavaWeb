package buba.com.cn.entity;



import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName:Cart
 * @Auther: YooAo
 * @Description: 购物车
 * @Date: 2022/10/24 08:49
 * @Version: v1.0
 */
public class Cart {
    private Map<Integer, CartItem> cartItemMap;//购物车中购物车项的集合，这个Map集合中的key是Book的id
    private BigDecimal totalMoney; //购物车的总金额
    private Integer totalCount; //购物车中购物项的数量
    private Integer totalBookCount; //购物车中书的总数量，而不是购物车项的数量
    public Cart() {
    }

    public Cart(Map<Integer, CartItem> cartItemMap, BigDecimal totalMoney, Integer totalCount) {
        this.cartItemMap = cartItemMap;
        this.totalMoney = totalMoney;
        this.totalCount = totalCount;
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    //求总金额
    public BigDecimal getTotalMoney() {
        totalMoney = BigDecimal.valueOf(0.0);
        //这个判断就说明购物车中有图书了，循环把图书的价格*数量 就求出购物车项的总金额了
        if(cartItemMap!=null && cartItemMap.size()>0){
            //获取Map内部维护的键值对对象entry对象 获取键值对
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for(Map.Entry<Integer, CartItem> cartItemEntry : entries){
                CartItem cartItem = cartItemEntry.getValue();
                //totalMoney+=cartItem.getBook().getPrice()*cartItem.getBookCount()
                //BigDecimal中.add代表相加 .multiply代表相乘
                totalMoney = totalMoney.add(cartItem.getBook().getPrice().multiply(BigDecimal.valueOf(cartItem.getBookCount())));
            }
        }
        return totalMoney;
    }


    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    //总数量、总共有多少件商品
    public Integer getTotalCount() {
        totalCount = 0;
        //这个判断就说明购物车中有图书了，求出购物车总共有多少件商品 就是集合的长度
        if(cartItemMap!=null && cartItemMap.size() >0){
            totalCount = cartItemMap.size();
        }
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    //购物车中书的总数量，而不是购物车项的数量
    public Integer getTotalBookCount() {
        totalBookCount = 0;
        if(cartItemMap!=null && cartItemMap.size() > 0){
            for(CartItem cartItem : cartItemMap.values()){
                totalBookCount += cartItem.getBookCount();
            }
        }
        return totalBookCount;
    }

    public void setTotalBookCount(Integer totalBookCount) {
        this.totalBookCount = totalBookCount;
    }
}
