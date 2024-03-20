package com.example.wallet.repositories;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.wallet.models.User;


public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their username.
     * 
     * @param username the username of the user to find
     * @return the user if found, null otherwise
     */
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(String username);

    /**
     * Updates the balance of a user.
     * 
     * @param userId     the ID of the user
     * @param newBalance the new balance to set
     */
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.balance = :newBalance WHERE u.id = :userId")
    void updateBalance(Long userId, BigDecimal newBalance);
}
