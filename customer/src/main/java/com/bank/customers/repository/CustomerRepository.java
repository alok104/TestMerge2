package com.bank.customers.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bank.customers.entity.Customer;

public interface CustomerRepository extends MongoRepository<Customer, Integer> {

	Optional<Customer> findTopByOrderByCustomerIdDesc();

}
