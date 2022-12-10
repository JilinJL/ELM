package com.jilin.dao;

import com.jilin.entity.DeliveryAddress;

import java.util.List;

public interface DeliveryAddressDao {
    //添加地址
    int addDeliveryAddress(DeliveryAddress DA);
    //修改地址
    int updateDeliveryAddress(DeliveryAddress DA);
    //删除地址
    int removeDeliveryAddress(DeliveryAddress DA);
    //查询某一地址
    DeliveryAddress getDeliveryAddressById(String daId);
    //查询所有地址
    List<DeliveryAddress> listDeliveryAddressByUserId(String userId);
}
