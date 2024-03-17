package com.example.wallet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.wallet.models.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    /**
     * Finds all transactions associated with a user.
     *
     * @param userId the ID of the user
     * @return a list of transactions associated with the user
     */
    @Query("SELECT * FROM transactions WHERE sender_id = :userId OR receiver_id = :userId")
    Optional<List<Transaction>> findAllByUserId(Long userId);

    /**
     * Deletes a transaction by its ID.
     *
     * @param id the ID of the transaction to delete.
     */
    @Override
    void deleteById(Long id);
}
