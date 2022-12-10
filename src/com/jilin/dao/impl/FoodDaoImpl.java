package com.jilin.dao.impl;

import com.jilin.dao.FoodDao;
import com.jilin.entity.Business;
import com.jilin.entity.Cart;
import com.jilin.entity.Food;
import com.jilin.utils.MySqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
    @Override
    public List<Food> listFoodByBusinessId(String businessId) {
        List<Food> list = new ArrayList<>();
        try{
            Connection con = MySqlUtil.getConn();
            //cartQuery
            String sql = "Select * from food where businessId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, businessId);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Food food = new Food();
                food.setFoodId(rs.getInt("foodId"));
                food.setFoodName(rs.getString("foodName"));
                food.setFoodExplain(rs.getString("foodExplain"));
                food.setFoodImg(rs.getString("foodImg"));
                food.setFoodPrice(rs.getDouble("foodPrice"));
                food.setBusinessId(Integer.valueOf(businessId));
                food.setRemarks(rs.getString("remarks"));

                list.add(food);
            }
            MySqlUtil.close(rs,pst,con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
