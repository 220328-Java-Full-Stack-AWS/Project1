package com.revature;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;
import java.util.Scanner;

public class Driver {

    private static User user;
    private static String userInput;
    private static Scanner input = new Scanner(System.in);
    private static AuthService authentication = new AuthService();
    private static ReimbursementService reimbursementService = new ReimbursementService();

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
        System.out.println("Choose an option:");
        System.out.println("1- Login");
        System.out.println("2- Register");
        System.out.println("3- Exit");
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

        if(user.getId() != 0){
            //System.out.println(user); prints the logged in user

            //test 1
//            User tempFinance = userdao.getUser("kylePlummer@gmail.com");
//            Reimbursement r1 = new Reimbursement(0, Status.PENDING, user, tempFinance, 50.00);
//            dao.create(r1, "I forgot to cancel my subscription, after the free-trial", 4);

            //test 2
//            User tempFinance = userdao.getUser("johnDoe02@gmail.com");
//            Reimbursement r2 = new Reimbursement(0, Status.PENDING, user, tempFinance, 15.00);
//            dao.create(r2, "The store sold me spoiled milk", 2);

            // test the getById reimbursement
//            System.out.println(dao.getById(3)); // passes

            // test the deleteReimbursement method
//            User tempFinance = userdao.getUser("johnDoe02@gmail.com");
//            Reimbursement r2 = new Reimbursement(0, Status.PENDING, user, tempFinance, 25.00);
//            dao.create(r2, "I bought the wrong size pants", 5);
//            dao.deleteReimbursement(3);
//            dao.deleteReimbursement(4);

            // adding more reimbursement types to test the get by status
//            User tempFinance = userdao.getUser("kylePlummer@gmail.com");
//            Reimbursement r2 = new Reimbursement(0, Status.PENDING, user, tempFinance, 82.32);
//            dao.create(r2, "I was told that the company would pay for my gas, for this business trip.", 3);

            // test get by status PASSES
//            List<Reimbursement> result = dao.getByStatus(Status.PENDING);
//            for(Reimbursement r : result){
//                System.out.println(r);
//            }


            // get by author works using the username and email
            //List<Reimbursement> result = dao.getByAuthor(user.getUsername()); PASSES
            //List<Reimbursement> result = dao.getByAuthorEmail(user.getEmail()); PASSES
            //List<Reimbursement> result = dao.getByResolver("johnDoe02@gmail.com");PASSES
            //List<Reimbursement> result = dao.getByResolverUsername("johnDoe01"); PASSES
//            List<Reimbursement> result = dao.getAllReimbursements(); PASSES
//            for(Reimbursement r : result){
//                System.out.println(r.getDescription() + ", $" + r.getAmount() + ", " + r.getStatus());
//            }

//            // test the complete reimbursement PASSES
//            Reimbursement test = dao.getReimbursementsById(9);
//            test.setStatus(Status.APPROVED);
//            dao.complete(test);

            //test the update pending request PASSES
//            Reimbursement test = dao.getReimbursementsById(7);
//            test.setAmount(500.03);
//            test.setDescription("Need some extra cash to pay rent.");
//            test.setReimbursementType(1);
//            dao.update(test);

            //System.out.println(dao.getById(7));

            ERSystem();

        }

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

    private static void ERSystem(){
        int ersChoice;
        System.out.println("Welcome " + " " +  user.getUsername());
        System.out.println(user.getRole());
        System.out.println("What would you like to do today?");

        String role = String.valueOf(user.getRole());
        if(role.equals("Employee")){
            li : while(true){
                EmployeeERS();
                ersChoice = input.nextInt();

                switch(ersChoice){
                    case 1:
                        System.out.println("New Reimbursement Request");
                        break;
                    case 2:
                        System.out.println("View All Pending Request");
                        break;
                    case 3:
                        System.out.println("Edit Pending Request");
                        break;
                    case 4:
                        System.out.println("View Reimbursement History");
                        break;
                    case 5:
                        System.out.println("Delete Pending Request");
                        break;
                    case 6:
                        System.out.println("Edit my account");
                        break;
                    case 7:
                        System.out.println("Logged Out");
                        break li;
                    default:
                        System.out.println("Wrong Input");

                }
            }
        }else{
            li : while(true){
                FinanceManagerERS();
                ersChoice = input.nextInt();
                switch(ersChoice){
                    case 1:
                        System.out.println("View All Request by Status");
                        break;
                    case 2:
                        System.out.println("View All Reimbursements");
                        break;
                    case 3:
                        System.out.println("View your Reimbursements history");
                        break;
                    case 4:
                        System.out.println("Edit my account");
                        break;
                    case 5:
                        System.out.println("Logged Out");
                        break li;
                    default:
                        System.out.println("Wrong Input");

                }
            }
        }

    }

    private static void EmployeeERS(){
        System.out.println("1 - New Reimbursement Request");
        System.out.println("2 - View All Pending Request");
        System.out.println("3 - Edit Pending Request");
        System.out.println("4 - View Reimbursement History");
        System.out.println("5 - Delete Pending Request");
        System.out.println("6 - Edit my account");
        System.out.println("7 - Log out");
    }

    private static void FinanceManagerERS(){
        System.out.println("1 - View All Request by Status");
        System.out.println("2 - View All Reimbursements");
        System.out.println("3 - View your Reimbursements history");
        System.out.println("4 - Edit my account");
        System.out.println("5 - Log out");
    }


}
