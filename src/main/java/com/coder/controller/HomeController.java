package com.coder.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.coder.entity.User;
import com.coder.repository.UserRepository;
import com.coder.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
	@GetMapping("/user/profile")
	public String profile(Principal p , Model m) {
		String email = p.getName();
		User user = userRepo.findByEmail(email);
		m.addAttribute("user", user);
		return "profile";
	}
	
	@GetMapping("/user/home")
	public String home() {
		return "home";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user,HttpSession session , Model m) {
		
		User saveUser = userService.saveUser(user);
		if(saveUser != null) {
			session.setAttribute("msg", "Register Successsfully");
		}else {
			session.setAttribute("msg", "Something went Wrong");
		}
		return "redirect:/register";
	}
}
