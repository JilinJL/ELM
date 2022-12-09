package com.jilin.service.impl;

import com.jilin.dao.CartDao;
import com.jilin.dao.impl.CartDaoImpl;
import com.jilin.entity.Cart;
import com.jilin.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    @Override
    public List<Cart> listCart(String userId) {
        CartDao dao = new CartDaoImpl();
        return dao.listCart(userId);
    }

    @Override
    public int addCart(Cart cart) {
        CartDao dao = new CartDaoImpl();
        return dao.addCart(cart);
    }

    @Override
    public int updateCart(Cart cart) {
        CartDao dao = new CartDaoImpl();
        return dao.addCart(cart);
    }

    @Override
    public int removeCart(Cart cart) {
        CartDao dao = new CartDaoImpl();
        return dao.removeCart(cart);
    }
}
