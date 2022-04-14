package com.revature;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.AuthService;
import java.util.Scanner;

public class Driver {

    private static User user;
    private static String userInput;
    private static Scanner input = new Scanner(System.in);
    private static   AuthService authentication = new AuthService();

    public static void main(String[] args) {
        int userChoice;

        System.out.println("Welcome to the Employee Reimbursement System");
        System.out.println("--------------------------------------------");

        lp : while (true) {
            AuthMenu();
            userChoice = input.nextInt();

            switch (userChoice) {
                case 1:
                    LoginMenu();
                    break;
                case 2:
                    RegisterMenu();
                    break;
                case 3:
                    System.out.println("Goodbye");
                    break lp;
                default:
                    System.out.println("Invalid Choice");
            }

        }

    }

    private static void AuthMenu(){
        System.out.println("");
        System.out.println("Choose an option:");
        System.out.println("1- Login");
        System.out.println("2- Register");
        System.out.println("3-Exit");
    }

    private static void LoginMenu(){
        System.out.println("Login");
        System.out.println("-----");

        // username
        System.out.println("Enter your email:");
        String username = input.next();

        // password
        System.out.println("Enter your password");
        String password = input.next();

        user = authentication.login(username, password);



    }

    private static void RegisterMenu(){
        System.out.println("Register");
        System.out.println("--------");

        User userToBeRegistered = new User();

        //first name
        System.out.println("Enter your first name:");
        userInput = input.next();
        userToBeRegistered.setFirst_name(userInput);

        // last name
        System.out.println("Enter your last name:");
        userInput = input.next();
        userToBeRegistered.setLast_name(userInput);

        // email
        System.out.println("Enter a valid email:");
        userInput = input.next();
        userToBeRegistered.setEmail(userInput);

        // phone
        System.out.println("Enter your phone number:");
        userInput = input.next();
        userToBeRegistered.setPhone(userInput);

        // role
        int role;
        System.out.println("Choose your role:");
        System.out.println("1: Employee");
        System.out.println("2: Finance Manager");
        role = input.nextInt();
        if(role == 1){
            userToBeRegistered.setRole(Role.EMPLOYEE);
        }else if(role == 2){
            userToBeRegistered.setRole(Role.FINANCE_MANAGER);
        }else{
            System.out.println("Wrong Input");
        }

        // username
        System.out.println("Create a username:");
        userInput = input.next();
        userToBeRegistered.setUsername(userInput);

        // password
        System.out.println("Create a password:");
        userInput = input.next();
        userToBeRegistered.setPassword(userInput);

        user = authentication.register(userToBeRegistered);
    }
}
