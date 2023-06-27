package com.weride.weride.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CAR")
public class Car{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;
    
    @Column(name = "make")
    private String make;

    @Column(name = "type")
    private String type;

    @Column(name = "color")
    private String color;

    @Column(name = "plate_no")
    private String plateNo;

    protected Car() {
        // No-argument constructor
    }

    public Car(Driver driver, String make, String type, String color, String plateNo) {
        // Constructor with arguments
        super();
        this.driver = driver;
        this.make = make;
        this.type = type;
        this.color = color;
        this.plateNo = plateNo;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int newID) {
        this.id = newID;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(Driver newDriver) {
        this.driver = newDriver;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String newMake) {
        this.make = newMake;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String newType) {
        this.type = newType;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String newColor) {
        this.color = newColor;
    }

    public String getPlateNo() {
        return this.plateNo;
    }

    public void setPlateNo(String newPlateNo) {
        this.plateNo = newPlateNo;
    }    
}