package com.revature;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.util.Scanner;

public class Driver {


    public static void main(String[] args) {

        try{
            Connection conn = ConnectionFactory.getConnection();
        } catch(Exception e){
            e.printStackTrace();
        }
//        Scanner input = new Scanner(System.in);
//        int userChoice;
//
//        // Random user to test MockDB
//        User user1 = new User(1, "LeoBarrientos02", "password", Role.EMPLOYEE);
//        User user2 = new User(2, "EthanTeach", "password", Role.FINANCE_MANAGER);
//
//
//        System.out.println("Welcome to the Employee Reimbursement System");
//        System.out.println("--------------------------------------------");
//
//        lp : while (true) {
//            AuthMenu();
//            userChoice = input.nextInt();
//
//            switch (userChoice) {
//                case 1:
//                    LoginMenu();
//                    break;
//                case 2:
//                    RegisterMenu();
//                    break;
//                case 3:
//                    System.out.println("Goodbye");
//                    break lp;
//                default:
//                    System.out.println("Invalid Choice");
//            }
//
//        }
    }

    private static void AuthMenu(){
        System.out.println("Choose an option:");
        System.out.println("1- Login");
        System.out.println("2- Register");
        System.out.println("3-Exit");
    }

    private static void LoginMenu(){
        System.out.println("Login");
        System.out.println("Enter your username:");
        System.out.println("Enter your password");
    }

    private static void RegisterMenu(){
        System.out.println("Register");
        System.out.println("--------");
    }
}
