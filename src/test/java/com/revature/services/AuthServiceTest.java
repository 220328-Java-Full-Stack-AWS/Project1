package com.revature.services;

import com.revature.exceptions.LoginUnsuccessfulException;
import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.models.Role;
import org.junit.BeforeClass;

import com.revature.models.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class AuthServiceTest {

	private static User GENERIC_EMPLOYEE_1;
	private static UserService userService;
	private static AuthService authService;

	@BeforeClass
	public static void setUpClass(){
		authService = new AuthService();
		userService = new UserService();
		GENERIC_EMPLOYEE_1 = new User(1, "genericEmployee1", "genericPassword", "genericFirstname", "genericLastname", "genericEmail", "genericPhone", Role.EMPLOYEE);
	}

	@Test
	public void loginPass(){
		User user1 = userService.getById(1);
		assertEquals(user1, authService.login("leobarrientos02@gmail.com", "Chente72"));
	}

	@Test(expected = LoginUnsuccessfulException.class)
	public void testWrongEmail(){
		authService.login("wrongEmail@gmail.com", "password");
	}

	@Test(expected = LoginUnsuccessfulException.class)
	public void testWrongPassword(){
		authService.login("leobarrientos02@gmail.com", "password");
	}

	// Registration test success

	// Registration test failures
	@Test(expected = UsernameNotUniqueException.class)
	public void testNonUniqueUsername(){
		// get a user from the database
		User user1 = userService.getById(1);
		// create a new user
		User newUser = new User();
		// set newUser's username to user1's username which would be invalid
		newUser.setUsername(user1.getUsername());
		authService.register(newUser);
	}

	@Test
	public void testRegisterSuccess(){
		authService.register(GENERIC_EMPLOYEE_1);
		User test = userService.getByEmail(GENERIC_EMPLOYEE_1.getEmail());
		assertEquals(test.getEmail(), GENERIC_EMPLOYEE_1.getEmail());
	}

}
