package com.analyzer.ms.co2analyzer.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.analyzer.ms.co2analyzer.entity.SensorMeasurement;

public interface SensorMeasurementRepository extends MongoRepository<SensorMeasurement, String> {
	public List<SensorMeasurement> findByUuid(String uuid);
}
