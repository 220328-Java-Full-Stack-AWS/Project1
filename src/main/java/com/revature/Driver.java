package com.revature;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Driver {


    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        User user = dao.read(7);
        System.out.println("User: " + user.toString());

        // Trying the create method
//        User jose = new User(0,"JoZ003", "password","Jose","Barrientos","JoZ003@gmail.com","917-002-1010", Role.EMPLOYEE);
//        //System.out.println(jose);
//        dao.create(jose);

//        User daniel = new User(0,"DamnDaniel", "youCANtCmE","Daniel","Cormier","danCormier@gmail.com","888-102-6522", Role.EMPLOYEE);
//        dao.create(daniel);

//        User kyle = new User(0,"kyle3000", "password","Kyle","Plummer","kylePlummer@gmail.com","516-666-0011", Role.FINANCE_MANAGER);
//        dao.create(kyle);

//        dao.deleteUser(6);

        // Get all users from the ers_users table
        List<User> list = dao.getAllUsers();
        for(User temp : list){
            System.out.println("User " + temp.getId() + ", " + temp.getUsername() + ", " + temp.getPassword());
        }

        User me = dao.getByUsername("kyle3000");
        System.out.println(me);

        ConnectionFactory.close();


        //        try{
//            Connection conn = ConnectionFactory.getConnection();
//        } catch(Exception e){
//            e.printStackTrace();
//        }

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
