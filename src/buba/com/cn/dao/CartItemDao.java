package buba.com.cn.dao;

import buba.com.cn.entity.CartItem;
import buba.com.cn.entity.User;

import java.util.List;

public interface CartItemDao {
    //新增购物车项
    int addCartItem(CartItem cartItem);

    //修改特定的购物车项
    int updateCartItem(CartItem cartItem);

    //删除购物车项
    int delCartItem(Integer cartId);

    //清空购物车项
    int clearCartItem(Integer userId);

    //获取特定用户的所有购物车项
    List<CartItem> getCartItemList(User user);
}
