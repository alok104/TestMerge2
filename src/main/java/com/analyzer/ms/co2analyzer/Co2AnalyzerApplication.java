package com.analyzer.ms.co2analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;

@Cacheable
@SpringBootApplication
public class Co2AnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Co2AnalyzerApplication.class, args);
	}

}
