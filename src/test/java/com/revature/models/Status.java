package com.revature.models;

public enum Status {
    PENDING {
        public String toString() {
            return "Pending";
        }
    },
    APPROVED {
        public String toString() {
            return "Approved";
        }
    },
    DENIED {
        public String toString() {
            return "Denied";
        }
    };

    private Status() {
    }
}
