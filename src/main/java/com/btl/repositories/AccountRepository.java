package com.btl.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.btl.entities.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	Optional<Account> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	Optional<Account> findByEmail(String email);
	
	
}