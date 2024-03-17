package com.jsp.bankingapp.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.jsp.bankingapp.dao.AccountDao;
import com.jsp.bankingapp.entity.Account;
import com.jsp.bankingapp.mapper.AccountMapper;
import com.jsp.bankingapp.repository.AccountRepository;
import com.jsp.bankingapp.service.AccountService;


@Service
public class AccountServiceImplementation implements AccountService{
	
	private AccountRepository accountRepository;
	
	public AccountServiceImplementation(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public AccountDao createAccount(AccountDao accountDao) {
		Account account = AccountMapper.mapToAccount(accountDao);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDao(savedAccount);
	}

	@Override
	public AccountDao getAccountById(long id) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account doesnot exist"));
		return AccountMapper.mapToAccountDao(account);
	}

	@Override
	public AccountDao deposit(long id, double amount) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account doesnot exist"));
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDao(savedAccount);
	}

	@Override
	public AccountDao withdraw(long id, double amount) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account doesnot exist"));
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient Balance");
		}
		double total = account.getBalance()-amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDao(savedAccount);
	}

	@Override
	public List<AccountDao> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDao(account)).collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(long id) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account doesnot exist"));
		accountRepository.deleteById(id);
	}

}
