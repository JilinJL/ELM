package com.jilin.dao;

import com.jilin.entity.Food;

import java.util.List;

public interface FoodDao {
    List<Food> listFoodByBusinessId(String businessId);
}
