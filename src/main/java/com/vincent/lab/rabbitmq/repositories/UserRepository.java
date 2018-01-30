/**
 * 
 */
package com.vincent.lab.rabbitmq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vincent.lab.rabbitmq.domain.User;

/**
 * @author Vincent
 *
 * Created on 30 Jan 2018
 */

public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * @param name
	 * @return
	 */
	User findByName(String name);
	
}
