package com.analyzer.ms.co2analyzer.service;

import com.analyzer.ms.co2analyzer.model.SensorMetricsResponse;

public interface SensorMetricService {
	public SensorMetricsResponse getSensorMetrics(String uuid);
}
