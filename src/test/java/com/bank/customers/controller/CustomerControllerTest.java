package com.bank.customers.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bank.customers.Controller.CustomerController;
import com.bank.customers.constants.Constant;
import com.bank.customers.repository.CustomerRepository;
import com.bank.customers.service.CustomerService;
import com.bank.customers.util.DtoFactory;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepo;
	
	
	protected static DtoFactory sensorDtoFactory;
	
	@Before
	public void setUp() {
		sensorDtoFactory = new DtoFactory();
	}
	
	@Test
	public void testOk() throws Exception {
		when(customerService.getAllCustomerData()).thenReturn(sensorDtoFactory.getAllCustomerVo());
		StringBuilder uriBuilder = new StringBuilder().append(Constant.CUSTOMER_URI).append(Constant.ALL_CUSTOMER_URI);
		URI uri = new URI(uriBuilder.toString());
		mockMvc.perform(get(uri)).andExpect(status().isOk()).
				andExpect(jsonPath("$.customerId", is(sensorDtoFactory.getAllCustomerVo().get(0).getCustomerId())));
	}
}