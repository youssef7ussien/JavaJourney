package com.login.springweb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.login.springweb.models.User;
import com.login.springweb.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

}