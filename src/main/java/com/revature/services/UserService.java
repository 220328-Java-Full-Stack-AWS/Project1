package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.models.User;
import com.revature.repositories.UserDAO;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {

	UserDAO dao = new UserDAO();
	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
	public Optional<User> getByUsername(String username) {
		User user = dao.getByUsername(username);
		boolean found = true;
		if(found){
			return Optional.of(user);
		}else{
			return Optional.empty();
		}
	}

	/**
	 * Creates a new User
	 * @param user - user object that gets all the data needed
	 * @return A user that has been registered to the system
	 */
	public User create(User user){
		return dao.create(user);
	}

	/**
	 * Update a user information.
	 * @param user - User to be updated.
	 * @return The updated user information.
	 */
	public User updateUser(User user){
		dao.update(user);
		User updatedUser = dao.getById(user.getId());
		return updatedUser;
	}

	public void update(User user){
		dao.update(user);
	}

	public void updateUserRole(int id, int role){
		dao.updateUserRole2(id, role);
	}


	/**
	 * Get a user by their id.
	 * @param id - the user id.
	 * @return The user with the associated id.
	 */
	public User getById(int id){
		User user = dao.getById(id);
		return user;
	}

	/**
	 * Get a user by their email.
	 * @param Email - the user's email.
	 * @return A user associated with the email.
	 */
	public User getByEmail(String Email){
		User user = dao.getUserByEmail(Email);
		return user;
	}

	/**
	 * This returns all users in the database.
	 * @return All users
	 */
	public List<User> getAllUsers(){
		List<User> allUsers = dao.getAllUsers();
		return allUsers;
	}

	public void deleteUser(int id){
		dao.deleteUser(id);
	}


}
