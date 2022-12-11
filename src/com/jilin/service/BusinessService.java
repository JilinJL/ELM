package com.jilin.service;

import com.jilin.entity.Business;

import java.util.List;

public interface BusinessService {

    //根据id查询商家信息
    List<Business> listBusiness(Integer type);

    //查询商家
    Business getBusinessById(String id);
}
