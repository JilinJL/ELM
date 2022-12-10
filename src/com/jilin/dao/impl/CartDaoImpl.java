package com.jilin.dao.impl;

import com.jilin.dao.CartDao;
import com.jilin.entity.Business;
import com.jilin.entity.Cart;
import com.jilin.entity.Food;
import com.jilin.utils.MySqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    @Override
    public List<Cart> listCart(String userId) {
        List<Cart> list = new ArrayList<>();
        try{
            Connection con = MySqlUtil.getConn();
            //cartQuery
            String sql = "Select * from cart,food,business where cart.foodId = food.foodId and cart.businessId = business.businessId and cart.userId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userId);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Cart cart = new Cart();
                cart.setCartId(rs.getInt("cartId"));
                cart.setUserId(rs.getString("userId"));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setFoodId(rs.getInt("foodId"));
                cart.setBusinessId(rs.getInt("businessId"));

                //TODO 把Food和Business填上
                cart.setFood(new Food(rs.getInt("foodId"),rs.getString("foodName"),rs.getString("foodExplain"),rs.getString("foodImg"),rs.getDouble("foodPrice"),rs.getInt("businessId"),rs.getString("remarks")));
                cart.setBusiness(new Business(rs.getInt("businessId"),rs.getString("businessName"),rs.getString("businessAddress"),rs.getString("businessExplain"),rs.getString("businessImg"),rs.getInt("orderTypeId"),rs.getDouble("starPrice"),rs.getDouble("deliveryPrice"),rs.getString("remarks")));

                list.add(cart);
            }
        MySqlUtil.close(rs,pst,con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int addCart(Cart cart) {
        int ret=0;
        try{
            Connection con = MySqlUtil.getConn();
            String sql ="INSERT INTO cart(businessId, userId, foodId)values(?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, String.valueOf(cart.getBusinessId()));
            pst.setString(2, cart.getUserId());
            pst.setString(3, String.valueOf(cart.getFoodId()));
            ret = pst.executeUpdate();

            MySqlUtil.close(pst, con);
            return ret;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateCart(Cart cart) {
        int ret;
        try{
            Connection con = MySqlUtil.getConn();
            String sql = "UPDATE cart SET quantity = ? where businessId = ? and userId = ? and foodId =? and quantity = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,cart.getQuantity());
            pst.setInt(2,cart.getBusinessId());
            pst.setString(3,cart.getUserId());
            pst.setInt(4,cart.getFoodId());
            ret = pst.executeUpdate();

            MySqlUtil.close(pst, con);
            return ret;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int removeCart(Cart cart) {
        int ret;
        try{
            Connection con = MySqlUtil.getConn();
            String sql = "DELETE FROM cart WHERE businessId = ? and userId = ? and foodId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,cart.getBusinessId());
            pst.setString(2, cart.getUserId());
            pst.setInt(3,cart.getFoodId());
            ret = pst.executeUpdate();

            MySqlUtil.close(pst, con);
            return ret;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
