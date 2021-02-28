package com.bank.customers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bank.customers.constants.Constant;
import com.bank.customers.encryption.AesEncryption;
import com.bank.customers.entity.Customer;
import com.bank.customers.exception.CustomersException;
import com.bank.customers.model.CustomerVO;
import com.bank.customers.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Value("${aes.secret}")
	private String secret;

	@Override
	public List<CustomerVO> getAllCustomerData() {
		List<Customer> customers = customerRepo.findAll();
		if(customers == null || customers.isEmpty()) {
			throw new CustomersException("No data found");
		}
		return buildCustomerVOs(customers);
	}

	@Override
	public CustomerVO getCustomerData(String customerId) {
		String decryptedId = AesEncryption.decrypt(customerId, secret);
		Optional<Customer> customer = customerRepo.findById(Integer.valueOf(decryptedId));
		if(customer.isEmpty()) {
			throw new CustomersException(Constant.ERROR_ID_DOESNT_EXIST);
		}
		return buildCustomerVO(customer.get());
	}

	@Override
	public String addCustomer(CustomerVO customerVo) {
	    Optional<Customer> last = customerRepo.findTopByOrderByCustomerIdDesc();
	    if(last.isPresent()) {
	    	Customer customer = customerRepo.save(buildCustomer(customerVo,last.get().getCustomerId()+1));
	    	return String.valueOf(customer.getCustomerId());
	    	
	    } else {
	    	Customer customer = customerRepo.save(buildCustomer(customerVo,100001));
	    	return String.valueOf(customer.getCustomerId());
	    }
	}

	@Override
	public void updateCustomer(CustomerVO customerVo, String customerId) {
		String decryptedId = AesEncryption.decrypt(customerId, secret);
		CustomerVO customer = getCustomerData(decryptedId);
		if(customer == null) {
			throw new CustomersException(Constant.ERROR_ID_DOESNT_EXIST);
		}
		customerRepo.save(buildCustomer(customer,Integer.parseInt(customerVo.getCustomerId())));
	}

	@Override
	public void deleteCustomer(String customerId) {
		String decryptedId = AesEncryption.decrypt(customerId, secret);

		CustomerVO customer = getCustomerData(decryptedId);
		if(customer == null) {
			throw new CustomersException(Constant.ERROR_ID_DOESNT_EXIST);
		}
		customerRepo.deleteById(Integer.parseInt(customerId));
	}

}
