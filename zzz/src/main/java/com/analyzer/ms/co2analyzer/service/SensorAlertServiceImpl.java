package com.analyzer.ms.co2analyzer.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.analyzer.ms.co2analyzer.entity.SensorMeasurement;
import com.analyzer.ms.co2analyzer.enums.SensorStatus;
import com.analyzer.ms.co2analyzer.model.AlertResponse;

@Component
public class SensorAlertServiceImpl implements SensorAlertService {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<AlertResponse> listAlerts(String uuid) {
		LOG.info("Request received for listAlerts with uuid, {}", uuid);
		List<Criteria> criterias = new ArrayList<>();
		Query query = new Query();
		criterias.add(Criteria.where("uuid").is(uuid).and("status").is(SensorStatus.ALERT.name()));
		List<SensorMeasurement> measurements =  mongoTemplate.find(query, SensorMeasurement.class);
		Optional<SensorMeasurement> maxSensorMeasurement = null;
		measurements.sort(Comparator.comparing(SensorMeasurement::getTime, Comparator.nullsLast(Comparator.reverseOrder())));
		return null;
	}

}
