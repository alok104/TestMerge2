package com.bank.customers.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bank.customers.util.DtoFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerCreationControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	
	@Test
	public void createEmployeeAPI() throws Exception 
	{
	  mvc.perform( MockMvcRequestBuilders
	      .post("/employees")
	      .content(asJsonString(DtoFactory.getCustomer()))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isCreated())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
	}
	 
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
