package com.h2.example.repositories;

import java.util.List;

import com.h2.example.exceptions.RepositoryException;
import com.h2.example.models.Purchase;

public interface PurchaseRepository {

    /**
     * Saves a purchase to the database.
     *
     * @param purchase The purchase object to be saved.
     * @throws RepositoryException If an error occurs while saving the purchase.
     */
    void save(Purchase purchase) throws RepositoryException;

    /**
     * Deletes a purchase from the database by its id.
     *
     * @param id The id of the purchase to be deleted.
     * @throws RepositoryException If an error occurs while deleting the purchase.
     */
    void deleteById(Long id) throws RepositoryException;

    /**
     * Finds a purchase in the database by its id.
     *
     * @param id The id of the purchase to find.
     * @return The found Purchase object, or null if not found.
     * @throws RepositoryException If an error occurs while finding the purchase.
     */
    Purchase findById(Long id) throws RepositoryException;

    /**
     * Retrieves all purchases from the database.
     *
     * @return A list of Purchase objects representing all purchases in the
     *         database.
     * @throws RepositoryException If an error occurs while retrieving purchases.
     */
    List<Purchase> findAll() throws RepositoryException;

}