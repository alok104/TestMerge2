package com.analyzer.ms.co2analyzer.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.analyzer.ms.co2analyzer.constants.Constants;
import com.analyzer.ms.co2analyzer.helper.SensorDTOFactory;
import com.analyzer.ms.co2analyzer.service.SensorAlertService;
import com.analyzer.ms.co2analyzer.service.SensorCollectService;
import com.analyzer.ms.co2analyzer.service.SensorMetricService;
import com.analyzer.ms.co2analyzer.service.SensorStatusService;

@RunWith(SpringRunner.class)
@WebMvcTest(Co2AnalyzerController.class)
public class SensorStatusControllerTest {
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
	
	protected static SensorDTOFactory sensorDtoFactory;
	
	public static final String UUID = "{uuid}";
	
	@Before
	public void setUp() {
		sensorDtoFactory = new SensorDTOFactory();
	}
	
	@Test
	public void testOk() throws Exception {
		Mockito.when(sensorStatusService.getSensorStatus(any(String.class))).thenReturn(sensorDtoFactory.sensorStatus());
		StringBuilder uriBuilder = new StringBuilder().append(Constants.BASE_URI).append(Constants.SENSOR_STATUS_URI);
		URI uri = new URI(uriBuilder.toString().replace(UUID, String.valueOf(sensorDtoFactory.generateUuid())));
		ResultActions mvcResult = mockMvc.perform(get((uri)));
		assertThat(mvcResult).isNotNull();
		mvcResult.andExpect(status().isOk());
	}
	
	@Test
	public void testFailure() throws Exception {
		Mockito.when(sensorStatusService.getSensorStatus(any(String.class))).thenReturn(sensorDtoFactory.sensorStatus());
		StringBuilder uriBuilder = new StringBuilder().append(Constants.BASE_URI).append(Constants.SENSOR_STATUS_URI);
		URI uri = new URI(uriBuilder.toString().replace("", String.valueOf(sensorDtoFactory.generateUuid())));
		ResultActions mvcResult = mockMvc.perform(get((uri)));
		assertThat(mvcResult).isNotNull();
		mvcResult.andExpect(status().isBadRequest());
	}
	
}
