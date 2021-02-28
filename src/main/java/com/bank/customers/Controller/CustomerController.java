package com.bank.customers.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bank.customers.constants.Constant;
import com.bank.customers.model.CustomerVO;
import com.bank.customers.service.CustomerService;
import com.bank.customers.validator.CustomerValidator;

@RestController
@RequestMapping(value = Constant.CUSTOMER_URI)
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(Constant.ALL_CUSTOMER_URI)
	public List<CustomerVO> getCustomers() {
		return customerService.getAllCustomerData();
	}

	@GetMapping(Constant.CUSTOMER_OPERATION_URI)
	public CustomerVO getCustomerData(@PathVariable String id) {
		CustomerValidator.validateCustomerId(id);
		return customerService.getCustomerData(id);
	}

	@PostMapping
	public ResponseEntity<?> addCustomerDetails(@RequestBody(required = true) CustomerVO customer ) {
		CustomerValidator.validateCustomerRequest(customer);
		String id = customerService.addCustomer(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id)
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(Constant.CUSTOMER_OPERATION_URI)
	public ResponseEntity<?> updateCustomerDetails(@RequestBody(required = true) CustomerVO customer,@PathVariable(value = "id") String customerId) {
		CustomerValidator.validateCustomerId(customerId);
		customerService.updateCustomer(customer,customerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(Constant.CUSTOMER_OPERATION_URI)
	public ResponseEntity<?> deleteCustomerDetails(@PathVariable(value = "id") String customerId) {
		CustomerValidator.validateCustomerId(customerId);
		customerService.deleteCustomer(customerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
