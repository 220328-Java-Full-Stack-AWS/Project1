package com.revature.models;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * This AbstractReimbursement class defines a minimum functionality for
 * interacting with reimbursements in the ERS application.
 *
 * All reimbursements in this application must at least have:
 * <ul>
 *     <li>ID</li>
 *     <li>Status</li>
 *     <li>Author</li>
 *     <li>Resolver</li>
 *     <li>Amount</li>
 * </ul>
 *
 * Additional fields can be added to the concrete {@link com.revature.models.Reimbursement} class.
 *
 * @author Center of Excellence
 */
public class AbstractReimbursement {

    private int id;
    private Status status;
    private User author;
    private User resolver;
    private double amount;
    private Timestamp submitted;
    private Timestamp resolved;
    private String description;
    private int reimbursementType;
    private String receipt;

    public AbstractReimbursement() {
        super();
    }

    public AbstractReimbursement(int id, Status status, User author, User resolver, double amount) {
        super();
        this.id = id;
        this.status = status;
        this.author = author;
        this.resolver = resolver;
        this.amount = amount;
    }
    public AbstractReimbursement(int id, Status status, User author, User resolver, double amount, String description, int reimbursementType){
        this.id = id;
        this.status = status;
        this.author = author;
        this.resolver = resolver;
        this.amount = amount;
        this.description = description;
        this.reimbursementType = reimbursementType;
    }

    public AbstractReimbursement(int id, Status status, User author, User resolver, double amount, String description, int reimbursementType, Timestamp submitted){
        this.id = id;
        this.status = status;
        this.author = author;
        this.resolver = resolver;
        this.amount = amount;
        this.description = description;
        this.reimbursementType = reimbursementType;
        this.submitted = submitted;
    }

    public AbstractReimbursement(int id, Status status, User author, User resolver, double amount, String description, int reimbursementType, Timestamp submitted, Timestamp resolved, String receipt){
        this.id = id;
        this.status = status;
        this.author = author;
        this.resolver = resolver;
        this.amount = amount;
        this.description = description;
        this.reimbursementType = reimbursementType;
        this.submitted = submitted;
        this.resolved = resolved;
        this.receipt = receipt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReimbursementType() {
        return reimbursementType;
    }

    public void setReimbursementType(int reimbursementType) {
        this.reimbursementType = reimbursementType;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractReimbursement)) return false;
        AbstractReimbursement that = (AbstractReimbursement) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && reimbursementType == that.reimbursementType && status == that.status && Objects.equals(author, that.author) && Objects.equals(resolver, that.resolver) && Objects.equals(submitted, that.submitted) && Objects.equals(resolved, that.resolved) && Objects.equals(description, that.description) && Objects.equals(receipt, that.receipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, author, resolver, amount, submitted, resolved, description, reimbursementType, receipt);
    }

    @Override
    public String toString() {
        return "Reimbursement:" +
                "id=" + id +
                ", status=" + status +
                ", author=" + author +
                ", resolver=" + resolver +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", reimbursementType=" + reimbursementType +
                ", receipt='" + receipt + '\'' +
                '.';
    }
}
