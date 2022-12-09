package com.jilin.service.impl;

import com.jilin.dao.FoodDao;
import com.jilin.dao.impl.FoodDaoImpl;
import com.jilin.entity.Food;
import com.jilin.service.FoodService;

import java.util.List;

public class FoodServiceImpl implements FoodService {
    @Override
    public List<Food> listFoodByBusinessId(String businessId) {
        FoodDao dao = new FoodDaoImpl();
        return dao.listFoodByBusinessId(businessId);
    }
}
