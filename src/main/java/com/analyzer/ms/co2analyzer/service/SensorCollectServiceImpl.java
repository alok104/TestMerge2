package com.analyzer.ms.co2analyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.analyzer.ms.co2analyzer.entity.SensorMeasurement;
import com.analyzer.ms.co2analyzer.model.SensorRequest;
import com.analyzer.ms.co2analyzer.repository.SensorMeasurementRepository;
import com.analyzer.ms.co2analyzer.utility.SensorStatusMeasurementUtil;

@Component
public class SensorCollectServiceImpl implements SensorCollectService {
	
	@Autowired
	private SensorMeasurementRepository measurementRepo;
	
	@Override
	public void collectSensor(SensorRequest sensorRequest,String uuid) {
		saveSensor(prepareSensorMeasurementBody(sensorRequest,uuid));
		
	}
	private void saveSensor(SensorMeasurement sensorMeasurement) {
		measurementRepo.save(sensorMeasurement);
		
	}
	private SensorMeasurement prepareSensorMeasurementBody(SensorRequest sensorRequest, String uuid) {
		SensorMeasurement measurement = new SensorMeasurement();
		measurement.setStatus(SensorStatusMeasurementUtil.measureStatusByCo2(sensorRequest.getCo2()).name());
		measurement.setTime(sensorRequest.getTime());
		measurement.setCo2(sensorRequest.getCo2());
		measurement.setUuid(uuid);
		return measurement;
	}
	

}
