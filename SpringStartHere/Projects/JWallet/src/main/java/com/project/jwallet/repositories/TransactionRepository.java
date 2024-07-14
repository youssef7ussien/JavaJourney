package com.project.jwallet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.jwallet.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Finds all transactions associated with a user.
     *
     * @param userId the ID of the user
     * @return a list of transactions associated with the user
     */
    @Query("SELECT t FROM Transaction t WHERE t.senderId = :userId OR t.receiverId = :userId")
    Optional<List<Transaction>> findAllByUserId(Long userId);

    /**
     * Deletes a transaction by its ID.
     *
     * @param id the ID of the transaction to delete.
     */
    @Override
    void deleteById(Long id);
}
