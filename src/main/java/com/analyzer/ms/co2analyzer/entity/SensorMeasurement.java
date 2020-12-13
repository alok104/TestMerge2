package com.analyzer.ms.co2analyzer.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "sensormeasurement")
public class SensorMeasurement {
	private String uuid;
	private Integer co2;
	private Date time;
	private String status;
	
}
