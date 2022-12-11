package com.jilin.controller;

import com.jilin.entity.DeliveryAddress;
import com.jilin.entity.Orders;
import com.jilin.service.OrdersService;
import com.jilin.service.impl.OrdersServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrdersController {
    //创建订单
    public int createOrders(HttpServletRequest request){
        Orders order = new Orders();
        order.setUserId(request.getParameter("userId"));
        order.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
        order.setDaId(Integer.valueOf(request.getParameter("daId")));
        order.setOrderTotal(Double.valueOf(request.getParameter("totalPrice")));
        OrdersService ordersService = new OrdersServiceImpl();
        return ordersService.createOrders(order);
    }
    //获取用户订单
    public List<Orders> listOrdersByUserId(HttpServletRequest request){
        String userId = request.getParameter("userId");
        OrdersService ordersService = new OrdersServiceImpl();
        return ordersService.listOrdersByUserId(userId);
    }
    //根据订单id查询订单
    public Orders getOrderById(HttpServletRequest request){
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrdersService ordersService = new OrdersServiceImpl();
        return ordersService.getOrderById(orderId);
    }
}
