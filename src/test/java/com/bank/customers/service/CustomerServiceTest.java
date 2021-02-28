package com.bank.customers.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.customers.constants.Constant;
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
	
	@Before
	public void setup() {
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testGetAllCustomer() {
		when(customerRepository.findAll()).thenReturn(DtoFactory.getAllCustomer());
		List<CustomerVO> customerVOs = customerService.getAllCustomerData();
		assertThat(customerVOs != null);
		assertThat(!customerVOs.isEmpty());
		assertThat(customerVOs.size() == 10);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testAddCustomer() {
		when(customerRepository.save(DtoFactory.getCustomer())).thenReturn(DtoFactory.getCustomer());
		String uuid = customerService.addCustomer(DtoFactory.getCustomerVO());
		assertThat(Strings.isBlank(uuid));
		assertThat("1000001".equals(uuid));
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testOkGetCustomer() {
		when(customerRepository.findById(1000001)).thenReturn(DtoFactory.getOptionalCustomer());
		CustomerVO customerVo = customerService.getCustomerData("1000001");
		assertThat(customerVo != null);
		assertThat("1000001".equals(customerVo.getCustomerId()));
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testFailureGetCustomer() {
		when(customerRepository.findById(1000001)).thenReturn(DtoFactory.getOptionalCustomer());
		CustomerVO customerVo = customerService.getCustomerData("1000002");
		assertThat(customerVo != null);
		assertThat("1000001".equals(customerVo.getCustomerId()));
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testDeleteCustomer() {
		doNothing().when(customerRepository).deleteById(1000001);;
		Exception exception = assertThrows(CustomersException.class, () -> {
			customerService.deleteCustomer("1000001");
		});

		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(Constant.ERROR_ID_DOESNT_EXIST));
		
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testUpdateCustomer() {
		when(customerRepository.save(DtoFactory.getCustomer())).thenReturn(DtoFactory.getCustomer());
		when(customerRepository.findById(1000001)).thenReturn(DtoFactory.getOptionalCustomer());

		Exception exception = assertThrows(CustomersException.class, () -> {
			customerService.updateCustomer(DtoFactory.getCustomerVO(), "10000012");
		});

		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(Constant.ERROR_ID_DOESNT_EXIST));
		
	}
	
	@Test
	public void testNoDataFound() {
		when(customerRepository.findAll()).thenReturn(DtoFactory.getAllCustomer());
		String message = "No data found";
		Exception exception = assertThrows(CustomersException.class, () -> {
			customerService.deleteCustomer("1000001");
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
