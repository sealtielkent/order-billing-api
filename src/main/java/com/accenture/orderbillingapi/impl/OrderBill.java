package com.accenture.orderbillingapi.impl;

import com.accenture.orderbillingapi.entity.Order;

import java.util.List;

public abstract class OrderBill {

    private List<Order> orderList;

    public OrderBill() {
    }


    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public abstract double getTotalBill();
}
