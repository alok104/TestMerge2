package com.bank.customers.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bank.customers.encryption.AesEncryption;
import com.bank.customers.entity.Customer;
import com.bank.customers.enums.IndentityType;
import com.bank.customers.model.Address;
import com.bank.customers.model.CustomerVO;
import com.bank.customers.model.Identity;

public class DtoFactory {
	public static CustomerVO getCustomerVO() {
		CustomerVO customerVO = new CustomerVO();
		customerVO.setCustomerId("1000001");
		customerVO.setName("abcd");
		customerVO.setDateOfBirth(1643324L);
		Address address = new Address();
		address.setCountry("UAE");
		address.setDistrict("Dubai");
		address.setStreet("Al mankhool");
		customerVO.setAddress(address);
		Identity identity = new Identity();
		identity.setType(IndentityType.EIDA);
		identity.setNumber("12325323253");
		customerVO.setIdentity(identity);
		return customerVO;
		
	}

	public static Customer getCustomer() {
		return getAllCustomer().get(0);
	}

	public static Optional<Customer> getOptionalCustomer() {
		return Optional.ofNullable(getAllCustomer().get(0));
	}
	
	public static List<Customer> getAllCustomer() {
		List<Customer> customerList = new ArrayList<>();
		for(int i = 1000001; i < 1000011;i++) {
			Customer customer = new Customer();
			customer.setCustomerId(i);
			customer.setName("abcd"+i);
			customer.setDateOfBirth(1643324L);
			customerList.add(customer);
		}
		
		return customerList;
	}
	
	public List<CustomerVO> getAllCustomerVo() {
		List<CustomerVO> customerList = new ArrayList<>();
		for(int i = 1000001; i < 1000011;i++) {
			CustomerVO customer = new CustomerVO();
			customer.setCustomerId(AesEncryption.encrypt(String.valueOf(i), "secret"));
			customer.setName("abcd"+i);
			customer.setDateOfBirth(1643324L);
			customerList.add(customer);
		}
		
		return customerList;
	}

	public static Optional<Customer> getOptionalEmptyCustomer() {
		return Optional.empty();
	}
}
