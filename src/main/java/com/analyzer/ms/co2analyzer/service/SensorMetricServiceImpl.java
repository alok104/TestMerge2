package com.analyzer.ms.co2analyzer.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.analyzer.ms.co2analyzer.entity.SensorMeasurement;
import com.analyzer.ms.co2analyzer.model.SensorMetricsResponse;

@Component
public class SensorMetricServiceImpl implements SensorMetricService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public SensorMetricsResponse getSensorMetrics(String uuid) {
		List<Criteria> criterias = new ArrayList<>();
		Query query = new Query();
		criterias.add(Criteria.where("uuid").is(uuid));
		criterias.add(Criteria.where("creationDate").gt(LocalDate.now().plusDays(30)).lt(LocalDate.now()));
		List<SensorMeasurement> measurements =  mongoTemplate.find(query, SensorMeasurement.class);
		SensorMetricsResponse measurementMetricsResponse = null;
		Optional<SensorMeasurement> maxSensorMeasurement = null;
		OptionalDouble avgSensorMeasurement = null;
		if(measurements != null  && !measurements.isEmpty()) {
			maxSensorMeasurement = measurements.stream().max(Comparator.comparing(SensorMeasurement::getCo2));
			avgSensorMeasurement = measurements.stream().mapToInt(SensorMeasurement::getCo2).average();
		}
		if(avgSensorMeasurement.isPresent()) {
			measurementMetricsResponse = new SensorMetricsResponse();
			measurementMetricsResponse.setAvgLast30Days(avgSensorMeasurement.getAsDouble());
		}
		if(maxSensorMeasurement.isPresent()) {
			if(measurementMetricsResponse == null) {
				measurementMetricsResponse = new SensorMetricsResponse();
			}
			measurementMetricsResponse.setMaxLast30Days(maxSensorMeasurement.get().getCo2());
		}
		return measurementMetricsResponse;
	}

}
