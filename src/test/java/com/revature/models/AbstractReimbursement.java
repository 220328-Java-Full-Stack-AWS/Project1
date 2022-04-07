package com.revature.models;

import java.util.Objects;

public class AbstractReimbursement {
    private int id;
    private Status status;
    private User author;
    private User resolver;
    private double amount;

    public AbstractReimbursement() {
    }

    public AbstractReimbursement(int id, Status status, User author, User resolver, double amount) {
        this.id = id;
        this.status = status;
        this.author = author;
        this.resolver = resolver;
        this.amount = amount;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getResolver() {
        return this.resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            AbstractReimbursement that = (AbstractReimbursement)o;
            return this.id == that.id && Double.compare(that.amount, this.amount) == 0 && this.status == that.status && Objects.equals(this.author, that.author) && Objects.equals(this.resolver, that.resolver);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.status, this.author, this.resolver, this.amount});
    }

    public String toString() {
        return "AbstractReimbursement{id=" + this.id + ", status=" + this.status + ", author=" + this.author + ", resolver=" + this.resolver + ", amount=" + this.amount + '}';
    }
}
