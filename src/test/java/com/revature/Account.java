package com.revature;

import java.util.Scanner;

public class Account {

    private static int menuChoice;
    private static Scanner input = new Scanner(System.in);


    public void Account(User user){
        System.out.println("");
        System.out.println("Welcome, " + user.getFirstName() + " " + user.getLastName());
        System.out.println("");
        System.out.println("Account Information");
        System.out.println("-------------------");
        System.out.println("Account Type: " + user.getAccountType());
        System.out.println("Linked Email: " + user.getEmail());
        System.out.println("Username: " + user.getUsername());
        Menu();
    }

    public static void Menu(){

        lo : while (true) {
            System.out.println("");
            System.out.println("What would you like to do today?");
            System.out.println("-1. New Reimbursement Request");
            System.out.println("-2. View Reimbursement Request");
            System.out.println("-3. View Reimbursement History");
            System.out.println("-4. Settings");
            System.out.println("-5. Logout");

            menuChoice = input.nextInt();

            switch (menuChoice) {
                case 1:
                    System.out.println("New Reimbursement Request");
                    break;
                case 2:
                    System.out.println("View Reimbursement Request");
                    break;
                case 3:
                    System.out.println("View Reimbursement History");
                    break;
                case 4:
                    System.out.println("Settings");
                case 5:
                    break lo;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

}
