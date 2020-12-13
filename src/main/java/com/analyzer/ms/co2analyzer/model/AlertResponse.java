package com.analyzer.ms.co2analyzer.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AlertResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date startTime;
	private Date endTime;
	private String mesurement1;
	private String mesurement2;
	private String mesurement3;
	
}
