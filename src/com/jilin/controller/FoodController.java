package com.jilin.controller;

import com.jilin.entity.Food;
import com.jilin.service.FoodService;
import com.jilin.service.impl.FoodServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FoodController {
    public List<Food> listFoodByBusinessId(HttpServletRequest request){
        String businessId = request.getParameter("businessId");
        FoodService service = new FoodServiceImpl();
        return service.listFoodByBusinessId(businessId);
    }
}
