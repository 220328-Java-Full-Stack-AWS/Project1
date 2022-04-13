package com.revature.repositories;

import com.revature.models.User;

import java.util.List;

public interface TempUserDB {

    void saveUser(User u);

    List<User> getAllUsers();

    User getByUsername(String username);

    void updateUser(User u);
}
