package com.revature.models;

public class User extends AbstractUser{

    public User() {
    }

    public User(int id, String username, String password, Role role) {
        super(id, username, password, role);
    }
}
