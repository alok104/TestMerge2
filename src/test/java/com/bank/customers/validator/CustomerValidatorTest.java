package com.bank.customers.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.customers.exception.CustomersException;
import com.bank.customers.model.CustomerVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CustomerValidatorTest {
	
	ClassLoader loader = ClassLoader.getSystemClassLoader();
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testValidateCustomerRequestNullCheck() {
		Exception exception = assertThrows(CustomersException.class, () -> {
			CustomerVO customerVO = null;
			CustomerValidator.validateCustomerRequest(customerVO);
		});

		String expectedMessage = "Invalid Request";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testValidateCustomerRequest() throws IOException, URISyntaxException  {
		String json = Files.lines(Paths.get(loader.getResource("customer.json").toURI()))
                .parallel()
                .collect(Collectors.joining());	
		CustomerVO customerVO  =  objectMapper.readValue(json, CustomerVO.class);
		
		Exception exception = assertThrows(CustomersException.class, () -> {
			CustomerValidator.validateCustomerRequest(customerVO);
		});

		assertThat(exception == null);
	}
	
	@Test
	public void testValidateCustomerInvalidNameRequest() throws IOException, URISyntaxException  {
		String json = Files.lines(Paths.get(loader.getResource("customer-noname.json").toURI()))
                .parallel()
                .collect(Collectors.joining());	
		CustomerVO customerVO  =  objectMapper.readValue(json, CustomerVO.class);
		
		Exception exception = assertThrows(CustomersException.class, () -> {
			CustomerValidator.validateCustomerRequest(customerVO);
		});

		assertThat(exception != null);
		assertThat("customerName is mandatory".equals(exception.getMessage()));
	}
	
	@Test
	public void testValidateCustomerInvalidDateRequest() throws IOException, URISyntaxException  {
		String json = Files.lines(Paths.get(loader.getResource("customer-nodob.json").toURI()))
                .parallel()
                .collect(Collectors.joining());	
		CustomerVO customerVO  =  objectMapper.readValue(json, CustomerVO.class);
		
		Exception exception = assertThrows(CustomersException.class, () -> {
			CustomerValidator.validateCustomerRequest(customerVO);
		});

		assertThat(exception != null);
		assertThat("dateOfBirth is mandatory".equals(exception.getMessage()));
	}
	
	@Test
	public void testValidateCustomerInvalidIdentityRequest() throws IOException, URISyntaxException  {
		String json = Files.lines(Paths.get(loader.getResource("customer-noidentity.json").toURI()))
                .parallel()
                .collect(Collectors.joining());	
		CustomerVO customerVO  =  objectMapper.readValue(json, CustomerVO.class);
		
		Exception exception = assertThrows(CustomersException.class, () -> {
			CustomerValidator.validateCustomerRequest(customerVO);
		});

		assertThat(exception != null);
		assertThat("Identity is mandatory".equals(exception.getMessage()));
	}
	
	@Test
	public void testValidateCustomerInvalidIdentityNameRequest() throws IOException, URISyntaxException  {
		String json = Files.lines(Paths.get(loader.getResource("customer-noidentityName.json").toURI()))
                .parallel()
                .collect(Collectors.joining());	
		CustomerVO customerVO  =  objectMapper.readValue(json, CustomerVO.class);
		
		Exception exception = assertThrows(CustomersException.class, () -> {
			CustomerValidator.validateCustomerRequest(customerVO);
		});

		assertThat(exception != null);
		assertThat("Identity number is mandatory".equals(exception.getMessage()));
	}
	
	@Test
	public void testValidateCustomerInvalidAddressRequest() throws IOException, URISyntaxException  {
		String json = Files.lines(Paths.get(loader.getResource("customer-noaddress.json").toURI()))
                .parallel()
                .collect(Collectors.joining());	
		CustomerVO customerVO  =  objectMapper.readValue(json, CustomerVO.class);
		
		Exception exception = assertThrows(CustomersException.class, () -> {
			CustomerValidator.validateCustomerRequest(customerVO);
		});

		assertThat(exception != null);
		assertThat("address field is mandatory".equals(exception.getMessage()));
	}
	
	@Test
	public void testValidateCustomerInvalidCountryRequest() throws IOException, URISyntaxException  {
		String json = Files.lines(Paths.get(loader.getResource("customer-noCountry.json").toURI()))
                .parallel()
                .collect(Collectors.joining());	
		CustomerVO customerVO  =  objectMapper.readValue(json, CustomerVO.class);
		
		Exception exception = assertThrows(CustomersException.class, () -> {
			CustomerValidator.validateCustomerRequest(customerVO);
		});

		assertThat(exception != null);
		assertThat("countryCode field is mandatory".equals(exception.getMessage()));
	}

}
