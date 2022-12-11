package com.jilin.dao.impl;

import com.jilin.dao.DeliveryAddressDao;
import com.jilin.entity.DeliveryAddress;
import com.jilin.utils.MySqlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressDaoImpl implements DeliveryAddressDao {
    @Override
    public int addDeliveryAddress(DeliveryAddress DA) {
        int ret=0;
        try{
            Connection con = MySqlUtil.getConn();
            String sql ="INSERT INTO deliveryaddress values(?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, DA.getUserId());
            pst.setString(2, DA.getContactName());
            pst.setInt(3, DA.getContactSex());
            pst.setString(4, DA.getContactTel());
            pst.setString(5, DA.getAddress());
            ret = pst.executeUpdate();

            MySqlUtil.close(pst, con);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateDeliveryAddress(DeliveryAddress DA) {
        int ret=0;
        try{
            Connection con = MySqlUtil.getConn();
            String sql ="UPDATE deliveryaddress SET contactName = ?, contactSex = ?, contactTel = ?, address = ? where daId=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, DA.getDaId());
            pst.setString(2, DA.getContactName());
            pst.setInt(3, DA.getContactSex());
            pst.setString(4, DA.getContactTel());
            pst.setString(5, DA.getAddress());
            ret = pst.executeUpdate();

            MySqlUtil.close(pst, con);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int removeDeliveryAddress(Integer daId) {
        int ret;
        try{
            Connection con = MySqlUtil.getConn();
            String sql = "DELETE FROM deliveryaddress WHERE daId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, daId);
            ret = pst.executeUpdate();
            MySqlUtil.close(pst, con);
            return ret;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public DeliveryAddress getDeliveryAddressById(Integer daId) {
        try {
            Connection con = MySqlUtil.getConn();
            String sql = "SELECT * FROM deliveryaddress where daId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, daId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DeliveryAddress da = new DeliveryAddress();
                da.setDaId(daId);
                da.setContactName(rs.getString("contactName"));
                da.setContactSex(rs.getInt("contactSex"));
                da.setContactTel(rs.getString("contactTel"));
                da.setAddress(rs.getString("address"));
                da.setUserId(rs.getString("userId"));

                MySqlUtil.close(pst, con);
                return da;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) {
        List<DeliveryAddress> list = new ArrayList<>();
        try{
            Connection con = MySqlUtil.getConn();
            String sql = "SELECT * FROM deliveryaddress WHERE userId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userId);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                DeliveryAddress da = new DeliveryAddress();
                da.setDaId(rs.getInt("daId"));
                da.setContactName(rs.getString("contactName"));
                da.setContactSex(rs.getInt("contactSex"));
                da.setContactTel(rs.getString("contactTel"));
                da.setAddress(rs.getString("address"));
                da.setUserId(rs.getString("userId"));
                list.add(da);
            }
            MySqlUtil.close(pst,con);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
