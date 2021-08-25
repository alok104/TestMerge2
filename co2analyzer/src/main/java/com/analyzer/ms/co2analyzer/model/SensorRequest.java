package com.analyzer.ms.co2analyzer.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer co2;
	private Date time;
	
}
