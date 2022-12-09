package com.jilin.service;

import com.jilin.entity.Food;

import java.util.List;

public interface FoodService {
    List<Food> listFoodByBusinessId(String businessId);
}
