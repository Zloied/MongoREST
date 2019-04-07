package com.vais.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.vais.entities.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void insert(User user) {
		Query query = new Query(Criteria.where("email").is(user.getEmail()));
		User userResult = mongoTemplate.findOne(query, User.class);
		if (userResult == null) {
			mongoTemplate.insert(user, "User");
		}
	}

	@Override
	public User findByEmail(String email) {

		Query query = new Query(Criteria.where("email").is(email).and("deleted").is(false));
		User userResult = mongoTemplate.findOne(query, User.class);
		return userResult;
	}

	@Override
	public List<User> findByNameLike(String name) {

		Query query = new Query(Criteria.where("name").regex(name).and("deleted").is(false));
		List<User> userList = mongoTemplate.find(query, User.class);
		return userList;
	}

	@Override
	public List<User> findAll() {

		Query query = new Query(Criteria.where("deleted").is(false));
		List<User> userList = mongoTemplate.find(query, User.class);
		return userList;
	}

	@Override
	public void deleteByEmail(String email) {

		Query query = new Query(Criteria.where("email").is(email));
		Update update = new Update();
		update.set("deleted", true);

		this.mongoTemplate.updateFirst(query, update, User.class);

	}

}
