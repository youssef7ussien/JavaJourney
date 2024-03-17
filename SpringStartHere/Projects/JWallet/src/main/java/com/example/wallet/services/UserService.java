package com.example.wallet.services;

import java.util.List;

import com.example.wallet.dto.UpdateBalanceDTO;
import com.example.wallet.dto.UserDTO;
import com.example.wallet.exceptions.UserNotFoundException;

public interface UserService {

    /**
     * Creates a new user.
     *
     * @param userDTO The user data to create.
     * @return The created user.
     */
    UserDTO createUser(UserDTO userDTO);

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The user with the given ID.
     * @throws UserNotFoundException if no user found with the given ID.
     */
    UserDTO getUserById(Long userId);

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The user with the given username.
     * @throws UserNotFoundException if no user found with the given username.
     */
    UserDTO getUserByUsername(String username);

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    List<UserDTO> getAllUsers();

    /**
     * Updates the balance of a user.
     *
     * @param updateBalanceDTO The DTO containing the user ID and the new balance.
     * @return The updated user DTO.
     * @throws UserNotFoundException if no user found with the given ID.
     */
    UserDTO updateUserBalance(UpdateBalanceDTO updateBalanceDTO);
}
