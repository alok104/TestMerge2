package com.analyzer.ms.co2analyzer.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sensormeasurement")
public class SensorMeasurement {
	private String uuid;
	private Integer co2;
	private Date time;
	private String status;
	
}
