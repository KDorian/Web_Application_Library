package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.User;
import com.example.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getUserPage(Model model) {
		
		List<User> users = userService.findAll();
		
		model.addAttribute("usersList", users);
		
		return "users";
	}
	
	@RequestMapping(value = "/create-user", method = RequestMethod.GET)
	public String getUserForm() {
		return "user-create";
	}
	
	@RequestMapping(value = "/create-user", method = RequestMethod.POST)
	public String saveUser(@RequestParam(required = false) Long id,
						  @RequestParam(name="firstName", required = true) String firstName,
						  @RequestParam(name="lastName", required = true) String lastName,
						  @RequestParam(name="email", required = true) String email,
						  @RequestParam(name="password", required = true) String password
						  ) {
		
		User user = new User(firstName, lastName, email, password);
		userService.save(user);
		
		return "redirect:/users";
		
	}
	
	
	//usuniecie usera na podstawie przekazanego w urlu id
	@RequestMapping(value = "/users/delete/{id}", method = RequestMethod.POST)
	public String deleteUser(@PathVariable Long id) {
		userService.delete(id);
		return "redirect:/users";
	}
	
	
	//edycja usera na podstawie przekazanego w urlu id
	@RequestMapping(value = "/users/delete/{id}", method = RequestMethod.POST)
	public String getEditUserPage(@PathVariable Long id, Model model) {
		User user = userService.findOne(id);
		
		model.addAttribute("user", user);
		
		return "user-create";  //przekierowanie na strone do edycji
	}

}
