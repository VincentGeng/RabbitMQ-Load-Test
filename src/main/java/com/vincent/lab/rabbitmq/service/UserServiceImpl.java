/**
 * 
 */
package com.vincent.lab.rabbitmq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.vincent.lab.rabbitmq.domain.User;
import com.vincent.lab.rabbitmq.repositories.UserRepository;

/**
 * @author Vincent
 *
 * Created on 30 Jan 2018
 */
@Service

public class UserServiceImpl implements UserService {
	
	public static final int TOTAL_USERS_NUMBER = 200000;
	public static final int BATCH_USERS_NUMBER = 200;
	
	private final UserRepository userRepository;
	
	private AmqpTemplate rabbitTemplate;
	
	public UserServiceImpl(UserRepository userRepository, AmqpTemplate rabbitTemplate) {
		this.userRepository = userRepository;
		this.rabbitTemplate = rabbitTemplate;
	}

	/* (non-Javadoc)
	 * @see com.vincent.lab.rabbitmq.service.UserService#createDummyUsers()
	 */
	@Override
	public void createDummyUsers() {
		if(userRepository.findAll().size()==0) {
			for(int k=0; k<TOTAL_USERS_NUMBER/BATCH_USERS_NUMBER; k++) {
				List<User> dummyUserList = new ArrayList<>();
				for(int i=0; i<BATCH_USERS_NUMBER; i++) {
					int number = k*BATCH_USERS_NUMBER+i+1;
					User dummyUser = new User("Vincent"+number, "Vincent"+number);
					dummyUserList.add(dummyUser);
				}
				userRepository.saveAll(dummyUserList);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.vincent.lab.rabbitmq.service.UserService#getUserByNameProducer(java.lang.String)
	 */
	@Override
	public void getUserByNameProducer(String name) {
		this.rabbitTemplate.convertAndSend("loadtest", name);
	}

	/* (non-Javadoc)
	 * @see com.vincent.lab.rabbitmq.service.UserService#getUserByNameConsumer(java.lang.String)
	 */
	@RabbitListener(queues = "loadtest")
	@RabbitHandler
	@Override
	public void getUserByNameConsumer(String name) {
		System.out.println(userRepository.findByName(name).toString());
	}

	/* (non-Javadoc)
	 * @see com.vincent.lab.rabbitmq.service.UserService#getUserByName(java.lang.String)
	 */
	@Override
	public void getUserByName(String name) {
		System.out.println(userRepository.findByName(name).toString());
	}

	/* (non-Javadoc)
	 * @see com.vincent.lab.rabbitmq.service.UserService#deleteAllUsers()
	 */
	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}

}
