package com.analyzer.ms.co2analyzer.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.analyzer.ms.co2analyzer.entity.SensorMeasurement;
import com.analyzer.ms.co2analyzer.repository.SensorMeasurementRepository;

@Component
public class SensorStatusServiceImpl implements SensorStatusService {

	@Autowired
	private SensorMeasurementRepository measurementRepo;
	
	@Override
	public String getSensorStatus(String uuid) {
		List<SensorMeasurement> measurements =  measurementRepo.findByUuid(uuid);
		Optional<SensorMeasurement> measurement = null;
		if(measurements != null  && !measurements.isEmpty()) {
			measurement = measurements.stream().sorted(Comparator.comparing(SensorMeasurement::getTime, Comparator.nullsLast(Comparator.reverseOrder()))).findFirst();
		}
		if(measurement.isPresent()) {
			return measurement.get().getStatus();
		}
		return null;
	}

}
