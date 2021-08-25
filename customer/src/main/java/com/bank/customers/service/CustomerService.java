package com.bank.customers.service;

import java.util.ArrayList;
import java.util.List;

import com.bank.customers.entity.Customer;
import com.bank.customers.model.CustomerVO;

public interface CustomerService {

	public List<CustomerVO> getAllCustomerData() ;

	public CustomerVO getCustomerData(String customerId);

	public String addCustomer(CustomerVO customer);

	public void updateCustomer(CustomerVO customerVo, String customerId);

	public void deleteCustomer(String customerId);

	default List<CustomerVO> buildCustomerVOs(List<Customer> customers) {
		if(customers != null && !customers.isEmpty()) {
			List<CustomerVO> customVOs = new ArrayList<CustomerVO>();
			customers.forEach(customer -> {
				customVOs.add(buildCustomerVO(customer));
			});
			return customVOs;
		}
		return null;
	}

	default CustomerVO buildCustomerVO(Customer customer) {
		CustomerVO customVO = new CustomerVO();
		customVO.setCustomerId(String.valueOf(customer.getCustomerId()));
		customVO.setAddress(customer.getAdress());
		customVO.setName(customer.getName());
		customVO.setIdentity(customer.getIdentity());
		customVO.setDateOfBirth(customer.getDateOfBirth());
		return customVO;
	}
	
	default Customer buildCustomer(CustomerVO customerVo, int id) {
		Customer customer = new Customer();
		customer.setCustomerId(id);
		customer.setAdress(customerVo.getAddress());
		customer.setDateOfBirth(customerVo.getDateOfBirth());
		customer.setName(customerVo.getName());
		customer.setIdentity(customerVo.getIdentity());
		return customer;
	}
}
