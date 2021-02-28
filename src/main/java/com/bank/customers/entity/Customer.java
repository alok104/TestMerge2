package com.bank.customers.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bank.customers.model.Address;
import com.bank.customers.model.Identity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection ="customer")
public class Customer {
	@Id
	private int customerId;
	private String name;
	private Long dateOfBirth;
	private Identity identity;
	private Address adress;
}
