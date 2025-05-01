package com.example.coffeeorder;

import java.util.Date;

public class OrderItem {
    private Coffee coffee;
    private int quantity;
    private Date orderDate;
    private String address;
    private String details;

    public OrderItem(Coffee coffee, int quantity, Date orderDate, String address, String details) {
        this.coffee = coffee;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.address = address;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
