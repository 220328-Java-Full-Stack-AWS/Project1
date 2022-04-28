package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ReimbursementDAO {
    // Userdao to use some methods such as searchByID
    UserDAO userDao = new UserDAO();

    // Structure of methods are in the CRUD format (Create, Read, Update, Delete)
    //Create
    public Reimbursement create(Reimbursement model){
        Reimbursement reimbursement = new Reimbursement();
        String sql = "INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_status_id, reimb_type_id) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);

            // Amount
            pstmt.setDouble(1, model.getAmount());

            // Submitted
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            pstmt.setTimestamp(2, ts);

            // Description
            pstmt.setString(3, model.getDescription());

            // Author
            pstmt.setInt(4, model.getAuthor().getId());

            // Status
            pstmt.setInt(5, 1); // ers_status_id = 1 is "Pending"

            // Type
            pstmt.setInt(6, model.getReimbursementType()); // get the reimbursement through the parameter

            // Execute
            pstmt.executeUpdate();

            // get the reimb_id from the database
            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()) {
                int key = keys.getInt(1);
                model.setId(key);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursement;
    }

    public Reimbursement NewRequest(Reimbursement model, String description, int reimbursement_type){
        Reimbursement reimbursement = new Reimbursement();
        String sql = "INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_status_id, reimb_type_id) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);

            // Amount
            pstmt.setDouble(1, model.getAmount());

            // Submitted
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            pstmt.setTimestamp(2, ts);

            // Description
            pstmt.setString(3, description);

            // Author
            pstmt.setInt(4, model.getAuthor().getId());

            // Status
            pstmt.setInt(5, 1); // ers_status_id = 1 is "Pending"

            // Type
            pstmt.setInt(6, reimbursement_type); // get the reimbursement through the parameter

            // Execute
            pstmt.executeUpdate();

            // get the reimb_id from the database
            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()) {
                int key = keys.getInt(1);
                model.setId(key);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursement;
    }

    // Read
    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
    public Optional<Reimbursement> getById(int id) {
        Reimbursement reimbursement = new Reimbursement();
        boolean found = true;
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?";
        try{
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            while(rs.next()){
                // Id
                reimbursement.setId(rs.getInt("reimb_id"));
                // Status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){ // Pending status has an id=1
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){ // Approved status has an id=2
                    reimbursement.setStatus(Status.APPROVED);
                }else{ // If not 1 or 2 its id=3 Denied
                    reimbursement.setStatus(Status.DENIED);
                }
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // Author
                int authorId = rs.getInt("reimb_author");
                User author = userDao.getById(authorId);
                reimbursement.setAuthor(author);
                // Resolver
                int resolverId = rs.getInt("reimb_resolver");
                User resolver = userDao.getById(resolverId);
                reimbursement.setResolver(resolver);
                // Amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        if(found){
            return Optional.of(reimbursement);
        }else{
            return Optional.empty();
        }
    }

    /**
     * Get the reimbursement without the optional
     * @param id
     * @return
     */
    public Reimbursement getReimbursementById(int id) {
        Reimbursement reimbursement = new Reimbursement();
        boolean found = true;
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?";
        try{
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            while(rs.next()){
                // Id
                reimbursement.setId(rs.getInt("reimb_id"));
                // Status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){ // Pending status has an id=1
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){ // Approved status has an id=2
                    reimbursement.setStatus(Status.APPROVED);
                }else{ // If not 1 or 2 its id=3 Denied
                    reimbursement.setStatus(Status.DENIED);
                }
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // Author
                int authorId = rs.getInt("reimb_author");
                User author = userDao.getById(authorId);
                reimbursement.setAuthor(author);
                // Resolver
                int resolverId = rs.getInt("reimb_resolver");
                User resolver = userDao.getById(resolverId);
                reimbursement.setResolver(resolver);
                // Amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return reimbursement;
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = ?";
        int statusConverter = 0;
        if(status.toString().equals("Pending")){
            statusConverter = 1;
        }else if(status.toString().equals("Approved")){
            statusConverter = 2;
        }else{
            statusConverter = 3; // Denied
        }

        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, statusConverter);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                User author  = userDao.getById(rs.getInt("reimb_author"));
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                reimbursement.setStatus(status);
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }

    public List<Reimbursement> getByType(String type) {
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_type_id = ?";
        int typeConverter = 0;
        if(type.equals("Lodging")){
            typeConverter = 1;
        }else if(type.equals("Food")){
            typeConverter = 2;
        }else if(type.equals("Travel")){
            typeConverter = 3;
        }else if(type.equals("Entertainment")){
            typeConverter = 4;
        }else if(type.equals("Shopping")){
            typeConverter = 5;
        }else{
            typeConverter = 6;
        }

        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, typeConverter);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                User author  = userDao.getById(rs.getInt("reimb_author"));
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }


    /**
     * Retrieve all reimbursements by the author
     */
    public List<Reimbursement> getByAuthor(String username) {
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ? ORDER BY reimb_id ASC";
        User author = userDao.getByUsername(username);
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, author.getId());

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));
                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }

    /**
     * This gets all the reimbursements associated with the Author
     * @param email - used to search for the author linked in the table
     * @return A list of all reimbursements posted by the author
     */
    public List<Reimbursement> getByAuthorEmail(String email) {
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ? ORDER BY reimb_id ASC";
        User author = userDao.getUserByEmail(email);
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, author.getId());

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }

    public List<Reimbursement> getPending(String username) {
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ? AND reimb_status_id = 1";
        User author = userDao.getByUsername(username);
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, author.getId());

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }

    // sort id
    public List<Reimbursement> employeeSortIdAsc(int id){
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ? ORDER BY reimb_id ASC";
        User author = userDao.getById(id);
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, author.getId());

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }
    public List<Reimbursement> employeeSortIdDesc(int id){
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ? ORDER BY reimb_id DESC";
        User author = userDao.getById(id);
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, author.getId());

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }
    // sort amount
    public List<Reimbursement> employeeSortAmountAsc(int id){
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ? ORDER BY reimb_amount ASC";
        User author = userDao.getById(id);
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, author.getId());

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }
    public List<Reimbursement> employeeSortAmountDesc(int id){
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ? ORDER BY reimb_amount DESC";
        User author = userDao.getById(id);
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, author.getId());

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }
    // sort date
    public List<Reimbursement> employeeSortDateAsc(int id){
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ? ORDER BY reimb_submitted ASC";
        User author = userDao.getById(id);
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, author.getId());

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }
    public List<Reimbursement> employeeSortDateDesc(int id){
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ? ORDER BY reimb_submitted DESC";
        User author = userDao.getById(id);
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, author.getId());

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }
    //filter
    public List<Reimbursement> employeeFilterStatus(int id, int statusId){
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ? AND reimb_status_id = ?";
        User author = userDao.getById(id);
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, author.getId());
            pstmt.setInt(2, statusId);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }
    public List<Reimbursement> employeeFilterType(int id, int typeId){
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ? AND reimb_type_id = ?";
        User author = userDao.getById(id);
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, author.getId());
            pstmt.setInt(2, typeId);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }


    /**
     * Retrieve all reimbursements by the resolver's email
     */
    public List<Reimbursement> getByResolverId(int id) {
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_resolver = ?";
        User resolver = userDao.getById(id);
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, resolver.getId());

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                reimbursement.setResolver(resolver);
                // author
                User author1  = userDao.getById(rs.getInt("reimb_author"));
                reimbursement.setAuthor(author1);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }

    /**
     * Retrieve all reimbursements by the resolver's username
     */
    public List<Reimbursement> getByResolverUsername(String username) {
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement WHERE reimb_resolver = ?";
        User resolver = userDao.getByUsername(username);
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, resolver.getId());

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                reimbursement.setResolver(resolver);
                // author
                User author  = userDao.getById(rs.getInt("reimb_author"));
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }


    /**
     * Get all reimbursements
     */
    public List<Reimbursement> getAllReimbursements() {
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement ORDER BY reimb_id ASC";
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                User author  = userDao.getById(rs.getInt("reimb_author"));
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }

    public List<Reimbursement> getAllReimbursementDESCId() {
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement ORDER BY reimb_id DESC";
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                User author  = userDao.getById(rs.getInt("reimb_author"));
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }

    public List<Reimbursement> getAllReimbursementDESCAmount() {
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement ORDER BY reimb_amount DESC";
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                User author  = userDao.getById(rs.getInt("reimb_author"));
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }

    public List<Reimbursement> getAllReimbursementASCAmount() {
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement ORDER BY reimb_amount ASC";
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                User author  = userDao.getById(rs.getInt("reimb_author"));
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }

    public List<Reimbursement> getAllReimbursementDESCDate() {
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement ORDER BY reimb_submitted DESC";
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                User author  = userDao.getById(rs.getInt("reimb_author"));
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }

    public List<Reimbursement> getAllReimbursementASCDate() {
        List<Reimbursement> result = new LinkedList<>();
        String sql = "SELECT * FROM ers_reimbursement ORDER BY reimb_submitted ASC";
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(); // actually fire off the SQL

            // get the data from the database
            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                // reimbursement id
                reimbursement.setId(rs.getInt("reimb_id"));
                // resolver
                User resolver = userDao.getById(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(resolver);
                // author
                User author  = userDao.getById(rs.getInt("reimb_author"));
                reimbursement.setAuthor(author);
                // amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // status
                int status = rs.getInt("reimb_status_id");
                if(status == 1){
                    reimbursement.setStatus(Status.PENDING);
                }else if(status == 2){
                    reimbursement.setStatus(Status.APPROVED);
                }else{
                    reimbursement.setStatus(Status.DENIED);
                }
                // type
                reimbursement.setReimbursementType(rs.getInt("reimb_type_id"));
                // description
                reimbursement.setDescription(rs.getString("reimb_description"));
                // submitted
                reimbursement.setSubmitted(rs.getTimestamp("reimb_submitted"));
                // resolved
                reimbursement.setResolved(rs.getTimestamp("reimb_resolved"));
                // receipt
                reimbursement.setReceipt(rs.getString("reimb_receipt"));

                // add to list
                result.add(reimbursement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }

    }

    // Update
    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public void update(Reimbursement unprocessedReimbursement) {
        String sql = "UPDATE ers_reimbursement SET reimb_amount = ?, reimb_submitted = ?, reimb_description = ?, reimb_type_id = ? WHERE reimb_id = ?";
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);

            // edit amount
            pstmt.setDouble(1, unprocessedReimbursement.getAmount());
            // update submitted time
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            pstmt.setTimestamp(2, ts);
            // edit description
            pstmt.setString(3, unprocessedReimbursement.getDescription());
            // edit type
            pstmt.setInt(4, unprocessedReimbursement.getReimbursementType());
            // id
            pstmt.setInt(5, unprocessedReimbursement.getId());
            // execute
            pstmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void complete(Reimbursement model) {
        String sql = "UPDATE ers_reimbursement SET reimb_resolved = ?, reimb_status_id = ?, reimb_resolver = ?, reimb_receipt = ? WHERE reimb_id = ?";
        try{
            Connection conn = ConnectionFactory.getConnection();  // get the connection
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);

            // resolved timestamp
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            pstmt.setTimestamp(1, ts);
            // status update
            String status = String.valueOf(model.getStatus());
            if(status.equals("Pending")){
                pstmt.setInt(2, 1);
            }else if(status.equals("Approved")){
                pstmt.setInt(2, 2);
            }else if(status.equals("Denied")){
                pstmt.setInt(2, 3);
            }
            //resolver
            pstmt.setInt(3, model.getResolver().getId());
            // receipt
            String receipt = "Your reimbursement request of $" + model.getAmount() + " has been " + status + " by " + model.getResolver().getFirst_name() + " " + model.getResolver().getLast_name();
            pstmt.setString(4, receipt);
            // get id
            pstmt.setInt(5, model.getId());
            pstmt.executeUpdate(); // actually fire off the SQL
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteReimbursement(int id){
        String sql = "DELETE FROM ers_reimbursement WHERE reimb_id = ?";
        try{
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
