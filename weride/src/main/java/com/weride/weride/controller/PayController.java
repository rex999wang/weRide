package com.weride.weride.controller;

import org.springframework.web.bind.annotation.*;

import com.weride.weride.model.Pay;
import com.weride.weride.repository.PayRepository;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/pays")
public class PayController {

    private final PayRepository payRepository;

    public PayController(PayRepository payRepository) {
        this.payRepository = payRepository;
    }

    @GetMapping("/")
    public List<Pay> getAllPays() {
        return payRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pay> getPayById(@PathVariable Integer id) {
        return payRepository.findById(id)
                .map(pay -> ResponseEntity.ok().body(pay))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Pay createPay(@RequestBody Pay pay) {
        return payRepository.save(pay);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pay> updatePay(@PathVariable Integer id, @RequestBody Pay updatedPay) {
        return payRepository.findById(id)
                .map(pay -> {
                    pay.setTripID(updatedPay.getTripID());
                    pay.setMethod(updatedPay.getMethod());
                    pay.setAmount(updatedPay.getAmount());
                    pay.setPayTime(updatedPay.getPayTime());
                    // Update other fields as needed
                    Pay savedPay = payRepository.save(pay);
                    return ResponseEntity.ok().body(savedPay);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePay(@PathVariable Integer id) {
        return payRepository.findById(id)
                .map(pay -> {
                    payRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
