package com.vais.repository;

import java.util.List;

import com.vais.entities.User;

/**
 * 
 * @author Eduard
 *
 *         This is mongo repository with methods for retrieving data from
 *         MongoDb all methods in this class are created via mongo pattern
 */
public interface UserRepository {

	public void insert(User user);

	/**
	 * This methods finds and retrieves a user record from database. Search goes by
	 * user's email . Should retrieve only users not marked as deleted
	 * 
	 * @param email user's email
	 * @return User entity
	 */
	public User findByEmail(String email);

	/**
	 * This method retrieves all users from DB with name matching incoming name
	 * parameter
	 * 
	 * @param name User's name to search
	 * @return List of User entities
	 */
	public List<User> findByNameLike(String name);

	/**
	 * This retrieves all users from database which are not marked as deleted or not
	 * 
	 * @param delete
	 * @return List of User entities
	 */
	public List<User> findAll();

	/**
	 * Sets as deleted a single user record with given email in DB
	 * 
	 * @param email User's email
	 */
	void deleteByEmail(String email);
}
