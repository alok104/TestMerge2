package com.bank.customers.model;

import com.bank.customers.enums.IndentityType;

import lombok.Data;

@Data
public class Identity {
	private IndentityType type;
	private String number;
}
