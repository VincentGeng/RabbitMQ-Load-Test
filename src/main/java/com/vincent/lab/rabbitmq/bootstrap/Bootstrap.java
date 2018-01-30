/**
 * 
 */
package com.vincent.lab.rabbitmq.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vincent.lab.rabbitmq.service.UserService;

/**
 * @author Vincent
 *
 * Created on 30 Jan 2018
 */
@Component
public class Bootstrap implements CommandLineRunner {

	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	
	private UserService userService;
	
	public Bootstrap(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void run(String... args) throws Exception {
		userService.createDummyUsers();
	}

}
