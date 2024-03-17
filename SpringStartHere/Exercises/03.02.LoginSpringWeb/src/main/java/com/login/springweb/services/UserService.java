package com.login.springweb.services;

import java.util.List;
import java.util.Optional;

import com.login.springweb.models.User;

public interface UserService {
    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return An {@code Optional} containing the user if found; otherwise, an empty
     *         {@code Optional}.
     */
    public Optional<User> getUserByUsername(String username);

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    public List<User> getAllUsers();

    /**
     * Saves a user.
     *
     * @param user The user to save.
     */
    public void saveUser(User user);

}
