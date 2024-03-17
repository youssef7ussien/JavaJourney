package com.example.wallet.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.wallet.dto.UpdateBalanceDTO;
import com.example.wallet.dto.UserDTO;
import com.example.wallet.exceptions.UserNotFoundException;
import com.example.wallet.models.User;
import com.example.wallet.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getBalance());
        user = userRepository.save(user);
        return convertToDTO(user);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID = " + userId));

        return convertToDTO(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username = " + username));

        return convertToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> usersDTO = new ArrayList<>();

        userRepository.findAll().forEach(user -> {
            usersDTO.add(convertToDTO(user));
        });

        return usersDTO;
    }

    @Override
    public UserDTO updateUserBalance(UpdateBalanceDTO updateBalanceDTO) {
        User user = userRepository.findById(updateBalanceDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found with id = " + updateBalanceDTO.getUserId()));

        user.setBalance(updateBalanceDTO.getNewBalance());
        userRepository.save(user);

        return convertToDTO(user);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setBalance(user.getBalance());
        return dto;
    }

}