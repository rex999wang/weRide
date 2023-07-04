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
public class Rider extends User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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
        super(name, email, location);
        this.trips = new LinkedList<>();
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

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String newLocation) {
        this.location = newLocation;
    }

    @Override
    public String toString() {
        return super.getName() + ", " + super.getEmail() + ", " + this.location;
    }
}