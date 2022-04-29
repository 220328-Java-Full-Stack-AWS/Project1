# Project 1 - Employee Reimbursment System (ERS)

## Executive Summary
The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement. The reimbursement types haves the following options: LODGING, FOOD, TRAVEL, ENTERTAINMENT, SHOPPING, and OTHER.

# Tech Stack
 **Backend**
 - Java 8, Apache Maven, PostgreSQL, AWS RDS, Java Servlets, JDBC, and Tomcat.
<div>
  <img src="https://github.com/devicons/devicon/blob/master/icons/java/java-original-wordmark.svg" title="Java" alt="Java" width="40" height="40"/>&nbsp;
   <img src="https://github.com/devicons/devicon/blob/master/icons/apache/apache-original-wordmark.svg" title="Apache" alt="Apache" width="40" height="40"/>&nbsp;
    <img src="https://github.com/devicons/devicon/blob/master/icons/postgresql/postgresql-original.svg" title="PostgreSql"  alt="PostgreSQl" width="40" height="40"/>&nbsp;
   <img src="https://github.com/devicons/devicon/blob/master/icons/amazonwebservices/amazonwebservices-plain-wordmark.svg" title="AWS" **alt="AWS" width="40" height="40"/>
</div>

**Frontend**
 - HTML, CSS, SASS, and JavaScript
<div>
   <img src="https://github.com/devicons/devicon/blob/master/icons/html5/html5-original.svg" title="HTML5" alt="HTML" width="40" height="40"/>&nbsp;
   <img src="https://github.com/devicons/devicon/blob/master/icons/css3/css3-original.svg"  title="CSS" alt="CSS" width="40" height="40"/>&nbsp;
   <img src="https://github.com/devicons/devicon/blob/master/icons/sass/sass-original.svg"  title="SASS" alt="SASS" width="40" height="40"/>&nbsp;
   <img src="https://github.com/devicons/devicon/blob/master/icons/javascript/javascript-original.svg" title="JavaScript" alt="JavaScript" width="40" height="40"/>&
</div>

# User Stories
**Finance Manager** 
 1. Login
    - As a finance manager you can login to an account made for me by the database manager, using my email and password.
 2. View All reimbursements
    - As a finance manager, you can view all reimbursements upload by the company's employee, once you login into your account.
 3. Filter/Sort reimbursements
     - As a finance manager you can filter all reimbursements by it's status, type, and reimbursement's processed by you. In addition, you can sort the reimbursements by the date uploaded, amount, and reimbursement id.
 4. Process reimbursements
    - As a finance manager you process any reimbursemets upload by any employee from the company. This can be done by location the reimbursements you would like to process, press on the reimbursement's process button, which would redirect you to the reimbursement's page. In the reimbursement's page, you can view all information on the reimbursement request and choose to Approved or Deny the reimbursement.
 5. Update user role
     - As a finance manager, you can update an employee's role in the system to a finance manager. This can be done by location the Users page found in the header's dropdown menu. The Users page display all the system's users, you can filter and sort all the user's in the system helping to find users faster. For all employees, there is a Change Role button, which a finance manger can press to update an employee's role. ** Once completed, the finance manager can not change the role back and need to contact the database manager.
 6. Update account
     - As a finance manager, you can update your personal account information by going to the settings page location in the dropdown menu. This can be used to quickly update your private information and not need to contact the database manager to make simple updates. The finance manager can update the full name, email, phone, username, and their password.

**Employee**
 1. Login
      - As a user, you can log in to by existing account by using the email and password during the registration.
 2. Register
      - As a user, you can register to a new account in the Employee Reimbursement System by completing the form on the register page. The registration form uses many input validation to ensure we get valid data.
 3. View your reimbursements
      - As a user, you can view all your reimbursements as soon as you log in to your personal account. If you are a new user, you would not see any reimbursements but would be able to begin making reimbursement requests to the Employee Reimbursement System.
 4. Filter/Sort your reimbursements
      - As a user,you can filter and sort through alll your old and new reimbursement request in the system. The user can view all their filter and sorting option by pressing on the Filter & Sort button, which would display the filter and sort menu. The user is able to filter thier reimbursements by status and type. In addition, the user can sort their reimbursements by reimbursement id, amount, and the date uploaded.
 5. Add reimbursement
      - As a user you can make as much reimbursements request as you want, by navigating to the make add reimbursement page. This can be done by pressing on the Add Reimbursement button located on the top of the page. In this page you can easily make new reimbursement request by completing the form asking for the reimbursement amount, description, and type. Once the form is complete, just click on the Submit button and the new reimbursement request would be up in the system.
 6. Update reimbursement
      - As a user you can update any reimbursements that has not been processed yet by a finance manager. If you want to make edits to a reimbursement request it should be done as soon as possible and it can done on the Update Reimbursment Page. This page can be reached by clicking on the Edit button found on the reimbursement you want to update. In this page you can see more information on your reimbursement request and update the amount, description, and the reimbursement type. Once you make the changes you wish, the update would be completed as soon as you press on the Submit button.
 7. Delete reimbursement
      - As a user you can delete any reimbursements uploaded to the system that has not been processed yet by a Finance Manger. If a finance manager processes your reimbursement, it would be saved into the system for which you would not have the option to delete the reimbursement. If you want to delete a reimbursement request it should be done as soon as possible. You can delete a new reimbursement request on your personal page where each unprocessed reimbursement would have an Edit & Delete button. Simply press on the delete button on the reimbursement would be deleted of our system.
 8. Update account
      - As a user, you can update your personal information such as your full name, email, username, password, and phone number. This can be done by visiting the settings page, which can be reached by the the dropdown menu on the navbar. In the settings page, you can update your personal information by making the updates and pressing on the Submit button. The system does some user input validation still to ensure the user does not enter invalid data.
 9. Delete account
     - As a user, you can delete your account on your own by navigating the settings page, once the account is deleted there would be no way of retrieving the account back.

# Diagrams
**State-chart Diagram (Reimbursement Statuses)** 

![](./imgs/state-chart.jpg)


**Logical Model**

![](./imgs/logical.jpg)

**Physical Model**

![](./imgs/physical.jpg)

**Use Case Diagram**

![](./imgs/use-case.jpg)

**Activity Diagram**

![](./imgs/activity.jpg)

# Employee Reimbursement System(pics)
![alt text](http://url/to/img.png)
