package com.jsp.bankingapp.service;

import java.util.List;
import com.jsp.bankingapp.dao.AccountDao;

public interface AccountService {
	
	AccountDao createAccount(AccountDao accountDao);
	
	AccountDao getAccountById(long id);
	
	AccountDao deposit(long id , double amount);
	
	AccountDao withdraw(long id, double amount);
	
	List<AccountDao> getAllAccounts();
	
	void deleteAccount(long id);
}
