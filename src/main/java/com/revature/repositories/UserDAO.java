package com.revature.repositories;

import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
//    public Optional<User> getByUsername(String username) {
//        User user = new User();
//        Optional<User> result = Optional.empty();
//        try {
//            String sql =  "SELECT * FROM ers_users WHERE ers_username = ?";
//            Connection conn = ConnectionFactory.getConnection();  // get the connection
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, username);
//            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL
//
//            while(rs.next()){
//                user.setId(rs.getInt("ers_users_id"));
//                user.setUsername(rs.getString("ers_username"));
//                user.setPassword(rs.getString("ers_password"));
//                user.setFirst_name(rs.getString("user_first_name"));
//                user.setLast_name(rs.getString("user_last_name"));
//                user.setEmail(rs.getString("user_email"));
//                user.setPhone(rs.getString("user_phone"));
//                int roleId = rs.getInt("user_role_id");
//                if(roleId == 1){
//                    user.setRole(Role.EMPLOYEE);
//                }else if (roleId == 2){
//                    user.setRole(Role.FINANCE_MANAGER);
//                }
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        result = Optional.ofNullable(user);
//        return result;
//    }

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     */
    public User create(User userToBeRegistered) {
        String sql = "INSERT INTO ers_users (ers_username,ers_password,user_first_name,user_last_name,user_email,user_phone,user_role_id) VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setString(1, userToBeRegistered.getUsername());
            pstmt.setString(2,userToBeRegistered.getPassword());
            pstmt.setString(3, userToBeRegistered.getFirst_name());
            pstmt.setString(4,userToBeRegistered.getLast_name());
            pstmt.setString(5, userToBeRegistered.getEmail());
            pstmt.setString(6, userToBeRegistered.getPhone());

            String role = String.valueOf(userToBeRegistered.getRole());
            if(role.equals("Employee")){
                pstmt.setInt(7, 1);
            }{
                pstmt.setInt(7, 2);
            }

            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()){
                int key = keys.getInt(1);
                userToBeRegistered.setId(key);
            }

        }catch(SQLException e){
            System.out.println("Error adding user " + e.getMessage() + " " + e.getErrorCode());
            throw new RegistrationUnsuccessfulException("Registration Failed");
        }
        return userToBeRegistered;
    }

    /**
     * This method returns the User connected with the id specified.
     * @param id - the id in relation to the user's id in the database (ers_users_id).
     * @return The User with the ers_users_id the method takes in.
     */
    public User read(int id){
        User user = new User();
        try {
            String sql = "SELECT * FROM ers_users WHERE ers_users_id = ?";
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL
            while(rs.next()){
                user.setId(rs.getInt("ers_users_id"));
                user.setUsername(rs.getString("ers_username"));
                user.setPassword(rs.getString("ers_password"));
                user.setFirst_name(rs.getString("user_first_name"));
                user.setLast_name(rs.getString("user_last_name"));
                user.setEmail(rs.getString("user_email"));
                user.setPhone(rs.getString("user_phone"));

                int userRoleId = rs.getInt("user_role_id");
                if(userRoleId == 1){
                    user.setRole(Role.EMPLOYEE);
                }else{
                    user.setRole(Role.FINANCE_MANAGER);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * This method returns the User connected with the username specified.
     * @param username - search for a user by their username;
     * @return The user with the username specified.
     */
    public User read(String username){
        User user = new User();
        try {
            String sql = "SELECT * FROM ers_users WHERE user_email = ?";
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL
            while(rs.next()){
                user.setId(rs.getInt("ers_users_id"));
                user.setUsername(rs.getString("ers_username"));
                user.setPassword(rs.getString("ers_password"));
                user.setFirst_name(rs.getString("user_first_name"));
                user.setLast_name(rs.getString("user_last_name"));
                user.setEmail(rs.getString("user_email"));
                user.setPhone(rs.getString("user_phone"));

                int userRoleId = rs.getInt("user_role_id");
                if(userRoleId == 1){
                    user.setRole(Role.EMPLOYEE);
                }else{
                    user.setRole(Role.FINANCE_MANAGER);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * This method returns the User connected with the email specified.
     * @param email - search for a user by their email;
     * @return The user with the email specified.
     */
    public User readByEmail(String email){
        User user = new User();
        try {
            String sql = "SELECT * FROM ers_users WHERE user_email = ?";
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL
            while(rs.next()){
                user.setId(rs.getInt("ers_users_id"));
                user.setUsername(rs.getString("ers_username"));
                user.setPassword(rs.getString("ers_password"));
                user.setFirst_name(rs.getString("user_first_name"));
                user.setLast_name(rs.getString("user_last_name"));
                user.setEmail(rs.getString("user_email"));
                user.setPhone(rs.getString("user_phone"));

                int userRoleId = rs.getInt("user_role_id");
                if(userRoleId == 1){
                    user.setRole(Role.EMPLOYEE);
                }else{
                    user.setRole(Role.FINANCE_MANAGER);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Updates the username
     * @param user
     */
    public void updateUsername(User user){
        String sql = "UPDATE ers_users SET ers_username = ? WHERE id = ?";
        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setInt(2, user.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the password
     * @param user
     */
    public void updatePassword(User user){
        String sql = "UPDATE ers_users SET ers_password = ? WHERE id = ?";
        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getPassword());
            pstmt.setInt(2, user.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Updates the userRole
     * @param user
     */
    public void updateUserRole(User user){
        String sql = "UPDATE ers_users SET user_role_id = ? WHERE id = ?";
        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            String role = String.valueOf(user.getRole());
            if(role == "Employee"){
                pstmt.setInt(1, 1);
            }else if (role == "Financial Manager"){
                pstmt.setInt(1, 2);
            }
            pstmt.setInt(2, user.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This deletes a single row from the database based on id
     * @param id The ID to identify the row we wish to delete
     */
    public void deleteUser(int id){
        String sql = "DELETE FROM ers_users WHERE ers_users_id = ?";
        try{
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This deletes a single row from the database based on id included in model object
     * @param user The model containing the ID to identify the row we wish to delete
     */
    public void deleteUser(User user){
        String sql = "DELETE FROM ers_users WHERE ers_users_id = ?";
        try{
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            pstmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public User getUser(String email){
        User user = new User();
        String sql = "SELECT * FROM ers_users WHERE user_email = ?";
        try {
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            while(rs.next()){
                user.setId(rs.getInt("ers_users_id"));
                user.setUsername(rs.getString("ers_username"));
                user.setPassword(rs.getString("ers_password"));
                user.setFirst_name(rs.getString("user_first_name"));
                user.setLast_name(rs.getString("user_last_name"));
                user.setEmail(rs.getString("user_email"));
                user.setPhone(rs.getString("user_phone"));
                int roleId = rs.getInt("user_role_id");
                if(roleId == 1){
                    user.setRole(Role.EMPLOYEE);
                }else if (roleId == 2){
                    user.setRole(Role.FINANCE_MANAGER);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<String> getAllUsernames(){
        List<String> Usernames = new ArrayList<String>();
        String sql = "SELECT ers_username FROM ers_users";

        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL
            while(rs.next()){
                Usernames.add(rs.getString("ers_username"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return Usernames;
    }

    public List<String> getAllEmails(){
        List<String> Emails = new ArrayList<String>();
        String sql = "SELECT user_email FROM ers_users";

        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL
            while(rs.next()){
                Emails.add(rs.getString("user_email"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return Emails;
    }

}
