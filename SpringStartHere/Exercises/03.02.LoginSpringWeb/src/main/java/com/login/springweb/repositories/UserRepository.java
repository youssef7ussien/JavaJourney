package com.login.springweb.repositories;

import java.util.List;
import java.util.Optional;

import com.login.springweb.models.User;

public interface UserRepository {

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return An {@code Optional} containing the user if found; otherwise, an empty
     *         {@code Optional}.
     * 
     *         <p>
     *         The use of {@code Optional} indicates potential absence, promoting
     *         explicit handling and preventing null pointer exceptions.
     *         </p>
     */
    Optional<User> findByUsername(String username);

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    List<User> getAll();

    /**
     * Saves a user.
     *
     * @param user The user to save.
     */
    void save(User user);
}