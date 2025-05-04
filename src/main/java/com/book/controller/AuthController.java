package com.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.entity.User;
import com.book.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import java.util.*;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Needed if testing HTML from filesystem
public class AuthController {

	@Autowired
	private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
    
    
    
    
    
    
    
    
//    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@RequestBody User user) {
//        if (userRepository.findByUsername(user.getUsername()) != null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
//        }
//
//        // Encode the password before saving
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        
//        return ResponseEntity.ok("Signup successful");
//    }
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user) {
//        System.out.println(">>> /login endpoint HIT with username: " + user.getUsername());
//        User dbUser = userRepository.findByUsername(user.getUsername());
//
//        if (dbUser != null && passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
//            return ResponseEntity.ok("Login successful");
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }

   


//        @PostMapping("/signup")
//        public ResponseEntity<String> signup(@RequestBody User user) {
//            if (userRepository.findByUsername(user.getUsername()) != null) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
//            }
//
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userRepository.save(user);
//            
//            return ResponseEntity.ok("Signup successful");
//        }
        
        // Remove the login and logout methods as they're handled by Spring Security
    
//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(@RequestBody User user) {
//        if (loggedInUsers.contains(user.getUsername())) {
//            loggedInUsers.remove(user.getUsername());
//            return ResponseEntity.ok("Logged out successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not logged in");
//        }
//    }

//  @PostMapping("/login")
//  public ResponseEntity<String> login(@RequestBody User user) {
//      User dbUser = userRepository.findByUsername(user.getUsername());
//      System.out.println("hi login");
//      if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
//          return ResponseEntity.ok("Login successful");
//      }
//      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//  }
}
