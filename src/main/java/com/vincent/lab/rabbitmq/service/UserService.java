/**
 * 
 */
package com.vincent.lab.rabbitmq.service;

/**
 * @author Vincent
 *
 * Created on 30 Jan 2018
 */
public interface UserService {
	
	void createDummyUsers();
	
	void deleteAllUsers();
	
	void getUserByNameUsingRabbitMQ(String name);
	
	void getUserByName(String name);
	
}
