package com.jsp.bankingapp.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDao {
	private long id;
	private String accountHolderName;
	private double balance;
}
