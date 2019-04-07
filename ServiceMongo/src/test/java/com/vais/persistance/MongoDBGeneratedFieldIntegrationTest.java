package com.vais.persistance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.vais.entities.User;
import com.vais.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDBGeneratedFieldIntegrationTest {

	@Autowired
	UserRepository userRepository;
	@Autowired
	MongoTemplate mongoTemplate;

	@Test
	public void contextLoads() {

	}

	@After
	public void tearDown() throws Exception {
		mongoTemplate.dropCollection(User.class);
	}

	@Test
	public void createGivenUserObject_whenSave() {

		// creating User entity and saving it in DB
		String email = "tom@mail.com";
		User user = new User("Tom", email, "Wall street", false);
		userRepository.insert(user);

		assertThat(userRepository.findAll().size()).isGreaterThan(0);
		assertEquals(user, userRepository.findByEmail(email));
	}

	@Test
	public void retrieveNotDeletedUsers() {

		// creating User entities
		User tom = new User("Tom", "tom@mail.com", "Wall street", false);
		User john = new User("John", "john@mail.com", "Barnaby str.", true);
		User bob = new User("Bob", "bob@mail.com", "South beach str.", false);

		userRepository.insert(tom);
		userRepository.insert(john);
		userRepository.insert(bob);

		assertThat(userRepository.findAll().size()).isEqualTo(2);

	}

	@Test
	public void deleteUser() {

		// creating User entities
		User tom = new User("Tom", "tom@mail.com", "Wall street", false);
		User john = new User("John", "john@mail.com", "Barnaby str.", false);

		userRepository.insert(tom);
		userRepository.insert(john);
		userRepository.deleteByEmail(john.getEmail());

		assertThat(userRepository.findAll().size()).isEqualTo(1);
	}

}
