package buba.com.cn.service;

import buba.com.cn.entity.Cart;
import buba.com.cn.entity.CartItem;
import buba.com.cn.entity.User;

public interface CartItemService {
    //新增购物车项
    int addCartItem(CartItem cartItem);

    //修改特定的购物车项
    int updateCartItem(CartItem cartItem);

    void addOrUpdateCartItem(CartItem cartItem , Cart cart);

    //加载特定用户的购物车信息
    Cart getCart(User user);

    //删除购物车项
    int delCartItem(Integer cartId);

    //清空购物车项
    int clearCartItem(Integer userId);
}
