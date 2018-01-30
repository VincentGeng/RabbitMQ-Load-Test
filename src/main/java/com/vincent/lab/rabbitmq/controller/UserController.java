/**
 * 
 */
package com.vincent.lab.rabbitmq.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vincent.lab.rabbitmq.service.UserService;

/**
 * @author Vincent
 *
 * Created on 30 Jan 2018
 */

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {
	public static final String BASE_URL = "/users";
	
	private final UserService userSerive;

	public UserController(UserService userSerive) {
		this.userSerive = userSerive;
	}
	
}
