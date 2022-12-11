package com.jilin.dao;

import com.jilin.entity.Orders;

import java.util.List;

public interface OrdersDao {
    //创建订单
    int createOrders(Orders order);
    //获取用户订单
    List<Orders> listOrdersByUserId(String userId);
    //根据订单id查询订单
    Orders getOrderById(int orderId);
}
