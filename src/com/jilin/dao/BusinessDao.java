package com.jilin.dao;

import com.jilin.entity.Business;

import java.util.List;

public interface BusinessDao {

    //查询商家列表
    List<Business> listBusiness(Integer orderTypeId);

    //根据id查询商家信息
    Business getBusinessById(String businessId);
}
