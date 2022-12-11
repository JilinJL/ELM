package com.jilin.dao.impl;

import com.jilin.dao.BusinessDao;
import com.jilin.entity.Business;
import com.jilin.utils.MySqlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaoImpl implements BusinessDao {

    //获取分类商家
    @Override
    public List<Business> listBusiness(Integer orderTypeId) {
        //System.out.println("获取列表成功");
        List<Business> busList = new ArrayList<>();
        try{
            Connection con = MySqlUtil.getConn();
            String sql = "Select * from business where orderTypeId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, String.valueOf(orderTypeId));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Business bus = new Business();
                bus.setBusinessId(rs.getInt("orderTypeId"));
                bus.setBusinessName(rs.getString("businessName"));
                bus.setBusinessAddress(rs.getString("businessAddress"));
                bus.setBusinessExplain(rs.getString("businessExplain"));
                bus.setBusinessImg(rs.getString("businessImg"));
                bus.setStarPrice(rs.getDouble("starPrice"));
                bus.setDeliveryPrice(rs.getDouble("deliveryPrice"));
                bus.setRemarks(rs.getString("remarks"));
                busList.add(bus);
            }
            MySqlUtil.close(rs,pst,con);
        }catch (Exception e){
            e.printStackTrace();

        }
        return busList;
    }

    //根据id获取信息
    @Override
    public Business getBusinessById(String businessId) {
        try {
        //System.out.println("搜索成功");
        Connection con = MySqlUtil.getConn();
        String sql = "SELECT * FROM business where businessId = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, String.valueOf(businessId));
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
            Business bus = new Business();
            bus.setBusinessId(rs.getInt("orderTypeId"));
            bus.setBusinessName(rs.getString("businessName"));
            bus.setBusinessAddress(rs.getString("businessAddress"));
            bus.setBusinessExplain(rs.getString("businessExplain"));
            bus.setBusinessImg(rs.getString("businessImg"));
            bus.setStarPrice(rs.getDouble("starPrice"));
            bus.setDeliveryPrice(rs.getDouble("deliveryPrice"));
            bus.setRemarks(rs.getString("remarks"));
            return bus;
        }

            MySqlUtil.close(rs,pst,con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
