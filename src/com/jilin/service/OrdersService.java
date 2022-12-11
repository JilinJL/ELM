package com.jilin.service;

import com.jilin.entity.Orders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrdersService {
    //创建订单
    int createOrders(Orders order);
    //获取用户订单
    List<Orders> listOrdersByUserId(String userId);
    //根据订单id查询订单
    Orders getOrderById(int orderId);
}
