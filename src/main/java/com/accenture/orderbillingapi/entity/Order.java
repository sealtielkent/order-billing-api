package com.accenture.orderbillingapi.entity;

import javax.persistence.*;

@Entity
@Table(name="`order`")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private long id;

    private String orderName;

    private double price;

    private double isDiscountPercentage;

    public Order(){}

    public Order(String orderName, double price, double isDiscountPercentage) {
        this.orderName = orderName;
        this.price = price;
        this.isDiscountPercentage = isDiscountPercentage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getIsDiscountPercentage() {
        return isDiscountPercentage;
    }

    public void setIsDiscountPercentage(double isDiscountPercentage) {
        this.isDiscountPercentage = isDiscountPercentage;
    }
}
