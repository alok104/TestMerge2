package com.analyzer.ms.co2analyzer.cache;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.analyzer.ms.co2analyzer.model.SensorRequestVO;

@Service
public class SensorCache {
	
	@Autowired
	@Qualifier(value = "redisTemplate")
	RedisTemplate<String, Object> redisTemplate;
	
	private HashOperations<String, String, SensorRequestVO> hashOps;
	
	@PostConstruct
	public void init() {
		hashOps = redisTemplate.opsForHash();
	}
	
	public void save(SensorRequestVO requestVO) {
		hashOps.put(requestVO.getUuid(), requestVO.getUuid(), requestVO);
		redisTemplate.expire(requestVO.getUuid(), 60, TimeUnit.SECONDS);
	}
	
	public SensorRequestVO find(String uuid) {
		return hashOps.get(uuid, uuid);
	}
	

}
