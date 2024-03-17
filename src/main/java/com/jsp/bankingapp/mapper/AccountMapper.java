package com.jsp.bankingapp.mapper;
import com.jsp.bankingapp.dao.AccountDao;
import com.jsp.bankingapp.entity.Account;

public class AccountMapper {
	
	public static Account mapToAccount(AccountDao accountDao) {
		Account account = new Account(
				accountDao.getId(),
				accountDao.getAccountHolderName(),
				accountDao.getBalance()
		);
		return account;
	}
	
	public static AccountDao mapToAccountDao(Account account) {
		AccountDao accountDao = new AccountDao(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
		);
		return accountDao;
	}
}
