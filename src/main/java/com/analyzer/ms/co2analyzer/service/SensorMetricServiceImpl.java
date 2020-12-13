package com.analyzer.ms.co2analyzer.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.analyzer.ms.co2analyzer.entity.SensorMeasurement;
import com.analyzer.ms.co2analyzer.model.SensorMetricsResponse;
import com.analyzer.ms.co2analyzer.repository.SensorMeasurementRepository;

@Component
public class SensorMetricServiceImpl implements SensorMetricService {

	@Autowired
	private SensorMeasurementRepository measurementRepo;
	
	@Override
	public SensorMetricsResponse getSensorMetrics(String uuid) {
		List<SensorMeasurement> measurements =  measurementRepo.findByUuid(uuid);
		SensorMetricsResponse measurementMetricsResponse = null;
		Optional<SensorMeasurement> maxSensorMeasurement = null;
		OptionalDouble avgSensorMeasurement = null;
		if(measurements != null  && !measurements.isEmpty()) {
			maxSensorMeasurement = measurements.stream().max(Comparator.comparing(SensorMeasurement::getCo2));
			avgSensorMeasurement = measurements.stream().mapToInt(SensorMeasurement::getCo2).average();
		}
		if(maxSensorMeasurement.isPresent()) {
			measurementMetricsResponse = new SensorMetricsResponse();
			measurementMetricsResponse.setAvgLast30Days(null);
		}
		return null;
	}

}
