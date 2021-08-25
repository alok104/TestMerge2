package com.analyzer.ms.co2analyzer.service;

import java.util.List;

import com.analyzer.ms.co2analyzer.model.AlertResponse;

public interface SensorAlertService {
	public List<AlertResponse> listAlerts(String uuid);
}
