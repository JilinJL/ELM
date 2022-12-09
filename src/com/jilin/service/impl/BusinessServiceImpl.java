package com.jilin.service.impl;

import com.jilin.dao.BusinessDao;
import com.jilin.dao.impl.BusinessDaoImpl;
import com.jilin.entity.Business;
import com.jilin.service.BusinessService;

import java.util.List;

public class BusinessServiceImpl implements BusinessService {

    @Override
    public List<Business> listBusiness(Integer type) {
        BusinessDao dao = new BusinessDaoImpl();
        return dao.listBusiness(type);
    }

    @Override
    public Business getBusinessById(Integer id) {
        BusinessDao dao = new BusinessDaoImpl();
        return dao.getBusinessById(id);
    }
}
