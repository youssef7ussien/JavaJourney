package com.login.springweb.services;

public interface OnlineUserService {

    /**
     * Handles the login of a user, incrementing the count of online users.
     */
    void userLoggedIn();

    /**
     * Handles the logout of a user, decrementing the count of online users.
     */
    void userLoggedOut();

    /**
     * Retrieves the current number of online users.
     *
     * @return The current number of online users.
     */
    int currentOnlineUsers();
}
