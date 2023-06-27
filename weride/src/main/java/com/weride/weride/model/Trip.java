package com.weride.weride.model;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRIP")
public class Trip {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "driver_id")
    private int driverID;
    
    @ManyToMany(mappedBy = "trips")
    List<Rider> riders;    

    @Column(name = "pay_id")
    private int payID;

    @Column(name = "status")
    private TripStatus status;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "post_date")
    private String postDate;

    protected Trip() {
        // No-argument constructor
    }

    public Trip(int driverID, int payID, TripStatus status, String origin, String destination, String postDate) {
        // Constructor with arguments
        this.riders = new LinkedList<>();
        this.driverID = driverID;
        this.payID = payID;
        this.status = status;
        this.origin = origin;
        this.destination = destination;
        this.postDate = postDate;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int newID) {
        this.id = newID;
    }

    public int getDriverID() {
        return this.driverID;
    }

    public void setDriverID(int newDriverID) {
        this.driverID = newDriverID;
    }

    public List<Rider> getRiders() {
        return this.riders;
    }

    public void addRider(Rider newRider) {
        this.riders.add(newRider);
        newRider.getTrips().add(this);
    }
    
    public void removeRider(Rider rider) {
        this.riders.remove(rider);
        rider.getTrips().remove(this);
    }

    public void updateRiders(List<Rider> newRiders) {
        this.riders.clear();
        if (newRiders != null) {
            for (Rider rider : newRiders) {
                this.addRider(rider);
            }
        }    
    }
    
    public int getPayID() {
        return this.payID;
    }

    public void setPayID(int newPayID) {
        this.payID = newPayID;
    }

    public TripStatus getStatus() {
        return this.status;
    }

    public void setStatus(TripStatus newStatus) {
        this.status = newStatus;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String newOrigin) {
        this.origin = newOrigin;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String newDestination) {
        this.destination = newDestination;
    }

    public String getPostDate() {
        return this.postDate;
    }

    public void setPostDate(String newPostDate) {
        this.postDate = newPostDate;
    }

    public enum TripStatus {
        WAITING,
        ON_TRIP,
        FINISHED,
        CANCELED
    }
}