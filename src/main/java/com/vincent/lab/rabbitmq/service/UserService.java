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
	
	void getUserByNameProducer(String name);
	
	void getUserByNameConsumer(String name);
	
	void getUserByName(String name);
	
}
