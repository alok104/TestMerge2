package com.bank.customers.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.bank.customers.exception.CustomersException;

public class CustomerValidatorIdTest {


	@Test
	public void testIsNumeric() {

		assertThat(CustomerValidator.isNumeric("22")).isTrue();
		assertThat(CustomerValidator.isNumeric("5.05")).isFalse();
		assertThat(CustomerValidator.isNumeric("-200")).isFalse();

		assertThat(CustomerValidator.isNumeric(null)).isFalse();
		assertThat(CustomerValidator.isNumeric("abc")).isFalse();

	}

	
	@Test
	public void testCustomerIdAlphabetValue() {

		Exception exception = assertThrows(CustomersException.class, () -> {
			CustomerValidator.validateCustomerId("ABC");
		});

		String expectedMessage = "Invalid ID";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testCustomerIdNegativeNumeber() {

		Exception exception = assertThrows(CustomersException.class, () -> {
			CustomerValidator.validateCustomerId("-200");
		});

		String expectedMessage = "Invalid ID";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testCustomerIdNullCheck() {

		Exception exception = assertThrows(CustomersException.class, () -> {
			CustomerValidator.validateCustomerId(null);
		});

		String expectedMessage = "Invalid ID";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
}
