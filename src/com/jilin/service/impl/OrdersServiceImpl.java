package com.jilin.service.impl;

import com.jilin.dao.OrdersDao;
import com.jilin.dao.impl.OrdersDaoImpl;
import com.jilin.entity.Orders;
import com.jilin.service.OrdersService;

import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    @Override
    public int createOrders(Orders order) {
        OrdersDao dao = new OrdersDaoImpl();
        return dao.createOrders(order);
    }

    @Override
    public List<Orders> listOrdersByUserId(String userId) {
        OrdersDao dao = new OrdersDaoImpl();
        return dao.listOrdersByUserId(userId);
    }

    @Override
    public Orders getOrderById(int orderId) {
        OrdersDao dao = new OrdersDaoImpl();
        return dao.getOrderById(orderId);
    }
}
