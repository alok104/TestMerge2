package com.analyzer.ms.co2analyzer.service;

import com.analyzer.ms.co2analyzer.model.StatusResposeVO;

public interface SensorStatusService {
	public StatusResposeVO getSensorStatus(String uuid);
}
