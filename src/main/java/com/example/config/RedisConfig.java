package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisConfig {

	 //Creating Connection with Redis
	   @Bean
	   public RedisConnectionFactory redisConnectionFactory() {
	       return new LettuceConnectionFactory();
	   }
	   
//	   //Creating RedisTemplate for Entity 'Employee'
//	   @Bean
//	   public RedisTemplate<String, Employee> redisTemplate(){
//	      RedisTemplate<String, Employee> empTemplate = new RedisTemplate<>();
//	      empTemplate.setConnectionFactory(redisConnectionFactory());
//	      return empTemplate;
//	   }
}
