package com.revature.repositories;

import com.revature.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoMockImp implements TempUserDB {

    private MockUserDB db = MockUserDB.getInstance();

    @Override
    public void saveUser(User u) {
        db.getUdb().put(u.getUsername(), u);
    }

    public List<User> getAllUsers() {
        return new ArrayList<User>(db.getUdb().values());
    }

    public User getByUsername(String username) {
        return db.getUdb().get(username);
    }

    public void updateUser(User u) {
        db.getUdb().put(u.getUsername(), u);
    }
}
