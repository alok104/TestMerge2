package com.analyzer.ms.co2analyzer.utility;

import java.util.concurrent.atomic.AtomicInteger;

import com.analyzer.ms.co2analyzer.enums.SensorStatus;

public class SensorStatusMeasurementUtil {
	
	private static Boolean isAlrert = false;
	private static AtomicInteger warnCount = new AtomicInteger();
	public static SensorStatus measureStatusByCo2(Integer co2) {
		if(co2 > 2000) {
			if(warnCount.get() == 3 || warnCount.incrementAndGet() == 3) {
				isAlrert = true;
				return SensorStatus.ALERT;
			}
			return SensorStatus.WARN;
		} else {
			if(isAlrert && warnCount.decrementAndGet() != 0) {
				return SensorStatus.ALERT;
			}
			isAlrert = false;
			warnCount.set(0);
			return SensorStatus.OK;
		}
	}

}
