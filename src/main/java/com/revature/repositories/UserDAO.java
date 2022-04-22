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

public class UserDAO {

    // Ordered the page in the CRUD Order

    // CREATE
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

//            String role = String.valueOf(userToBeRegistered.getRole());
//            if(role.equals("Employee")){
//                pstmt.setInt(7, 1);
//            }else{
//                pstmt.setInt(7, 2);
//            }
            pstmt.setInt(7, 1); // set all new users to non-manager

            pstmt.executeUpdate();

            // get the reimb_id from the database
            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()) {
                int key = keys.getInt(1);
                userToBeRegistered.setId(key);
            }

        }catch(SQLException e){
            System.out.println("Error adding user " + e.getMessage() + " " + e.getErrorCode());
            throw new RegistrationUnsuccessfulException("Registration Failed");
        }
        return userToBeRegistered;
    }

    // READ
    /**
     * Returns a user by the specified email.
     * @param email - the user's email.
     * @return The user linked with the email.
     */
    public User getUserByEmail(String email){
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
    /**
     * Returns a user by the specified username.
     * @param username - the user's username.
     * @return The user linked with the username.
     */
    public User getByUsername(String username){
        User user = new User();
        String sql = "SELECT * FROM ers_users WHERE ers_username = ?";
        try {
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
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
                int roleId = rs.getInt("user_role_id");
                if(roleId == 1){
                    user.setRole(Role.EMPLOYEE);
                }else if (roleId == 2){
                    user.setRole(Role.FINANCE_MANAGER);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    /**
     * Returns a user by the specified id.
     * @param id - the user's ers_users_id.
     * @return The user linked with the id.
     */
    public User getById(int id){
        User user = new User();
        String sql = "SELECT * FROM ers_users WHERE ers_users_id = ?";
        try {
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
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
                int roleId = rs.getInt("user_role_id");
                if(roleId == 1){
                    user.setRole(Role.EMPLOYEE);
                }else if (roleId == 2){
                    user.setRole(Role.FINANCE_MANAGER);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Gets all usernames in the database, this is to ensure to make a quick comparison between
     * all usernames and the username entered during registration. This ensures we can have
     * unique usernames.
     * @return List of all Usernames.
     */
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

    /**
     * Gets all emails in the database, this is to ensure we have one email link to one username.
     * This list of email's is used to compare the email entered in registration and all emails and
     * tells the user that the email is in use with a username.
     * @return List of all Emails.
     */
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

    public List<User> getAllUsers(){
        List<User> AllUsers = new LinkedList<>();
        String sql = "SELECT * FROM ers_users";
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL
            while(rs.next()){
                User user = new User();
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

                AllUsers.add(user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return AllUsers;
    }

    /**
     * Updates all user's info
     * @param user - The user that is going to be updated.
     */
    public void update(User user){
        String sql = "UPDATE ers_users SET ers_username = ?, user_first_name = ?, user_last_name = ?, ers_password = ?, user_email = ?, user_phone = ? WHERE ers_users_id = ?";
        try{
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getFirst_name());
            pstmt.setString(3, user.getLast_name());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getPhone());
            pstmt.setInt(7, user.getId());
            pstmt.executeUpdate();
        }catch (Exception e){
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


}
