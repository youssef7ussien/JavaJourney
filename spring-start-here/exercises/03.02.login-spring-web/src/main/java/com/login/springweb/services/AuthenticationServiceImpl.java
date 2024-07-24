package com.login.springweb.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.login.springweb.models.User;

@Service
@RequestScope
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final SessionService sessionService;

    public AuthenticationServiceImpl(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @Override
    public boolean authenticate(String username, String password) {
        Optional<User> userOptional = userService.getUserByUsername(username);

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            sessionService.setCurrentUser(userOptional.get());
            return true;
        }

        return false;
    }

    @Override
    public void logout() {
        sessionService.clearCurrentUser();
    }

}
