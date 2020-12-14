package com.analyzer.ms.co2analyzer.service;

import java.time.LocalDate;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.analyzer.ms.co2analyzer.cache.SensorCache;
import com.analyzer.ms.co2analyzer.entity.SensorMeasurement;
import com.analyzer.ms.co2analyzer.exception.Co2AnalyzerException;
import com.analyzer.ms.co2analyzer.model.SensorRequest;
import com.analyzer.ms.co2analyzer.model.SensorRequestVO;
import com.analyzer.ms.co2analyzer.repository.SensorMeasurementRepository;
import com.analyzer.ms.co2analyzer.utility.SensorStatusMeasurementUtil;

@Component
public class SensorCollectServiceImpl implements SensorCollectService {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private SensorMeasurementRepository measurementRepo;
	
	@Autowired
	private SensorCache sensorCache;
	
	@Override
	public void collectSensor(SensorRequest sensorRequest,String uuid) {
		LOG.info("Request received for collectSensor with uuid, {}", uuid);
		validateRequest(sensorRequest,uuid);
		
		//Check if sensor measurement entry already there for same UUID within last one minute, ignore
		if(sensorCache.find(uuid) == null) {
			SensorRequestVO requestVO = mapToSensorVo(sensorRequest,uuid);
			saveSensor(prepareSensorMeasurementBody(requestVO));
			addToCache(requestVO);
		}
		
	}
	
	private void validateRequest(SensorRequest sensorRequest, String uuid) {
		if(StringUtils.isEmpty(uuid)) {
			throw new Co2AnalyzerException("Invalid UUID");
		}
		
		if(Objects.isNull(sensorRequest.getCo2())) {
			throw new Co2AnalyzerException("Invalid co2");
		}
		
		if(Objects.isNull(sensorRequest.getTime())) {
			throw new Co2AnalyzerException("Invalid time");
		}
		
	}

	private SensorMeasurement prepareSensorMeasurementBody(SensorRequestVO requestVO) {
		SensorMeasurement measurement = new SensorMeasurement();
		
		//Set status based on co2 level
		measurement.setStatus(SensorStatusMeasurementUtil.measureStatusByCo2(requestVO.getCo2()).name());
		measurement.setTime(requestVO.getTime());
		measurement.setCo2(requestVO.getCo2());
		measurement.setUuid(requestVO.getUuid());
		measurement.setCreationDate(LocalDate.now());
		return measurement;
	}
	
	private void addToCache(SensorRequestVO requestVO) {
		sensorCache.save(requestVO);
		
	}
	private void saveSensor(SensorMeasurement sensorMeasurement) {
		measurementRepo.save(sensorMeasurement);
		
	}
	

}
