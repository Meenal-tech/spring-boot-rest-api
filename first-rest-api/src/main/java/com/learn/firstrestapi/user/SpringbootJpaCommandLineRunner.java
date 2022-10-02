package com.learn.firstrestapi.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringbootJpaCommandLineRunner implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new UserDetails("Meenal", "developer"));
		userRepository.save(new UserDetails("Ravi", "support"));
		userRepository.save(new UserDetails("Random", "unemployed"));

		List<UserDetails> findByRole = userRepository.findByRole("developer");
		LOGGER.info("the user on finding by role is - " + findByRole.toString());
	}

}
