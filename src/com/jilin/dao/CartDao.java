package com.jilin.dao;

import com.jilin.entity.Cart;

import java.util.List;

public interface CartDao {
    //查询购物车
    List<Cart> listCart(String userId);
    //添加到购物车
    int addCart(Cart cart);
    //修改购物车
    int updateCart(Cart cart);
    //删除购物车
    int removeCart(Cart cart);
}
