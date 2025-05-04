package com.book.repository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.book.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
}
