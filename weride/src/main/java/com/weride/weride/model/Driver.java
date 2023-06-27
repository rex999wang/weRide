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
public class Driver {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "email")
    private String email;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars;
    
    protected Driver() {
        // No-argument constructor
    }

    public Driver(String name, String email, String location) {
        // Constructor with arguments
        this.cars = new LinkedList<>();
        this.name = name;
        this.email = email;
        this.location = location;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int newID) {
        this.id = newID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
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

}

