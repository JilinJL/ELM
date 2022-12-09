package com.jilin.entity;

import java.util.List;

public class Orders {
    private Integer orderId;
    private String userId;
    private Integer businessId;
    private String orderDate;
    private Double orderTotal;
    private Integer daId;
    private Integer orderState;

    //多对一
    private Business business;
    //一对多
    private List<OrderDetailet> list;
}

