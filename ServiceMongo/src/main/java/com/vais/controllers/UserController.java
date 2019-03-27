package com.vais.controllers;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vais.entities.User;
import com.vais.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping(value = "/user/add")
	public void addUser(@RequestBody User theUser) {
		if (userRepository.findByEmail(theUser.getEmail()) == null) {
			userRepository.insert(theUser);
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		System.out.println("-------------Doing something------------------");
		users.forEach(user -> System.out.println(user.toString()));

		return users;
	}

	@RequestMapping(value = "/user/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response getUserByEmail(@PathVariable String email) {
		User user = userRepository.findByEmail(email);
		if (user != null && !user.isDeleted()) {
			return Response.status(Response.Status.OK).entity(user).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@RequestMapping(value = "/user/{email}", method = RequestMethod.POST)
	public void deleteUser(@PathVariable String email) {
		userRepository.deleteByEmail(email);
	}
}
