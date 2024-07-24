package com.login.springweb.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.login.springweb.models.User;

@Service
@SessionScope
public class SessionServiceImpl implements SessionService {
    private User user;

    @Override
    public User getCurrentUser() {
        return user;
    }

    @Override
    public void setCurrentUser(User user) {
        this.user = user;
    }

    @Override
    public void clearCurrentUser() {
        this.user = null;
    }

    @Override
    public boolean isUserLoggedIn() {
        return this.user != null;
    }
    
}
