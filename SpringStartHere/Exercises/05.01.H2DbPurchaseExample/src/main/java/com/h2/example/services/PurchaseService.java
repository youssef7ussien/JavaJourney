package com.h2.example.services;

import java.util.List;

import com.h2.example.exceptions.ServiceException;
import com.h2.example.models.Purchase;

public interface PurchaseService {

    /**
     * Saves a purchase.
     *
     * @param purchase The purchase object to be saved.
     * @throws ServiceException If an error occurs while saving the purchase.
     */
    void savePurchase(Purchase purchase) throws ServiceException;

    /**
     * Deletes a purchase by its id.
     *
     * @param id The id of the purchase to be deleted.
     * @throws ServiceException If an error occurs while deleting the purchase.
     */
    void deletePurchaseById(Long id) throws ServiceException;

    /**
     * Finds a purchase by its id.
     *
     * @param id The id of the purchase to find.
     * @return The found Purchase object, or null if not found.
     * @throws ServiceException If an error occurs while finding the purchase.
     */
    Purchase findPurchaseById(Long id) throws ServiceException;

    /**
     * Retrieves all purchases.
     *
     * @return A list of Purchase objects representing all purchases.
     * @throws ServiceException If an error occurs while retrieving purchases.
     */
    List<Purchase> findAllPurchases() throws ServiceException;

}
