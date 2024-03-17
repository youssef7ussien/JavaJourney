package com.h2.example.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.h2.example.exceptions.ControllerException;
import com.h2.example.exceptions.ServiceException;
import com.h2.example.models.Purchase;
import com.h2.example.services.PurchaseService;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /**
     * Save a purchase.
     *
     * @param purchase The purchase object to be saved.
     * @return ResponseEntity with HTTP status and message.
     */
    @PostMapping
    public ResponseEntity<String> savePurchase(@RequestBody Purchase purchase) {
        try {
            purchaseService.savePurchase(purchase);
            return ResponseEntity.status(HttpStatus.CREATED).body("Purchase saved successfully");
        } catch (ServiceException e) {
            throw new ControllerException("Failed to save purchase", e);
        }
    }

    /**
     * Delete a purchase by its id.
     *
     * @param id The id of the purchase to be deleted.
     * @return ResponseEntity with HTTP status and message.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePurchaseById(@PathVariable Long id) {
        try {
            purchaseService.deletePurchaseById(id);
            return ResponseEntity.ok("Purchase deleted successfully");
        } catch (ServiceException e) {
            throw new ControllerException("Failed to delete purchase with id: " + id, e);
        }
    }

    /**
     * Get a purchase by its id.
     *
     * @param id The id of the purchase to retrieve.
     * @return ResponseEntity with HTTP status and the retrieved Purchase object.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Purchase> findPurchaseById(@PathVariable Long id) {
        try {
            Purchase purchase = purchaseService.findPurchaseById(id);
            return ResponseEntity.ok(purchase);
        } catch (ServiceException e) {
            throw new ControllerException("Failed to find purchase with id: " + id, e);
        }
    }

    /**
     * Get all purchases.
     *
     * @return ResponseEntity with HTTP status and a list of Purchase objects.
     */
    @GetMapping
    public ResponseEntity<List<Purchase>> findAllPurchases() {
        try {
            List<Purchase> purchases = purchaseService.findAllPurchases();
            return ResponseEntity.ok(purchases);
        } catch (ServiceException e) {
            throw new ControllerException("Failed to retrieve purchases", e);
        }
    }
}
