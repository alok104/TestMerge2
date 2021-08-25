package com.bank.customers.validator;

import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.logging.log4j.util.Strings;

import com.bank.customers.exception.CustomersException;
import com.bank.customers.model.Address;
import com.bank.customers.model.CustomerVO;
import com.bank.customers.model.Identity;

public class CustomerValidator {
	private static Pattern pattern = Pattern.compile("^[1-9]\\d*$");

	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    return pattern.matcher(strNum).matches();
	}
	public static void validateCustomerId(String customerId) {
		if(!isNumeric(customerId)) {
			throw new CustomersException("Invalid ID");
		}
	}
	
	public static void validateCustomerRequest(CustomerVO customer) {
		if(Objects.isNull(customer)) {
			throw new CustomersException("Invalid Request");
		}
		if(Strings.isBlank(customer.getName())) {
			throw new CustomersException("customerName is mandatory");
		}
		if(Objects.isNull(customer.getDateOfBirth())) {
			throw new CustomersException("dateOfBirth is mandatory");
		}
		vadlidateIdentity(customer.getIdentity());
		validateAddress(customer.getAddress());
	}
	
	private static void vadlidateIdentity(Identity identity) {
		if(Objects.isNull(identity)) {
			throw new CustomersException("Identity is mandatory");
		}	
		
		if(Strings.isBlank(identity.getNumber())) {
			throw new CustomersException("Identity number is mandatory");
		}	
	}
	private static void validateAddress(Address adress) {
		if(Objects.isNull(adress)) {
			throw new CustomersException("address field is mandatory");
		}
		if(Strings.isBlank(adress.getCountry())) {
			throw new CustomersException("countryCode field is mandatory");
		}
		
	}
}
