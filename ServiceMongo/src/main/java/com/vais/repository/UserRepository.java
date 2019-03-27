package com.vais.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vais.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
	User findByEmail(String email);

	List<User> findByNameLike(String name);

	List<User> findByDeletedNot(boolean delete);

	void deleteByEmail(String email);
}
