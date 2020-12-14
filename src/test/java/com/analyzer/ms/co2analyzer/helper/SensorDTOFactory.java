package com.analyzer.ms.co2analyzer.helper;

import java.util.Date;
import java.util.Random;

import com.analyzer.ms.co2analyzer.entity.SensorMeasurement;
import com.analyzer.ms.co2analyzer.model.SensorRequest;
import com.analyzer.ms.co2analyzer.utility.SensorStatusMeasurementUtil;

public class SensorDTOFactory {
	public SensorMeasurement sensorMeasurementEntity(String uuid, Integer co2) {
		return new SensorMeasurement(uuid, co2, new Date(), SensorStatusMeasurementUtil.measureStatusByCo2(co2).name());
	}
	
	public SensorRequest sensorRequest() {
		Random rendomNumber = new Random();
		int low = 1500;
		int high = 5000;
		int result = rendomNumber.nextInt(high-low) + low;
		return new SensorRequest(result, new Date());
	}
	
	public int generateUuid() {
		Random rendomNumber = new Random();
		int low = 55500;
		int high = 123000;
		int result = rendomNumber.nextInt(high-low) + low;
		return result;
	}
	
	public SensorRequest randomRequest() {
		return new SensorRequest(null, new Date());
	}
}
