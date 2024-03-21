package com.demo.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.model.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	UserEntity findByUsername(String username);

	UserEntity findByLoginIdAndPassword(String loginId, String password);

	UserEntity findByLoginId(String loginId);

}