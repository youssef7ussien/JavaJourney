package com.login.springweb.services;

public interface AuthenticationService {

    /**
     * Authenticates a user with the given username and password.
     *
     * @param username The username to authenticate.
     * @param password The password to authenticate.
     * @return True if the authentication is successful; otherwise, false.
     */
    public boolean authenticate(String username, String password);

    /**
     * Logs out the current user.
     */
    void logout();
}
