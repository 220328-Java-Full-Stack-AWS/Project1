package com.revature.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.models.Role;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;

public class ReimbursementServiceTest {

	private static ReimbursementService reimbursementService;
	private static ReimbursementDAO reimbursementDAO;
	private static UserService userService;
	public static User employee;
	public static User finance;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reimbursementService = new ReimbursementService();
		reimbursementDAO = new ReimbursementDAO();
		userService = new UserService();
	}
	
	@Before
	public void setUp() throws Exception {
		employee = userService.getById(1); // A employee
		finance = userService.getById(27); // A finance manager
	}

	@Test
	public void testGetAllReimbursements(){
		List<Reimbursement> test = reimbursementService.getAllReimbursements();
		assertNotNull(test);
	}

	@Test
	public void testGetAllReimbursementsByStatus(){
		List<Reimbursement> test = reimbursementService.getReimbursementsByStatus(Status.PENDING);
		assertNotNull(test);
	}

	@Test
	public void testGetByAuthor(){
		List<Reimbursement> test = reimbursementService.getByAuthor(employee);
		assertNotNull(test);
	}

	@Test
	public void testNewRequestReimbursement(){
		Reimbursement test = new Reimbursement(0, Status.PENDING, employee, null, 15.00);
       	Reimbursement created = reimbursementService.NewRequest(test, "This is a test", 6);
		assertNotNull(created);
	}

	@Test
	public void unprocessedReimbursement(){
		Reimbursement test = reimbursementService.getById(41);
		Reimbursement processed = reimbursementService.process(test, Status.APPROVED, finance);
		assertEquals(processed.getStatus(), Status.APPROVED);
	}

	@Test
	public void testDeleteReimbursement(){
		reimbursementService.deleteReimbursement(41); // the id of the test data
		Reimbursement test = reimbursementService.getById(41);
		assertEquals(test.getId(),0); // if 0 = does not exist;
	}

}
