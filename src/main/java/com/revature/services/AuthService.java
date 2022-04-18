package com.revature.services;

import com.revature.exceptions.LoginUnsuccessfulException;
import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * The AuthService should handle login and registration for the ERS application.
 *
 * {@code login} and {@code register} are the minimum methods required; however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Retrieve Currently Logged-in User</li>
 *     <li>Change Password</li>
 *     <li>Logout</li>
 * </ul>
 */
public class AuthService {

    UserDAO dao = new UserDAO();

    public User login(String username, String password) {
        User user = dao.getUserByEmail(username);
        User invalid = new User();
        if(user.getId() == 0) {
            System.out.println("Email not found");
            throw new LoginUnsuccessfulException("Email was not found");
        }else if(!(user.getPassword().equals(password))){
            System.out.println("Wrong password");
            throw new LoginUnsuccessfulException("Wrong Password");
        }
        else {
            System.out.println("Login Successful");
            return user;
        }
    }
    public User register(User user) {
        // Get All Usernames
        List<String> Usernames = new ArrayList<String>();
        Usernames = dao.getAllUsernames();
        // Get All Emails
        List<String> Emails = new ArrayList<String>();
        Emails = dao.getAllEmails();
        // User Input
        String username = user.getUsername();
        String email = user.getEmail();
        Boolean Successful = true;

        if(Usernames.contains(username)){ // Test if username already taken
            System.out.println("Username already taken");
            Successful = false;
            throw new UsernameNotUniqueException("Username already in use");
        }else if(Emails.contains(email)){
            User userWithEmail = dao.getUserByEmail(email);
            System.out.println("Email is linked with Username: " + userWithEmail.getUsername());
            Successful = false;
        }else{
            Successful = true;
        }

        if(Successful){
            System.out.println("Registration in progress...");
            dao.create(user);
            System.out.println("Registration Successful");
            return user;
        }else{
            System.out.println("Registration Failed.");
            return null;
        }
    }

    /**
     * This is an example method signature for retrieving the currently logged-in user.
     * It leverages the Optional type which is a useful interface to handle the
     * possibility of a user being unavailable.
     */
    public boolean retrieveCurrentUser(User user) {
        User temp = login(user.getEmail(), user.getPassword());
        if(temp != null){
            return true;
        }else{
            return false;
        }
    }
}
