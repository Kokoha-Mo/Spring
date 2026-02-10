package com.example.spring1.dto;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private String orderDate;
    private List<OrderDetail> odetails = new ArrayList<>();

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<OrderDetail> getOdetails() {
        return odetails;
    }

    public void setOdetails(List<OrderDetail> odetails) {
        this.odetails = odetails;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

}
