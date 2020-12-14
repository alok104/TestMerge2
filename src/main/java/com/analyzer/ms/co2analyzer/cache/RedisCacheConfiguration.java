package com.analyzer.ms.co2analyzer.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisCacheConfiguration {
	@Value(value = "${spring.redis.hostname}")
	private String hostname;
	
	@Value(value = "${spring.redis.port}")
	private Integer port;
	
	@Value(value = "${spring.redis.password}")
	private char[] password;
	
	@Bean(name = "jedisConnectionFactory")
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(hostname, port);
		redisStandaloneConfiguration.setPassword(password);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	 @Bean(name="redisTemplate")
	 public RedisTemplate<String, Object> getRedisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

}
