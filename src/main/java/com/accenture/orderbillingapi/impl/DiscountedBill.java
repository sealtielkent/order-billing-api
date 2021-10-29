package com.accenture.orderbillingapi.impl;

import com.accenture.orderbillingapi.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiscountedBill extends OrderBill {

    public DiscountedBill() {
        //empty constructor
    }

    @Override
    public double getTotalBill() {
        double totalBill = 0.0;
        List<Order> orderList = getOrderList();
        for (Order order : orderList) {
            totalBill = (order.getPrice() - (order.getPrice() *
                    (order.getIsDiscountPercentage()/100))
            ) + totalBill;
        }
        return totalBill;
    }
}
