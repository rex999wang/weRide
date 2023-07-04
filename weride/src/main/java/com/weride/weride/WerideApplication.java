package com.weride.weride;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.weride.weride.model.*;
import com.weride.weride.repository.UserRepository;


@SpringBootApplication
public class WerideApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WerideApplication.class, args);
	}

}

//TRY
@Component
class UserCommandLineRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running CommandLineRunner");

		for (User user : this.userRepository.findAll()) {
			System.out.println(user.toString());
		}	
	}

	@Autowired 
	UserRepository userRepository;
}
