package com.learn.firstrestapi.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetails, Long> {
	public List<UserDetails> findByRole(String role);

	// we are just declaring the methods,
	// and the name of the attribute, which we are using should match with the
	// parameter in UserDetails class
	// i.e - findByRole - Role string should match with "role" in UserDetails
}
