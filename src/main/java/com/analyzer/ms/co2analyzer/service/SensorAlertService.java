package com.analyzer.ms.co2analyzer.service;

import com.analyzer.ms.co2analyzer.model.AlertResponse;

public interface SensorAlertService {
	public AlertResponse listAlerts(String uuid);
}
