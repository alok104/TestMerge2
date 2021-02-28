package com.bank.customers.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Base64;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.bank.customers.Controller.CustomerController;
import com.bank.customers.constants.Constant;
import com.bank.customers.encryption.AesEncryption;
import com.bank.customers.entity.Customer;
import com.bank.customers.repository.CustomerRepository;
import com.bank.customers.service.CustomerService;
import com.bank.customers.util.DtoFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepo;
	@Autowired
	private ObjectMapper objectMapper;
	
	
	protected static DtoFactory sensorDtoFactory;
	private HttpHeaders header;
	
	@Before
	public void setUp() {
		sensorDtoFactory = new DtoFactory();
		header =new HttpHeaders() {/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
	         String auth = "admin" + ":" + "password";
	         byte[] encodedAuth = Base64.getEncoder().encode( 
	            auth.getBytes(Charset.forName("US-ASCII")) );
	         String authHeader = "Basic " + new String( encodedAuth );
	         set( "Authorization", authHeader );
	      }};
	}
	
	@Test
	public void testOk() throws Exception {
		when(customerService.getAllCustomerData()).thenReturn(sensorDtoFactory.getAllCustomerVo());
		StringBuilder uriBuilder = new StringBuilder().append(Constant.CUSTOMER_URI).append(Constant.ALL_CUSTOMER_URI);
		URI uri = new URI(uriBuilder.toString());
		ResultActions result = mockMvc.perform(get(uri).headers(header)).andExpect(status().isOk());
		result.andExpect(jsonPath("$customerId", is(sensorDtoFactory.getAllCustomerVo().get(0).getCustomerId())));
	}
	
	@Test
	public void testAuthenticateFailureAPI() throws JsonProcessingException, Exception
	{
		when(customerRepo.save(any(Customer.class))).thenReturn(DtoFactory.getCustomer());
		StringBuilder uriBuilder = new StringBuilder().append(Constant.CUSTOMER_URI);
		URI uri = new URI(uriBuilder.toString());
		ResultActions mvcResult = mockMvc.perform(post(uri).content(objectMapper.writeValueAsString(DtoFactory.getCustomerVO()))
				.contentType(MediaType.APPLICATION_JSON));
		assertThat(mvcResult).isNotNull();
		mvcResult.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void testCreateCustomer() throws JsonProcessingException, Exception
	{
		when(customerRepo.save(any(Customer.class))).thenReturn(DtoFactory.getCustomer());
		StringBuilder uriBuilder = new StringBuilder().append(Constant.CUSTOMER_URI);
		URI uri = new URI(uriBuilder.toString());
		
		ResultActions mvcResult = mockMvc.perform(post(uri).headers(header).content(objectMapper.writeValueAsString(DtoFactory.getCustomerVO()))
				.contentType(MediaType.APPLICATION_JSON));
		assertThat(mvcResult).isNotNull();
		mvcResult.andExpect(status().isCreated());
	}
	
	@Test
	public void testUpdateCustomer() throws JsonProcessingException, Exception
	{
		when(customerRepo.save(any(Customer.class))).thenReturn(DtoFactory.getCustomer());
		StringBuilder uriBuilder = new StringBuilder().append(Constant.CUSTOMER_URI).append("/").append(AesEncryption.encrypt("1000001", "secret"));
		URI uri = new URI(uriBuilder.toString());
		
		ResultActions mvcResult = mockMvc.perform(put(uri).headers(header).content(objectMapper.writeValueAsString(DtoFactory.getCustomerVO()))
				.contentType(MediaType.APPLICATION_JSON));
		assertThat(mvcResult).isNotNull();
		mvcResult.andExpect(status().isNoContent());
	}
	
	@Test
	public void testDeleteCustomerData() throws JsonProcessingException, Exception
	{
		when(customerRepo.save(any(Customer.class))).thenReturn(DtoFactory.getCustomer());
		StringBuilder uriBuilder = new StringBuilder().append(Constant.CUSTOMER_URI).append("/").append(AesEncryption.encrypt("1000001", "secret"));
		URI uri = new URI(uriBuilder.toString());
		
		ResultActions mvcResult = mockMvc.perform(delete(uri).headers(header));
		assertThat(mvcResult).isNotNull();
		mvcResult.andExpect(status().isNoContent());
	}
	
	@Test
	public void testIdNotExist() throws JsonProcessingException, Exception
	{
		StringBuilder uriBuilder = new StringBuilder().append(Constant.CUSTOMER_URI).append("/sdah");
		URI uri = new URI(uriBuilder.toString());
		
		ResultActions mvcResult = mockMvc.perform(get(uri).headers(header));
		assertThat(mvcResult).isNotNull();
		mvcResult.andExpect(status().isBadRequest());
	}
	 
	@SuppressWarnings("static-access")
	@Test
	public void testGetCustomerData() throws JsonProcessingException, Exception
	{
		when(customerService.getCustomerData(any(String.class))).thenReturn(sensorDtoFactory.getCustomerVO());

		StringBuilder uriBuilder = new StringBuilder().append(Constant.CUSTOMER_URI).append("/").append(AesEncryption.encrypt("1000001", "secret"));
		URI uri = new URI(uriBuilder.toString());
		
		ResultActions mvcResult = mockMvc.perform(get(uri).headers(header));
		assertThat(mvcResult).isNotNull();
		mvcResult.andExpect(status().isOk());
	}
}