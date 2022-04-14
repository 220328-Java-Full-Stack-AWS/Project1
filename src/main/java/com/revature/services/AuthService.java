package com.revature.services;

import com.revature.exceptions.LoginUnsuccessfulException;
import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    /**
     * <ul>
     *     <li>Needs to check for existing users with username/email provided.</li>
     *     <li>Must throw exception if user does not exist.</li>
     *     <li>Must compare password provided and stored password for that user.</li>
     *     <li>Should throw exception if the passwords do not match.</li>
     *     <li>Must return user object if the user logs in successfully.</li>
     * </ul>
     */
    public User login(String username, String password) {
        User user = dao.getUser(username);
        User invalid = new User();
        if(user.getId() == 0) {
            System.out.println("Email not found");
            return invalid;
        }else if(!(user.getPassword().equals(password))){
            System.out.println("Wrong password");
            return invalid;
        }
        else {
            System.out.println("Login Successful");
            return user;
        }
    }

    /**
     * <ul>
     *     <li>Should ensure that the username/email provided is unique.</li>
     *     <li>Must throw exception if the username/email is not unique.</li>
     *     <li>Should persist the user object upon successful registration.</li>
     *     <li>Must throw exception if registration is unsuccessful.</li>
     *     <li>Must return user object if the user registers successfully.</li>
     *     <li>Must throw exception if provided user has a non-zero ID</li>
     * </ul>
     *
     * Note: userToBeRegistered will have an id=0, additional fields may be null.
     * After registration, the id will be a positive integer.
     */
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
        }else if(Emails.contains(email)){
            User userWithEmail = dao.readByEmail(email);
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
