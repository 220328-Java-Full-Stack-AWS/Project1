package com.revature.models;

public enum Role {
    EMPLOYEE {
        public String toString() {
            return "Employee";
        }
    },
    FINANCE_MANAGER {
        public String toString() {
            return "Finance Manager";
        }
    };

    private Role() {
    }
}
