package com.login.springweb.services;

import com.login.springweb.models.User;

public interface SessionService {

    /**
     * 
     * @return True if The current user logged in; otherwise, false.
     */
    public boolean isUserLoggedIn();

    /**
     * Retrieves the current user data from the memory.
     *
     * @return The current user if present; otherwise, null.
     */
    public User getCurrentUser();

    /**
     * Sets the user data in the memory.
     *
     * @param currentUser The current user to set.
     */
    public void setCurrentUser(User user);

    /**
     * Clears the current user data.
     */
    void clearCurrentUser();
}
