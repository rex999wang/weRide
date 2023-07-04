package com.weride.weride.model;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "DRIVER")
public class Driver extends User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars;
    
    protected Driver() {
        // No-argument constructor
    }

    public Driver(String name, String email, String password, String location) {
        // Constructor with arguments
        super(name, email, password);
        this.cars = new LinkedList<>();
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String newLocation) {
        this.location = newLocation;
    }

    public List<Car> getCars() {
        return this.cars;
    }

    public void addCar(Car newCar) {
        this.cars.add(newCar);
        newCar.setDriver(this);
    }

    public void updateCars(List<Car> carList) {
        this.cars.clear();
        if (carList != null) {
            for (Car car : carList) {
                this.addCar(car);
            }
        }
    }

    public void removeCar(Car car) {
        cars.remove(car);
        car.setDriver(null);
    }

    @Override
    public String toString() {
        return super.getName() + ", " + super.getEmail() + ", " + this.location;
    }
}

