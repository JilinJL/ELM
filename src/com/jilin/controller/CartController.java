package com.jilin.controller;

import com.jilin.entity.Cart;
import com.jilin.entity.User;
import com.jilin.service.CartService;
import com.jilin.service.impl.CartServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CartController {

    //购物车列表
    public List<Cart> listCart(HttpServletRequest request){
        String userId = request.getParameter("userId");
        CartService cartserv = new CartServiceImpl();
        return cartserv.listCart(userId);

    }
    //添加到购物车
    public int saveCart(HttpServletRequest request){
        Cart cart = new Cart();
        cart.setCartId(Integer.valueOf(request.getParameter("cartId")));
        cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
        cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
        cart.setUserId(request.getParameter("userId"));
        cart.setQuantity(Integer.valueOf(request.getParameter("quantity")));
        CartService cartserv = new CartServiceImpl();
        return cartserv.addCart(cart);
    }
    //修改购物车
    public User updateCart(HttpServletRequest request){
        return null;
    }
    //删除购物车
    public int removeCart(HttpServletRequest request){
        return 0;
    }
}
