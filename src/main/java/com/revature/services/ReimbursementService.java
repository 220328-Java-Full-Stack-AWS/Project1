package com.revature.services;

import com.revature.exceptions.ReimbursementDoesNotExistException;
import com.revature.exceptions.UnvalidPermissionException;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The ReimbursementService should handle the submission, processing,
 * and retrieval of Reimbursements for the ERS application.
 *
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create Reimbursement</li>
 *     <li>Update Reimbursement</li>
 *     <li>Get Reimbursements by ID</li>
 *     <li>Get Reimbursements by Author</li>
 *     <li>Get Reimbursements by Resolver</li>
 *     <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {
    ReimbursementDAO reimbDAO = new ReimbursementDAO();
    /**
     * <ul>
     *     <li>Should ensure that the user is logged in as a Finance Manager</li>
     *     <li>Must throw exception if user is not logged in as a Finance Manager</li>
     *     <li>Should ensure that the reimbursement request exists</li>
     *     <li>Must throw exception if the reimbursement request is not found</li>
     *     <li>Should persist the updated reimbursement status with resolver information</li>
     *     <li>Must throw exception if persistence is unsuccessful</li>
     * </ul>
     *
     * Note: unprocessedReimbursement will have a status of PENDING, a non-zero ID and amount, and a non-null Author.
     * The Resolver should be null. Additional fields may be null.
     * After processing, the reimbursement will have its status changed to either APPROVED or DENIED.
     */
    public Reimbursement process(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver) {
        // Ensure logged-in user is a Finance Manager
        boolean isFinancialManager = false;
        boolean reimbursementExist = false;
        String userType = String.valueOf(resolver.getRole());
        if(userType.equals("Employee")){
            isFinancialManager = false;
            throw new UnvalidPermissionException("You are not a Finance Manager");
        }else{
            isFinancialManager = true;
        }

        // ensure the reimbursement request exist
        Reimbursement reimbursement = reimbDAO.getReimbursementById(unprocessedReimbursement.getId());
        if(reimbursement.getId() == 0){
            reimbursementExist = false;
            throw new ReimbursementDoesNotExistException("Reimbursement request was not found.");
        }else{
            reimbursementExist = true;
        }

        // Both test above passes means that we can try to process the request
        reimbursement.setResolver(resolver);
        reimbursement.setStatus(finalStatus);
        reimbDAO.complete(reimbursement);

        Reimbursement updatedReimbursement = reimbDAO.getReimbursementById(reimbursement.getId());

        return updatedReimbursement;
    }

    public Reimbursement create(Reimbursement model){
        return reimbDAO.create(model);
    }

    public Reimbursement NewRequest(Reimbursement model, String description, int type){
        return reimbDAO.NewRequest(model, description, type);
    }

    public List<Reimbursement> getAllPending(User user){
        List<Reimbursement> allPending = reimbDAO.getPending(user.getUsername());
        return allPending;
    }

    /**
     * Should retrieve all reimbursements with the correct status.
     */
    public List<Reimbursement> getReimbursementsByStatus(Status status) {
        List<Reimbursement> result = reimbDAO.getByStatus(status);
        if(result.isEmpty()){
            return Collections.emptyList();
        }else{
            return result;
        }
    }

    /**
     * Get a reimbursement by its id.
     * @param id the reimbursement we are looking for.
     * @return the reimbursement linked with the id.
     */
    public Reimbursement getById(int id){
        return reimbDAO.getReimbursementById(id);
    }

    /**
     * This is used to return all reimbursement associated with the author's email.
     * @param user
     * @return A list of reimbursements associated with the authors's email.
     */
    public List<Reimbursement> getByAuthor(User user){
        List<Reimbursement> ReimbursementResolverList = reimbDAO.getByAuthorEmail(user.getEmail());
        return ReimbursementResolverList;
    }

    /**
     * This is used to return all reimbursement associated with the author's username.
     * @param user
     * @return A list of reimbursements associated with the author's username.
     */
    public List<Reimbursement> getByAuthor2(User user){
        List<Reimbursement> ReimbursementResolverList = reimbDAO.getByAuthor(user.getUsername());
        return ReimbursementResolverList;
    }

    /**
     * Get all reimbursement associated with the resolver's email
     * @param user - the resolver
     * @return A list of all reimbursement associated with the resolver's email.
     */
    public List<Reimbursement> getByResolver(User user){
        List<Reimbursement> ReimbursementResolverList = reimbDAO.getByResolver(user.getEmail());
        return ReimbursementResolverList;
    }

    /**
     * Get all reimbursement associated with the resolver's username.
     * @param user - the resolver.
     * @return A list of all reimbursement associated with the resolver's username.
     */
    public List<Reimbursement> getByResolver2(User user){
        List<Reimbursement> ReimbursementResolverList = reimbDAO.getByResolverUsername(user.getUsername());
        return ReimbursementResolverList;
    }

    /**
     * This gets all reimbursements in the database
     * @return A linkedList of all reimbursements.
     */
    public List<Reimbursement> getAllReimbursements(){
        List<Reimbursement> AllReimbursements = reimbDAO.getAllReimbursements();
        return AllReimbursements;
    }

    public void update(Reimbursement model){
        reimbDAO.update(model);
    }

    public void deleteReimbursement(int id){
        reimbDAO.deleteReimbursement(id);
    }

}
