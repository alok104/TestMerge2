package com.analyzer.ms.co2analyzer.service;

import com.analyzer.ms.co2analyzer.model.SensorRequest;
import com.analyzer.ms.co2analyzer.model.SensorRequestVO;

public interface SensorCollectService {
	public void collectSensor(SensorRequest sensorRequest, String uuid);
	default SensorRequestVO mapToSensorVo(SensorRequest sensorRequest, String uuid) {
		SensorRequestVO requestVO = new SensorRequestVO();
		requestVO.setCo2(sensorRequest.getCo2());
		requestVO.setTime(sensorRequest.getTime());
		requestVO.setUuid(uuid);
		return requestVO;
	}
}
