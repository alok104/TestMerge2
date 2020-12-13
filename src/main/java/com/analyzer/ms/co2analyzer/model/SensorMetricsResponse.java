package com.analyzer.ms.co2analyzer.model;

import java.io.Serializable;

import lombok.Data;
@Data
public class SensorMetricsResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer maxLast30Days;
	private Integer avgLast30Days;

}