package com.bank.customers.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customerId;
	private String name;
	private Long dateOfBirth;
	private Identity identity;
	private Address address;
}
