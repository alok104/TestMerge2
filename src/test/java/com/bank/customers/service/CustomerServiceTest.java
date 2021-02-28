package com.bank.customers.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.customers.constants.Constant;
import com.bank.customers.encryption.AesEncryption;
import com.bank.customers.entity.Customer;
import com.bank.customers.exception.CustomersException;
import com.bank.customers.model.CustomerVO;
import com.bank.customers.repository.CustomerRepository;
import com.bank.customers.util.DtoFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CustomerServiceTest {
	
	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@SuppressWarnings("static-access")
	@Test
	public void testGetAllCustomer() {
		when(customerRepository.findAll()).thenReturn(DtoFactory.getAllCustomer());
		List<CustomerVO> customerVOs = customerService.getAllCustomerData();
		assertThat(!Objects.isNull(customerVOs));
		assertTrue(!customerVOs.isEmpty());
		assertEquals(customerVOs.size(), 10);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testAddCustomer() {
		when(customerRepository.save(any(Customer.class))).thenReturn(DtoFactory.getCustomer());
		when(customerRepository.findTopByOrderByCustomerIdDesc()).thenReturn(DtoFactory.getOptionalCustomer());
		String uuid = customerService.addCustomer(DtoFactory.getCustomerVO());
		assertThat(Strings.isBlank(uuid));
		assertThat("1000001".equals(uuid));
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testOkGetCustomer() {
		when(customerRepository.findById(1000001)).thenReturn(DtoFactory.getOptionalCustomer());
		CustomerVO customerVo = customerService.getCustomerData(AesEncryption.encrypt("1000001", "secret"));
		assertThat(customerVo != null);
		assertThat("1000001".equals(customerVo.getCustomerId()));
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testFailureGetCustomer() {
		when(customerRepository.findById(1000001)).thenReturn(DtoFactory.getOptionalCustomer());
		Exception exception = assertThrows(CustomersException.class, () -> {
			customerService.getCustomerData(AesEncryption.encrypt("1000002", "secret"));
		});
		assertTrue(Constant.ERROR_ID_DOESNT_EXIST.equals(exception.getMessage()));

	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testDeleteCustomer() {
		when(customerRepository.findById(1000001)).thenReturn(DtoFactory.getOptionalCustomer());
		doNothing().when(customerRepository).deleteById(1000001);;
		Exception exception = assertThrows(CustomersException.class, () -> {
			customerService.deleteCustomer(AesEncryption.encrypt("1000001", "secret"));
		});

		assertTrue(Constant.ERROR_ID_DOESNT_EXIST.equals(exception.getMessage()));
		
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testUpdateCustomer() {
		when(customerRepository.findById(1000001)).thenReturn(DtoFactory.getOptionalCustomer());
		when(customerRepository.save(DtoFactory.getCustomer())).thenReturn(DtoFactory.getCustomer());

		Exception exception = assertThrows(CustomersException.class, () -> {
			customerService.updateCustomer(DtoFactory.getCustomerVO(),AesEncryption.encrypt("1000001", "secret"));
		});

		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(Constant.ERROR_ID_DOESNT_EXIST));
		
	}
	
	@Test
	public void testNoDataFound() {
		when(customerRepository.findAll()).thenReturn(null);
		String message = "No data found";
		Exception exception = assertThrows(CustomersException.class, () -> {
			customerService.getAllCustomerData();
		});

		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(message));
	}
	
	/*
	 * public void updateCustomer(CustomerVO customerVo, String customerId);
	 * 
	 * public void deleteCustomer(String customerId);
	 */
	


}
