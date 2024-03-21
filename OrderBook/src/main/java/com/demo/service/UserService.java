package com.demo.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.model.Orderbook;
import com.demo.model.UserEntity;
import com.demo.repository.OrderbookRepo;
import com.demo.repository.UserRepo;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepo repo;

	@Autowired
	private OrderbookRepo orderbookRepo;

	@Autowired
	private EntityManager entityManager;

	public UserEntity findByUsername(String username) {

		return repo.findByUsername(username);

	}

	public UserEntity save(UserEntity user) {

		return repo.save(user);

	}

	public ResponseEntity<String> validateCredentials(UserEntity userentity, String requiredRole) {

		UserEntity user = repo.findByLoginIdAndPassword(userentity.getLoginId(), userentity.getPassword());

		if (user != null) {

			if (user.getRole().equals(requiredRole)) {

				return ResponseEntity.ok("user logged in successfully");

			} else {

				return ResponseEntity.badRequest().body("Access denied");

			}

		} else {

			return ResponseEntity.badRequest().body("invalid username or password");

		}

	}

	private boolean isRoleAllowed(String userRole) {

		List<String> allowedRoles = Arrays.asList("PM", "ADMIN");

		return allowedRoles.contains(userRole);

	}

	public ResponseEntity<String> logout(UserEntity user) {

		user.setLoggedIn(false);

		repo.save(user);

		return ResponseEntity.ok("User logged out successfully");

	}

	public List<Orderbook> getOrderbookData(String loginId) {
		UserEntity user = repo.findByLoginId(loginId);
		if (user != null && user.getRole().equals("admin")) {
			return orderbookRepo.findAll();
		} else {
			return orderbookRepo.findLikePGM(user.getUsername());
		}
	}
}