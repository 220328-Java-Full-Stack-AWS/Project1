package com.revature;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public static ArrayList<User> Users = new ArrayList<User>();
    public static int id = -1;
    public static int choice;
    public static Scanner scanner = new Scanner(System.in);
    public static String loginUsername;
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
                        if(user.getUsername().equals(loginUsername) && user.getPassword().equals(loginPassword)){
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
                        forgotPassword();
                    }

                    break;
                case 2:
                    register();
                    System.out.println("Account created!!");
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
        System.out.println("Please enter your username");
        loginUsername = scanner.next();
        System.out.println("Please enter your password");
        loginPassword = scanner.next();

    }

    public static void register(){
        User user = new User();
        String firstName;
        String lastName;
        String username;
        String email;
        String password;

        System.out.println("Register");
        System.out.println("--------");
        System.out.println("Please enter your first name:");
        firstName = scanner.next();
        System.out.println("Please enter your last name:");
        lastName = scanner.next();
        System.out.println("Create an Username:");
        username = scanner.next();
        System.out.println("Please enter a valid email:");
        email = scanner.next();
        System.out.println("Please enter a strong password:");
        password = scanner.next();


        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        Users.add(user);
    }

    public static void forgotPassword(){
        int forgotPasswordChoice;
        String tempEmail;
        String changePassword;

        li : while (true) {
            System.out.println("Forget your password?");
            System.out.println("---1. Yes");
            System.out.println("---2. No");
            forgotPasswordChoice = scanner.nextInt();

            switch(forgotPasswordChoice){
                case 1:
                    System.out.println("Enter the email linked to your account:");
                    tempEmail = scanner.next();

                    ArrayList<Boolean> emailResult = new ArrayList<Boolean>();
                    int emailId = -1;
                    for(User user : Users){
                        if(user.getEmail().equals(tempEmail)){
                            emailResult.add(true);
                        }else{
                            emailResult.add(false);
                        }
                    }
                    emailId = emailResult.indexOf(true);

                    if(emailId != -1){
                        System.out.println("Your Username is " + Users.get(emailId).getUsername());
                        System.out.println("Please change your password");
                        changePassword = scanner.next();
                        Users.get(emailId).setPassword(changePassword);
                        System.out.println("Password changed successfully");
                        break li;
                    }else{
                        System.out.println("Email not found, please register!");
                        break li;
                    }
                case 2:
                    break li;
                default:
                    System.out.println("Invalid Choice");
            }
        }

    }

    public static void Account(int index){
        ReinbursementSystem userAccount = new ReinbursementSystem();
        userAccount.Account(Users.get(index));
//        System.out.println("");
//        System.out.println("Welcome, " + Users.get(index).getFirstName() + " " + Users.get(index).getLastName());
//        System.out.println("Account Information");
//        System.out.println("-------------------");
//        System.out.println("Account Type: " + Users.get(index).getAccountType());
//        System.out.println("Email: " + Users.get(index).getEmail());
    }


}
