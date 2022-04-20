package com.revature;

import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Driver {

    private static User user;
    private static String userInput;
    private static Scanner input = new Scanner(System.in);
    private static AuthService authentication = new AuthService();
    private static UserService userService = new UserService();
    private static ReimbursementService reimbursementService = new ReimbursementService();

    public static void main(String[] args) throws IOException {
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
                        Reimbursement reimbursement = new Reimbursement();
                        reimbursement.setAuthor(user);
                        reimbursement.setStatus(Status.PENDING);
                        //Amount
                        System.out.println("Enter the reimbursement amount:");
                        double amount = input.nextDouble();
                        reimbursement.setAmount(amount);
                        // reimbursement type
                        System.out.println("Choose reimbursement type:");
                        System.out.println("1- Lodging");
                        System.out.println("2- Food");
                        System.out.println("3- Travel");
                        System.out.println("4- Entertainment");
                        System.out.println("5- Shopping");
                        System.out.println("6- Other");
                        int type = input.nextInt();
                        switch (type) {
                            case 1:
                                reimbursement.setReimbursementType(1);
                                break;
                            case 2:
                                reimbursement.setReimbursementType(2);
                                break;
                            case 3:
                                reimbursement.setReimbursementType(3);
                                break;
                            case 4:
                                reimbursement.setReimbursementType(4);
                                break;
                            case 5:
                                reimbursement.setReimbursementType(5);
                                break;
                            case 6:
                                reimbursement.setReimbursementType(6);
                                break;
                            default:
                                System.out.println("Invalid Choice");
                        }

                        input.nextLine();
                        // Description
                        System.out.println("Enter a description:");
                        String description = input.nextLine();
                        reimbursement.setDescription(description);

                        reimbursementService.NewRequest(reimbursement, reimbursement.getDescription(), reimbursement.getReimbursementType());
                        System.out.println("Reimbursement added!");
                        break;
                    case 2:
                        System.out.println("View All Pending Request");
                        List<Reimbursement> allPending = reimbursementService.getAllPending(user);
                        for(Reimbursement r : allPending){
                            System.out.println("Id:" + r.getId() + "- Amount:" + r.getAmount() + "- Description:" + r.getDescription() + "- Author:" + r.getAuthor().getUsername() + "- Resolver:" + r.getResolver().getUsername() + "- Status:" + r.getStatus() + "- Reimbursement Type:" + r.getReimbursementType());
                        }
                        break;
                    case 3:
                        System.out.println("Edit Pending Request");
                        List<Reimbursement> allPending2 = reimbursementService.getAllPending(user);
                        for(Reimbursement r : allPending2){
                            System.out.println("Id:" + r.getId() + "- Amount:" + r.getAmount() + "- Description:" + r.getDescription() + "- Author:" + r.getAuthor().getUsername() + "- Resolver:" + r.getResolver().getUsername() + "- Status:" + r.getStatus() + "- Reimbursement Type:" + r.getReimbursementType());
                        }
                        System.out.println("Enter the id of the reimbursement");
                        int reimbursementID = input.nextInt();
                        Reimbursement model = reimbursementService.getById(reimbursementID);
                        System.out.println("Id:" + model.getId() + "- Amount:" + model.getAmount() + "- Description:" + model.getDescription() + "- Author:" + model.getAuthor().getUsername() + "- Resolver:" + model.getResolver().getUsername() + "- Status:" + model.getStatus() + "- Reimbursement Type:" + model.getReimbursementType());
                        System.out.println("1 - Edit Amount");
                        System.out.println("2 - Edit Description");
                        System.out.println("3 - Edit Reimbursement Type");
                        System.out.println("4 - Cancel");
                        int editReimbursementChoice = input.nextInt();
                        switch(editReimbursementChoice){
                            case 1:
                                System.out.println("Enter new amount:");
                                double newAmount = input.nextDouble();
                                model.setAmount(newAmount);
                                break;
                            case 2:
                                input.nextLine();
                                System.out.println("Enter new description:");
                                String newDescription = input.nextLine();
                                model.setDescription(newDescription);
                                break;
                            case 3:
                                System.out.println("Choose reimbursement type:");
                                System.out.println("1- Lodging");
                                System.out.println("2- Food");
                                System.out.println("3- Travel");
                                System.out.println("4- Entertainment");
                                System.out.println("5- Shopping");
                                System.out.println("6- Other");
                                int newType = input.nextInt();
                                switch (newType) {
                                    case 1:
                                        model.setReimbursementType(1);
                                        break;
                                    case 2:
                                        model.setReimbursementType(2);
                                        break;
                                    case 3:
                                        model.setReimbursementType(3);
                                        break;
                                    case 4:
                                        model.setReimbursementType(4);
                                        break;
                                    case 5:
                                        model.setReimbursementType(5);
                                        break;
                                    case 6:
                                        model.setReimbursementType(6);
                                        break;
                                    default:
                                        System.out.println("Invalid Choice");
                                }
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("View Reimbursement History");
                        List<Reimbursement> history = reimbursementService.getByAuthor(user);
                        for(Reimbursement r : history){
                            System.out.println("Id:" + r.getId() + "- Amount:" + r.getAmount() + "- Description:" + r.getDescription() + "- Author:" + r.getAuthor().getUsername() + "- Resolver:" + r.getResolver().getUsername() + "- Status:" + r.getStatus() + "- Reimbursement Type:" + r.getReimbursementType());
                        }
                        break;
                    case 5:
                        System.out.println("Delete Pending Request");
                        List<Reimbursement> allReimbursements = reimbursementService.getByAuthor(user);
                        for(Reimbursement r : allReimbursements){
                            System.out.println("Id:" + r.getId() + "- Amount:" + r.getAmount() + "- Description:" + r.getDescription() + "- Author:" + r.getAuthor().getUsername() + "- Resolver:" + r.getResolver().getUsername() + "- Status:" + r.getStatus() + "- Reimbursement Type:" + r.getReimbursementType());
                        }
                        System.out.println("Enter the reimbursement id you want to delete:");
                        int deleteId = input.nextInt();
                        reimbursementService.deleteReimbursement(deleteId);
                        System.out.println("Reimbursement Removed");
                        break;
                    case 6:
                        System.out.println("Edit my account");
                        System.out.println(userService.getById(user.getId()).getFirst_name());
                        System.out.println(userService.getById(user.getId()).getLast_name());
                        System.out.println(userService.getById(user.getId()).getEmail());
                        System.out.println(userService.getById(user.getId()).getPhone());
                        System.out.println(userService.getById(user.getId()).getUsername());
                        System.out.println(userService.getById(user.getId()).getPassword());
                        System.out.println("What would you like to edit?");

                        System.out.println("1 - Change First Name");
                        System.out.println("2 - Change Last Name");
                        System.out.println("3 - Change Email");
                        System.out.println("4 - Change Phone");
                        System.out.println("5 - Change Username");
                        System.out.println("6 - Change Password");
                        System.out.println("7 - Cancel");
                        int userChange = input.nextInt();
                        switch (userChange){
                            case 1:
                                System.out.println("Enter new first name:");
                                String newFirstName = input.next();
                                user.setFirst_name(newFirstName);
                                break;
                            case 2:
                                System.out.println("Enter new last name:");
                                String newLastName = input.next();
                                user.setLast_name(newLastName);
                                break;
                            case 3:
                                System.out.println("Enter new email:");
                                String newEmail = input.next();
                                user.setEmail(newEmail);
                                break;
                            case 4:
                                System.out.println("Enter new Phone:");
                                String newPhone = input.next();
                                user.setPhone(newPhone);
                                break;
                            case 5:
                                System.out.println("Enter new username:");
                                String newUsername = input.next();
                                // test if username is taken
                                UserDAO userDao = new UserDAO();
                                List<String> usernames = userDao.getAllUsernames();
                                if(usernames.contains(newUsername)){
                                    System.out.println("Username already taken");
                                    throw new UsernameNotUniqueException("Username already taken");
                                }else{
                                    user.setPhone(newUsername);
                                }
                                break;
                            case 6:
                                System.out.println("Enter new Password:");
                                String newPassword = input.next();
                                user.setPassword(newPassword);
                                break;
                            case 7:
                                System.out.println("Cancel");
                                break;
                            default:
                                System.out.println("Invalid Input");
                        }
                        userService.updateUser(user);
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
                        System.out.println("Pending Reimbursements");
                        System.out.println("----------------------");
                        List<Reimbursement> pendingReimbursements = reimbursementService.getReimbursementsByStatus(Status.PENDING);
                        for(Reimbursement r : pendingReimbursements){
                            System.out.println("Id:" + r.getId() + "- Amount:" + r.getAmount() + "- Description:" + r.getDescription() + "- Author:" + r.getAuthor().getUsername() + "- Resolver:" + r.getResolver().getUsername() + "- Status:" + r.getStatus() + "- Reimbursement Type:" + r.getReimbursementType());
                        }
                        System.out.println("Enter the ID of the reimbursement");
                        int reimbursementID = input.nextInt();
                        Reimbursement model = reimbursementService.getById(reimbursementID);
                        System.out.println("Id:" + model.getId() + "- Amount:" + model.getAmount() + "- Description:" + model.getDescription() + "- Author:" + model.getAuthor().getUsername() + "- Resolver:" + model.getResolver().getUsername() + "- Status:" + model.getStatus() + "- Reimbursement Type:" + model.getReimbursementType());
                        System.out.println("1 - Approve");
                        System.out.println("2 - Deny");
                        System.out.println("3- Cancel");
                        int processChoice = input.nextInt();
                        if(processChoice == 1){
                            Reimbursement process = reimbursementService.process(model, Status.APPROVED, user);
                            System.out.println("Id:" + process.getId() + "- Amount:" + process.getAmount() + "- Description:" + process.getDescription() + "- Author:" + process.getAuthor().getUsername() + "- Resolver:" + process.getResolver().getUsername() + "- Status:" + process.getStatus() + "- Reimbursement Type:" + process.getReimbursementType());
                        }else if(processChoice == 2){
                            Reimbursement process = reimbursementService.process(model, Status.DENIED, user);
                            System.out.println("Id:" + process.getId() + "- Amount:" + process.getAmount() + "- Description:" + process.getDescription() + "- Author:" + process.getAuthor().getUsername() + "- Resolver:" + process.getResolver().getUsername() + "- Status:" + process.getStatus() + "- Reimbursement Type:" + process.getReimbursementType());
                        }else if(processChoice == 3){
                            break;
                        }else{
                            System.out.println("Invalid choice");
                        }
                        break;
                    case 2:
                        System.out.println("View All Reimbursements by Status");
                        System.out.println("Choose which status:");
                        System.out.println("1- Pending");
                        System.out.println("2- Approved");
                        System.out.println("3- Denied");
                        int statusChoice = input.nextInt();
                        if(statusChoice == 1){
                            List<Reimbursement> result = reimbursementService.getReimbursementsByStatus(Status.PENDING);
                            for(Reimbursement r : result){
                                System.out.println("Id:" + r.getId() + "- Amount:" + r.getAmount() + "- Description:" + r.getDescription() + "- Author:" + r.getAuthor().getUsername() + "- Resolver:" + r.getResolver().getUsername() + "- Status:" + r.getStatus() + "- Reimbursement Type:" + r.getReimbursementType());
                            }
                        }else if(statusChoice == 2){
                            List<Reimbursement> result = reimbursementService.getReimbursementsByStatus(Status.APPROVED);
                            for(Reimbursement r : result){
                                System.out.println("Id:" + r.getId() + "- Amount:" + r.getAmount() + "- Description:" + r.getDescription() + "- Author:" + r.getAuthor().getUsername() + "- Resolver:" + r.getResolver().getUsername() + "- Status:" + r.getStatus() + "- Reimbursement Type:" + r.getReimbursementType());
                            }
                        }else if(statusChoice == 3){
                            List<Reimbursement> result = reimbursementService.getReimbursementsByStatus(Status.DENIED);
                            for(Reimbursement r : result){
                                System.out.println("Id:" + r.getId() + "- Amount:" + r.getAmount() + "- Description:" + r.getDescription() + "- Author:" + r.getAuthor().getUsername() + "- Resolver:" + r.getResolver().getUsername() + "- Status:" + r.getStatus() + "- Reimbursement Type:" + r.getReimbursementType());
                            }
                        }else{
                            System.out.println("Not a choice");
                        }

                        break;
                    case 3:
                        System.out.println("View All Reimbursements");
                        List<Reimbursement> result = reimbursementService.getAllReimbursements();
                        for(Reimbursement r : result){
                            System.out.println("Id:" + r.getId() + "- Amount:" + r.getAmount() + "- Description:" + r.getDescription() + "- Author:" + r.getAuthor().getUsername() + "- Resolver:" + r.getResolver().getUsername() + "- Status:" + r.getStatus() + "- Reimbursement Type:" + r.getReimbursementType());
                        }
                        break;
                    case 4:
                        System.out.println("View all your processed reimbursements");
                        List<Reimbursement> history = reimbursementService.getByResolver2(user);
                        for(Reimbursement r : history){
                            System.out.println("Id:" + r.getId() + "- Amount:" + r.getAmount() + "- Description:" + r.getDescription() + "- Author:" + r.getAuthor().getUsername() + "- Resolver:" + r.getResolver().getUsername() + "- Status:" + r.getStatus() + "- Reimbursement Type:" + r.getReimbursementType());
                        }
                        break;
                    case 5:
                        System.out.println("Edit my account");
                        System.out.println(userService.getById(user.getId()).getFirst_name());
                        System.out.println(userService.getById(user.getId()).getLast_name());
                        System.out.println(userService.getById(user.getId()).getEmail());
                        System.out.println(userService.getById(user.getId()).getPhone());
                        System.out.println(userService.getById(user.getId()).getUsername());
                        System.out.println(userService.getById(user.getId()).getPassword());
                        System.out.println("What would you like to edit?");

                        System.out.println("1 - Change First Name");
                        System.out.println("2 - Change Last Name");
                        System.out.println("3 - Change Email");
                        System.out.println("4 - Change Phone");
                        System.out.println("5 - Change Username");
                        System.out.println("6 - Change Password");
                        System.out.println("7 - Cancel");
                        int userChange = input.nextInt();
                        switch (userChange){
                            case 1:
                                System.out.println("Enter new first name:");
                                String newFirstName = input.next();
                                user.setFirst_name(newFirstName);
                                break;
                            case 2:
                                System.out.println("Enter new last name:");
                                String newLastName = input.next();
                                user.setLast_name(newLastName);
                                break;
                            case 3:
                                System.out.println("Enter new email:");
                                String newEmail = input.next();
                                user.setEmail(newEmail);
                                break;
                            case 4:
                                System.out.println("Enter new Phone:");
                                String newPhone = input.next();
                                user.setPhone(newPhone);
                                break;
                            case 5:
                                System.out.println("Enter new username:");
                                String newUsername = input.next();
                                // test if username is taken
                                UserDAO userDao = new UserDAO();
                                List<String> usernames = userDao.getAllUsernames();
                                if(usernames.contains(newUsername)){
                                    System.out.println("Username already taken");
                                    throw new UsernameNotUniqueException("Username already taken");
                                }else{
                                    user.setPhone(newUsername);
                                }
                                break;
                            case 6:
                                System.out.println("Enter new Password:");
                                String newPassword = input.next();
                                user.setPassword(newPassword);
                                break;
                            case 7:
                                System.out.println("Cancel");
                                break;
                            default:
                                System.out.println("Invalid Input");
                        }
                        userService.updateUser(user);

                        break;
                    case 6:
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
        System.out.println("1 - Process Reimbursements");
        System.out.println("2 - View All Request by Status");
        System.out.println("3 - View All Reimbursements");
        System.out.println("4 - View All Reimbursements you processed");
        System.out.println("5 - Edit my account");
        System.out.println("6 - Log out");
    }


}
