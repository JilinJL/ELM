package com.jilin.dao.impl;

import com.jilin.dao.OrdersDao;
import com.jilin.entity.*;
import com.jilin.utils.MySqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao {

    @Override
    public int createOrders(Orders order) {
        int ret=0;
        try{
            Connection con = MySqlUtil.getConn();
            String sql ="INSERT INTO orders values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, order.getUserId());
            pst.setInt(2, order.getBusinessId());
            pst.setInt(3, order.getDaId());
            pst.setDouble(4, order.getOrderTotal());
            ret = pst.executeUpdate();
            MySqlUtil.close(pst, con);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Orders> listOrdersByUserId(String userId) {
        List<Orders> list = new ArrayList<>();
        List<OrderDetailet> odList = new ArrayList<>();
        try{
            Connection con = MySqlUtil.getConn();
            String sql = "SELECT * FROM orders,orderdetailet,business,food WHERE userId = ? AND orders.orderId = orderdetailet.orderId;";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userId);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Orders order = new Orders();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getString("userId"));
                order.setBusinessId(rs.getInt("businessId"));
                order.setOrderDate(rs.getString("orderDate"));
                order.setOrderTotal(rs.getDouble("orderTotal"));
                order.setDaId(rs.getInt("daId"));
                order.setOrderState(rs.getInt("orderState"));

                //Business
                Business business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setBusinessImg(rs.getString("businessImg"));
                business.setOrderTypeId(rs.getInt("orderTypeId"));
                business.setStarPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
                business.setRemarks(rs.getString("remarks"));
                order.setBusiness(business);

                //Food
                Food food = new Food();
                food.setFoodId(rs.getInt("foodId"));
                food.setFoodName(rs.getString("foodName"));
                food.setFoodExplain(rs.getString("foodExplain"));
                food.setFoodImg(rs.getString("foodImg"));
                food.setBusinessId(rs.getInt("businessId"));
                food.setRemarks(rs.getString("remarks"));

                //OrderDetailet
                OrderDetailet od = new OrderDetailet();
                od.setOrderId(rs.getInt("orderId"));
                od.setFoodId(rs.getInt("foodId"));
                od.setQuantity(rs.getInt("quantity"));
                od.setFood(food);
                odList.add(od);

                list.add(order);
            }
            MySqlUtil.close(pst,con);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Orders getOrderById(int orderId) {
        try {
            Connection con = MySqlUtil.getConn();
            String sql = "SELECT * FROM orders where orderId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Orders order = new Orders();
                order.setOrderId(orderId);
                order.setUserId(rs.getString("userId"));
                order.setBusinessId(rs.getInt("businessId"));
                order.setOrderDate(rs.getString("orderDate"));
                order.setOrderTotal(rs.getDouble("orderTotal"));
                order.setDaId(rs.getInt("daId"));
                order.setOrderState(rs.getInt("orderState"));

                MySqlUtil.close(pst, con);
                return order;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
