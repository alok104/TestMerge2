package com.bank.customers.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.bank.customers.constants.Constant;
import com.bank.customers.encryption.AesEncryption;
import com.bank.customers.entity.Customer;
import com.bank.customers.exception.CustomersException;
import com.bank.customers.model.CustomerVO;
import com.bank.customers.repository.CustomerRepository;
import com.bank.customers.validator.CustomerValidator;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
    private static Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

	
	@Value("${aes.secret}")
	private String secret;

	@Override
	public List<CustomerVO> getAllCustomerData() {
		logger.info("getAllCustomerData called");
		List<Customer> customers = customerRepo.findAll();
		if(customers == null || customers.isEmpty()) {
			logger.error("No data found");
			throw new CustomersException("No data found");
		}
		return buildCustomerVOs(customers);
	}

	@Override
	public CustomerVO getCustomerData(String customerId) {
		logger.info("getCustomerData called");
		String decryptedId = AesEncryption.decrypt(customerId, secret);
		CustomerValidator.validateCustomerId(decryptedId);
		Optional<Customer> customer = customerRepo.findById(Integer.valueOf(decryptedId));
		if(customer.isEmpty()) {
			logger.error("No data found for Id:{}",decryptedId);
			throw new CustomersException(Constant.ERROR_ID_DOESNT_EXIST);
		}
		return buildCustomerVO(customer.get());
	}

	@Override
	public String addCustomer(CustomerVO customerVo) {
		logger.info("addCustomer called");
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
		logger.info("updateCustomer called");
		CustomerVO customer = getCustomerData(customerId);
		customerRepo.save(buildCustomer(customer,Integer.parseInt(customerVo.getCustomerId())));
	}

	@Override
	public void deleteCustomer(String customerId) {
		logger.info("deleteCustomer called");
		CustomerVO customerVo = getCustomerData(customerId);
		customerRepo.deleteById(Integer.parseInt(customerVo.getCustomerId()));
	}

}
