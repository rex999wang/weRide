package com.weride.weride.model;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "RIDER")
public class Rider {

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

    @ManyToMany
    @JoinTable(
        name = "rider_trips", 
        joinColumns = @JoinColumn(name = "rider_id"), 
        inverseJoinColumns = @JoinColumn(name = "trip_id"))
    List<Trip> trips;

    protected Rider() {
        // No-argument constructor
    }

    public Rider(String name, String email, String location) {
        // Constructor with arguments
        this.trips = new LinkedList<>();
        this.name = name;
        this.email = email;
        this.location = location;
    }

    public List<Trip> getTrips() {
        return this.trips;
    }

    public void addTrip(Trip newTrip) {
        this.trips.add(newTrip);
        newTrip.getRiders().add(this);
    }
    
    public void removeTrip(Trip trip) {
        this.trips.remove(trip);
        trip.getRiders().remove(this);
    }

    public void updateTrips(List<Trip> newTrips) {
        this.trips.clear();
        if (newTrips != null) {
            for (Trip newTrip : newTrips) {
                this.addTrip(newTrip);
            }
        }    
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

    @Override
    public String toString() {
        return this.name + ", " + this.email + ", " + this.location;
    }
}