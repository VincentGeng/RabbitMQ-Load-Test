package com.vincent.lab;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vincent.lab.rabbitmq.service.UserService;

/**
 * @author Vincent
 *
 * Created on 30 Jan 2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RabbitMqLoadTestApplication.class)
public class RabbitMQLoadTest {
	
	final static int CONCURRENT_LOGIN_USERS = 2000;
	
	@Autowired
    private UserService userService;

	@Test
	public void test() {
		
		final CountDownLatch concurrentThreadsLatch = new CountDownLatch(1);
		final CountDownLatch mainThreadLatch = new CountDownLatch(CONCURRENT_LOGIN_USERS);
        
        final ExecutorService executorService = Executors.newFixedThreadPool(CONCURRENT_LOGIN_USERS);
        
        for(int i=1; i<=CONCURRENT_LOGIN_USERS; i++){
        	final int number = i;
            executorService.submit(new Runnable() {
                public void run() {
                    try {
                    	concurrentThreadsLatch.await();
                    	userService.getUserByNameUsingRabbitMQ("Vincent" + number);
                    } catch (InterruptedException e) {
            			e.printStackTrace();
            		} finally{
                        mainThreadLatch.countDown();
                    }
                }
            });
        }
        
        concurrentThreadsLatch.countDown();
        try {
			mainThreadLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        executorService.shutdown();
	}

}
