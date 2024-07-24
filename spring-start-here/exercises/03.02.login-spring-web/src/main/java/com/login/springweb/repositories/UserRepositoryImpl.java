package com.login.springweb.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

import com.login.springweb.models.User;

@Repository
@ApplicationScope
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        this.users.put(user.getUsername(), user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }

    @Override
    public List<User> getAll() {
        /**
         * I included `new ArrayList<>(users)` instead of directly returning the `users`
         * list to ensure that the returned list is a copy of the original list. This is
         * done to prevent external modifications to the returned list from affecting
         * the internal state of the repository.
         */
        return new ArrayList<>(users.values());
    }
}
