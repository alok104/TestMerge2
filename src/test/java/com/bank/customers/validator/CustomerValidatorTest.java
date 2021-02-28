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
import com.fasterxml.jackson.core.JsonProcessingException;
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
		try {
		String json = Files.lines(Paths.get(loader.getResource("customer-noname.json").toURI()))
                .parallel()
                .collect(Collectors.joining());	
		CustomerVO customerVO  =  objectMapper.readValue(json, CustomerVO.class);
		
		Exception exception = assertThrows(CustomersException.class, () -> {
			CustomerValidator.validateCustomerRequest(customerVO);
		});

		assertThat(exception == null);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * @Test public static void testVadlidateIdentity(Identity identity) {
	 * if(Objects.isNull(identity)) { throw new
	 * CustomersException("Identity is mandatory"); }
	 * 
	 * if(Strings.isBlank(identity.getNumber())) { throw new
	 * CustomersException("Identity number is mandatory"); } }
	 * 
	 * @Test private static void validateAddress(Address adress) {
	 * if(Objects.isNull(adress)) { throw new
	 * CustomersException("address field is mandatory"); }
	 * if(Strings.isBlank(adress.getCountry())) { throw new
	 * CustomersException("countryCode field is mandatory"); }
	 * 
	 * }
	 */
}
