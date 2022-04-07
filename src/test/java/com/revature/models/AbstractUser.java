package com.revature.models;

import java.util.Objects;

public class AbstractUser {

    private int id;
    private String username;
    private String password;
    private Role role;

    public AbstractUser() {
    }


    public AbstractUser(int id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            AbstractUser that = (AbstractUser)o;
            return this.id == that.id && Objects.equals(this.username, that.username) && Objects.equals(this.password, that.password) && this.role == that.role;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.username, this.password, this.role});
    }


    @Override
    public String toString() {
        return "AbstractUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
