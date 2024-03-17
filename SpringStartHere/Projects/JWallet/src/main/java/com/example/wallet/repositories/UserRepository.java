package com.example.wallet.repositories;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.wallet.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Finds a user by their username.
     * 
     * @param username the username of the user to find
     * @return the user if found, null otherwise
     */
    @Query("SELECT * FROM users WHERE username = :username")
    Optional<User> findByUsername(String username);

    /**
     * Updates the balance of a user.
     * 
     * @param userId     the ID of the user
     * @param newBalance the new balance to set
     */
    @Modifying
    @Transactional
    @Query("UPDATE users SET balance = :newBalance WHERE id = :userId")
    void updateBalance(Long userId, BigDecimal newBalance);
}
