package com.revature;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public static ArrayList<User> Users = new ArrayList<User>();
    public static int id = -1;
    public static int choice;
    public static Scanner scanner = new Scanner(System.in);
    public static String loginEmail;
    public static String loginPassword;

    public static void main(String[] args){
        System.out.println("---------------------");
        System.out.println("WELCOME TO THE SYSTEM");
        System.out.println("---------------------");
        lp : while(true){
            System.out.println("");
            System.out.println("Choose an option");
            Menu();
            choice = scanner.nextInt();

            switch(choice){
                case 1:
                    if(Users.isEmpty()){
                        System.out.println("There are currently no users, please register");
                        break;
                    } //else if( loginEmail.equals(Users.get(0).getEmail()) && loginPassword.equals(Users.get(0).getPassword()))
                        //System.out.println("Welcome " + Users.get(0).getFirstName() + " " + Users.get(0).getLastName());
                    login();


                    ArrayList<Boolean> result = new ArrayList<Boolean>();
                    for(User user : Users){
                        if(user.getEmail().contains(loginEmail) && user.getPassword().contains(loginPassword)){
                            result.add(true);
                        }else{
                            result.add(false);
                        }
                    }
                    //System.out.println(result.indexOf(true));
                    id = result.indexOf(true);

                    if( id != -1 ){
                        Account(id);
                        //System.out.println("Welcome " + Users.get(id).getFirstName() + " " + Users.get(id).getLastName());
                    }else {
                        System.out.println("Account not found.");
                    }

                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Goodbye");
                    break lp;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public static void Menu(){
        System.out.println("1: Login");
        System.out.println("2: Register ");
        System.out.println("3: Exit");
    }

    public static void login(){

        System.out.println("Login");
        System.out.println("-----");
        System.out.println("Please enter your email");
        loginEmail = scanner.next();
        System.out.println("Please enter your password");
        loginPassword = scanner.next();

    }

    public static void register(){
        User user = new User();
        String firstName;
        String lastName;
        String email;
        String password;

        System.out.println("Register");
        System.out.println("--------");
        System.out.println("Please enter your first name:");
        firstName = scanner.next();
        System.out.println("Please enter your last name:");
        lastName = scanner.next();
        System.out.println("Please enter a valid email:");
        email = scanner.next();
        System.out.println("Please enter a strong password:");
        password = scanner.next();


        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        Users.add(user);
    }

    public static void Account(int index){
        int adminOption;
        System.out.println("");
        System.out.println("Welcome, " + Users.get(index).getFirstName() + " " + Users.get(index).getLastName());
        System.out.println("Account Information");
        System.out.println("-------------------");
        System.out.println("Account Type: " + Users.get(index).getAccountType());
        System.out.println("Email: " + Users.get(index).getEmail());

    }


}
