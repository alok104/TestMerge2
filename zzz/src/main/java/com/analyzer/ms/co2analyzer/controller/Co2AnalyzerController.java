package com.analyzer.ms.co2analyzer.controller;


import static com.analyzer.ms.co2analyzer.constants.Constants.BASE_URI;
import static com.analyzer.ms.co2analyzer.constants.Constants.COLLECT_SENSOR_URI;
import static com.analyzer.ms.co2analyzer.constants.Constants.LISTING_ALERTS_URI;
import static com.analyzer.ms.co2analyzer.constants.Constants.SENSOR_METRICS_URI;
import static com.analyzer.ms.co2analyzer.constants.Constants.SENSOR_STATUS_URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analyzer.ms.co2analyzer.model.SensorRequest;
import com.analyzer.ms.co2analyzer.service.SensorAlertService;
import com.analyzer.ms.co2analyzer.service.SensorCollectService;
import com.analyzer.ms.co2analyzer.service.SensorMetricService;
import com.analyzer.ms.co2analyzer.service.SensorStatusService;

@RestController
@RequestMapping(value = BASE_URI)
public class Co2AnalyzerController {
	
	@Autowired
	private SensorAlertService sensorAlertService;
	@Autowired
	private SensorCollectService sensorCollectService;
	@Autowired
	private SensorMetricService sensorMetricService;
	@Autowired
	private SensorStatusService sensorStatusService;
	
	@PostMapping(COLLECT_SENSOR_URI)
	public ResponseEntity<?> collectSensor(@PathVariable String uuid, @RequestBody(required = true) SensorRequest sensorRequest) {
		sensorCollectService.collectSensor(sensorRequest,uuid);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(SENSOR_STATUS_URI)
	public ResponseEntity<?> getSensorStatus(@PathVariable String uuid) {
		return new ResponseEntity<>(sensorStatusService.getSensorStatus(uuid), HttpStatus.OK);
	}
	
	@GetMapping(SENSOR_METRICS_URI)
	public ResponseEntity<?> getSensorMetrics(@PathVariable String uuid) {
		return new ResponseEntity<>(sensorMetricService.getSensorMetrics(uuid), HttpStatus.OK);
	}
	
	@GetMapping(LISTING_ALERTS_URI)
	public ResponseEntity<?> listAlerts(@PathVariable String uuid) {
		return new ResponseEntity<>(sensorAlertService.listAlerts(uuid),HttpStatus.OK);
	}
}
