package buba.com.cn.service.impl;

import buba.com.cn.dao.CartItemDao;
import buba.com.cn.dao.impl.CartItemDaoImpl;
import buba.com.cn.entity.Cart;
import buba.com.cn.entity.CartItem;
import buba.com.cn.entity.User;
import buba.com.cn.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:CartItemServiceIpml
 * @Auther: YooAo
 * @Description: 购物车项
 * @Date: 2022/10/24 08:29
 * @Version: v1.0
 */
public class CartItemServiceImpl implements CartItemService {
    private CartItemDao cartItemDao = new CartItemDaoImpl();

    //新增购物车项
    @Override
    public int addCartItem(CartItem cartItem) {
        return cartItemDao.addCartItem(cartItem);
    }

    //修改特定的购物车项
    @Override
    public int updateCartItem(CartItem cartItem) {
        return cartItemDao.updateCartItem(cartItem);
    }


    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        //1、如果当前用户的购物车中已经存在这个图书了，那么将购物车中这本图书的数量+1
        //2、否则、在我的购物车中新增一个这本图书的cartItem，数量是1
        //判断当前用户的购物车中是否有这本书的CartItem，有->update, 无 ->add
        if(cart!=null){
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if (cartItemMap==null){ //如果集合是空的就new一个出来
                cartItemMap = new HashMap<>();
            }
            // 判断集合中是否包含key 这个Map集合中的key是Book的id
            // 判断集合中是否包含book的id 如果包含就说明购物车中已经有这本书了，让这本书的数量+1，如果没有就添加
            if(cartItemMap.containsKey(cartItem.getBook().getBookId())){
                //.get():通过key获取value
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getBookId());
                cartItemTemp.setBookCount(cartItemTemp.getBookCount()+1);//设置这本书的数量，让这本书的数量+1
                updateCartItem(cartItemTemp);//更新一下数据
            }else{//如果没有就添加
                addCartItem(cartItem);
            }
        }else{ //如果连购物车都没有就添加一个购物车项
            addCartItem(cartItem);
        }
    }

    //加载特定用户的购物车信息
    @Override
    public Cart getCart(User user) {
        //当前用户的信息
        List<CartItem> cartItemList = cartItemDao.getCartItemList(user);
        HashMap<Integer,CartItem> cartItemMap = new HashMap<>();
        for(CartItem cartItem : cartItemList){
            cartItemMap.put(cartItem.getBook().getBookId(),cartItem);
        }
        System.out.println(cartItemMap);
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        return cart;
    }

    //删除购物车项
    @Override
    public int delCartItem(Integer cartId) {
        return cartItemDao.delCartItem(cartId);
    }


    //清空购物车
    @Override
    public int clearCartItem(Integer userId) {
        return cartItemDao.clearCartItem(userId);
    }
}
