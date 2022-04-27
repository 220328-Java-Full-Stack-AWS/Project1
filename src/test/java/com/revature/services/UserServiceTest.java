package com.revature.services;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.revature.models.Role;
import com.revature.repositories.UserDAO;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.models.User;

public class UserServiceTest {

    private static UserService userService;
    private static UserDAO userDAO;

    private User GENERIC_EMPLOYEE_1;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        userService = new UserService();
        userDAO = new UserDAO();
    }

    @Before
    public void setUp() throws Exception {
        GENERIC_EMPLOYEE_1 = new User(1, "genericEmployee1", "genericPassword", "genericFirstname", "genericLastname", "genericEmail", "genericPhone", Role.EMPLOYEE);
    }

    @Test
    public void testGetByEmailPassesWhenEmailExists() {
        User user = userService.getByEmail("leobarrientos02@gmail.com");
        assertNotNull(user);
    }

    @Test
    public void testGetByEmailPassesWhenEmailDoesNotExists() {
        User user = userService.getByEmail("fake@email.com");
        // will return a user with an id of 0 and all fields null
        assertEquals(0, user.getId() );
        assertEquals(null, user.getUsername());
    }

    @Test //deletes the test data from the database
    public void testDelete(){
        // get the test user
        User test = userService.getByEmail(GENERIC_EMPLOYEE_1.getEmail());
        // delete the test user by their id
        userService.deleteUser(test.getId());
    }

    @Test
    public void updateUserRole(){
        // get test data
        User test = userService.getByEmail(GENERIC_EMPLOYEE_1.getEmail());
        // uses the id and the role wanted(1=Employee & 2=Finance)
        userService.updateUserRole(test.getId(), 2);

        User updatedUser = userService.getByEmail(GENERIC_EMPLOYEE_1.getEmail());
        assertEquals(updatedUser.getRole(), Role.FINANCE_MANAGER);
    }

    @Test
    public void updateUserTest(){
        User test = userService.getByEmail(GENERIC_EMPLOYEE_1.getEmail());
        test.setUsername("testPassed");
        User updatedUser = userService.updateUser(test);
        assertNotNull(updatedUser);
    }
}
