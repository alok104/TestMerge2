package com.analyzer.ms.co2analyzer.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.analyzer.ms.co2analyzer.constants.Constants;
import com.analyzer.ms.co2analyzer.entity.SensorMeasurement;
import com.analyzer.ms.co2analyzer.helper.SensorDTOFactory;
import com.analyzer.ms.co2analyzer.repository.SensorMeasurementRepository;
import com.analyzer.ms.co2analyzer.service.SensorAlertService;
import com.analyzer.ms.co2analyzer.service.SensorCollectService;
import com.analyzer.ms.co2analyzer.service.SensorMetricService;
import com.analyzer.ms.co2analyzer.service.SensorStatusService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(Co2AnalyzerController.class)
public class SensorMetricsControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private SensorAlertService sensorAlertService;
	@MockBean
	private SensorCollectService sensorCollectService;
	@MockBean
	private SensorMetricService sensorMetricService;
	@MockBean
	private SensorStatusService sensorStatusService;
	
	@MockBean
	private SensorMeasurementRepository measurementRepo;
	
	protected static SensorDTOFactory sensorDtoFactory;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public static final String UUID = "{uuid}";
	
	@Before
	public void setUp() {
		sensorDtoFactory = new SensorDTOFactory();
	}
	
	@Test
	public void testOk() throws Exception {
		Mockito.when(measurementRepo.save(any(SensorMeasurement.class))).thenReturn(sensorDtoFactory.sensorMeasurementEntity("12424",2001));
		StringBuilder uriBuilder = new StringBuilder().append(Constants.BASE_URI).append(Constants.SENSOR_METRICS_URI);
		URI uri = new URI(uriBuilder.toString().replace(UUID, String.valueOf(sensorDtoFactory.generateUuid())));
		ResultActions mvcResult = mockMvc.perform(get(uri));
		assertThat(mvcResult).isNotNull();
		mvcResult.andExpect(status().isOk());
	}
	
	@Test
	public void testFailure() throws Exception {
		Mockito.when(measurementRepo.save(any(SensorMeasurement.class))).thenReturn(sensorDtoFactory.sensorMeasurementEntity("12424",2001));
		StringBuilder uriBuilder = new StringBuilder().append(Constants.BASE_URI).append(Constants.COLLECT_SENSOR_URI);
		URI uri = new URI(uriBuilder.toString().replace(UUID, String.valueOf(sensorDtoFactory.generateUuid())));
		ResultActions mvcResult = mockMvc.perform(post(uri).content(objectMapper.writeValueAsString(sensorDtoFactory.randomRequest()))
				.contentType(MediaType.APPLICATION_JSON));
		assertThat(mvcResult).isNotNull();
		mvcResult.andExpect(status().isBadRequest());
	}
	
}
