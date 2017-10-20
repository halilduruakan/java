package com.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springdemo.entity.User;
import com.springdemo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/registration")
	public String showRegistration(Model theModel) {
		User theUser = new User();
		theModel.addAttribute("user", theUser);
		
		return "registration";
	}
	
	@PostMapping("/registration")
	public String register(@ModelAttribute("user") User theUser) {

		
		userService.registerUser(theUser);
		
		return "redirect:/login";
	}
	
	@PostMapping(value = "/registration/isUsernameExist" )
	public @ResponseBody String isUsernameExist(@RequestParam("username") String username) {
		if(userService.userNameExist(username)) {
			return "Username already exist";
		}
		return "";
	}
	
	@PostMapping(value = "/registration/isEmailExist" )
	public @ResponseBody String isEmailExist(@RequestParam("email") String email) {
		if(userService.emailExist(email)) {
			return "Email adress already exist";
		}
		return "";
	}
	
}
