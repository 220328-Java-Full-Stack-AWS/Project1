package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ReimbursementDAO {
    // Userdao to use some methods such as searchByID
    UserDAO userDao = new UserDAO();

    // Structure of methods are in the CRUD format (Create, Read, Update, Delete)
    //Create
    public Reimbursement create(Reimbursement model, String description, int reimbursement_type){
        Reimbursement reimbursement = new Reimbursement();
        String sql = "INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) VALUES (?,?,?,?,?,?,?)";
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

            // Resolver
            pstmt.setInt(5, model.getResolver().getId());

            // Status
            pstmt.setInt(6, 1); // ers_status_id = 1 is "Pending"

            // Type
            pstmt.setInt(7, reimbursement_type); // get the reimbursement through the parameter

            // Execute
            pstmt.executeUpdate();

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
                // Author
                int authorId = rs.getInt("reimb_author");
                User author = userDao.read(authorId);
                reimbursement.setAuthor(author);
                // Resolver
                int resolverId = rs.getInt("reimb_resolver");
                User resolver = userDao.read(resolverId);
                reimbursement.setResolver(resolver);
                // Amount
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
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
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
        return Collections.emptyList();
    }

    // Update
    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {
    	return null;
    }

    // Delete
    public void deleteReimbursement(int id){
        String sql = "DELETE * FROM ers_reimbursement WHERE reimb_id = ?";
        try{
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
