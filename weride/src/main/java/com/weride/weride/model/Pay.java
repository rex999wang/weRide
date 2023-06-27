package com.weride.weride.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PAY")
public class Pay {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "trip_id")
    private int tripID;
    
    @Column(name = "method")
    private PayMethod method;

    @Column(name = "amount")
    private double amount;

    @Column(name = "pay_time")
    private String payTime;
    
    protected Pay() {
        // No-argument constructor
    }

    public Pay(int tripID, PayMethod method, double amount, String payTime) {
        // Constructor with arguments
        this.tripID = tripID;
        this.method = method;
        this.amount = amount;
        this.payTime = payTime;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int newID) {
        this.id = newID;
    }

    public int getTripID() {
        return this.tripID;
    }

    public void setTripID(int newTripID) {
        this.tripID = newTripID;
    }

    public PayMethod getMethod() {
        return this.method;
    }

    public void setMethod(PayMethod newMethod) {
        this.method = newMethod;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double newAmount) {
        this.amount = newAmount;
    }

    public String getPayTime() {
        return this.payTime;
    }

    public void setPayTime(String newPayTime) {
        this.payTime = newPayTime;
    }

    public enum PayMethod {
        CASH,
        WECHAT,
        ALIPAY,
        PAYPAL,
        CARD
    }
}