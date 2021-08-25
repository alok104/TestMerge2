package com.bank.customers.constants;

public class Constant {
	private Constant() {
		
	}
	
	public static final String CUSTOMER_URI = "/v1/customers";
	public static final String CUSTOMER_OPERATION_URI = "/{id}";
	public static final String ALL_CUSTOMER_URI = "/all";
	public static final String ERROR_MESSAGE = "Internal server error. Please contact admin";
	public static final String ERROR_ID_DOESNT_EXIST = "Id doesnt exist";
}
