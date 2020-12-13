package com.analyzer.ms.co2analyzer.constants;

public class Constants {
	public final static String BASE_URI = "/api/v1/sensors";
	public final static String COLLECT_SENSOR_URI = "/{uuid}/mesurements";
	public final static String SENSOR_STATUS_URI = "/{uuid}";
	public final static String SENSOR_METRICS_URI = "/{uuid}/metric";
	public final static String LISTING_ALERTS_URI = "/{uuid}/alerts";
	
	private Constants() {
		
	}
	
}
