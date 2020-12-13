package com.analyzer.ms.co2analyzer.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SensorRequestVO  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String co2;
	private Date time;
}
