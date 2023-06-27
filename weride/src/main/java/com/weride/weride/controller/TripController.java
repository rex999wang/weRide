package com.weride.weride.controller;

import org.springframework.web.bind.annotation.*;

import com.weride.weride.model.Trip;
import com.weride.weride.repository.TripRepository;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripRepository tripRepository;

    public TripController(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @GetMapping("/")
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Integer id) {
        return tripRepository.findById(id)
                .map(trip -> ResponseEntity.ok().body(trip))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Trip createTrip(@RequestBody Trip trip) {
        return tripRepository.save(trip);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Integer id, @RequestBody Trip updatedTrip) {
        return tripRepository.findById(id)
                .map(trip -> {
                    trip.setDriverID(updatedTrip.getDriverID());
                    trip.updateRiders(updatedTrip.getRiders());
                    trip.setPayID(updatedTrip.getPayID());
                    trip.setStatus(updatedTrip.getStatus());
                    trip.setOrigin(updatedTrip.getOrigin());
                    trip.setDestination(updatedTrip.getDestination());
                    trip.setPostDate(updatedTrip.getPostDate());
                    // Update other fields as needed
                    Trip savedTrip = tripRepository.save(trip);
                    return ResponseEntity.ok().body(savedTrip);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable Integer id) {
        return tripRepository.findById(id)
                .map(trip -> {
                    tripRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
