package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.securityConfig.JwtUtil;

@RestController 
@RequestMapping("/auth")
public class AuthController // class handles authentication-related operations
{    
	@Autowired
     private UserRepository repo;  // UserRepo is a JPA repository , Used to interact with the database
     
     private JwtUtil jwtUtil = new JwtUtil();
     @PostMapping("/signup")
     public User signup(@RequestBody User user) // signup
     {
    	 return repo.save(user); // saves  the user in the database
     }
     
     @PostMapping("/login")
     public String login(@RequestBody User user) // login
     {
    	 User dbUser = repo.findByEmail(user.getEmail());
    	 
    	 if(dbUser != null && dbUser.getPassword().equals(user.getPassword()))
    	 {
    		 return jwtUtil.generateToken(dbUser.getEmail(), dbUser.getRole().name());
    	 }
    	 
    	 return "Invalid Credentials";
    	 
     }
     
}

