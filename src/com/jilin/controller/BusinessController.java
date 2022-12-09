package com.jilin.controller;

import com.jilin.entity.Business;
import com.jilin.service.BusinessService;
import com.jilin.service.impl.BusinessServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BusinessController {
    //查询商家
    public List<Business> listBusinessByOrderTypeId(HttpServletRequest request){
        BusinessService BusServ = new BusinessServiceImpl();
        int type = Integer.parseInt(request.getParameter("orderTypeId"));
        List<Business> list = BusServ.listBusiness(type);
        return list;
    }
    //根据id查询商家信息
    public Business getBusinessById(HttpServletRequest request){
        int Id = Integer.parseInt(request.getParameter("businessId"));
        BusinessService BusServ = new BusinessServiceImpl();
        return BusServ.getBusinessById(Id);
    }

}
