package com.analyzer.ms.co2analyzer.service;

import com.analyzer.ms.co2analyzer.model.SensorRequest;

public interface SensorCollectService {
	public void collectSensor(SensorRequest sensorRequest, String uuid);
}
