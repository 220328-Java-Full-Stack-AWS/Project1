package com.revature.repositories;

import com.revature.models.User;

import java.util.Optional;

public class UserDAO {
    public UserDAO() {
    }

    public Optional<User> getByUsername(String username) {
        return Optional.empty();
    }

    public User create(User userToBeRegistered) {
        return userToBeRegistered;
    }
}
