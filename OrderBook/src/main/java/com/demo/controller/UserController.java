package com.demo.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Orderbook;
import com.demo.model.UserEntity;
import com.demo.service.OrderbookService;
import com.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private OrderbookService service;

	@Autowired
	public UserController(UserService service) {
		this.userService = service;
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserEntity user) {
		UserEntity exsistingUser = userService.findByUsername(user.getUsername());
		if (exsistingUser != null) {
			return ResponseEntity.badRequest().body("user already exists");
		}
		userService.save(user);
		return ResponseEntity.ok("user registered successfully");
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/login")
	public ResponseEntity<String> validateCredentials(@RequestBody UserEntity user,
			@RequestParam(required = false) String requiredRole) {
		ResponseEntity<String> response = userService.validateCredentials(user, requiredRole);
		return response;
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestBody UserEntity user) {
		return userService.logout(user);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/api/orderbook/data")
	public ResponseEntity<List<Orderbook>> getOrderbookData(@RequestParam String loginId) {
		List<Orderbook> orders = userService.getOrderbookData(loginId);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
}
