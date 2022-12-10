package com.jilin.controller;

import com.jilin.entity.DeliveryAddress;
import com.jilin.service.DeliveryAddressService;
import com.jilin.service.impl.DeliveryAddressServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeliveryAddressController {

    //添加地址
    public int saveDeliveryAddress(HttpServletRequest request){
        DeliveryAddress address = new DeliveryAddress();
        address.setUserId(request.getParameter("userId"));
        address.setContactName(request.getParameter("contactName"));
        address.setContactSex(Integer.valueOf(request.getParameter("contactSex")));
        address.setContactTel(request.getParameter("contactTel"));
        address.setAddress(request.getParameter("address"));
        DeliveryAddressService DAserv = new DeliveryAddressServiceImpl();
        return DAserv.addDeliveryAddress(address);
    }
    //修改地址
    public int updateDeliveryAddress(HttpServletRequest request){
        DeliveryAddress address = new DeliveryAddress();
        address.setDaId(Integer.valueOf(request.getParameter("daId")));
        address.setContactName(request.getParameter("contactName"));
        address.setContactSex(Integer.valueOf(request.getParameter("contactSex")));
        address.setContactTel(request.getParameter("contactTel"));
        address.setAddress(request.getParameter("address"));
        DeliveryAddressService DAserv = new DeliveryAddressServiceImpl();
        return DAserv.addDeliveryAddress(address);
    }
    //删除地址
    public int removeDeliveryAddress(HttpServletRequest request){
        return 0;
    }
    //查询某一地址
    public DeliveryAddress getDeliveryAddressById(HttpServletRequest request){
        return null;
    }
    //查询所有地址
    public List<DeliveryAddress> listDeliveryAddressByUserId(HttpServletRequest request){
        return null;
    }
}
