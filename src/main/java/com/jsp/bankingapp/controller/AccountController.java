package com.jsp.bankingapp.controller;

import java.util.List;
import java.util.Map;

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

import com.jsp.bankingapp.dao.AccountDao;
import com.jsp.bankingapp.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	// Add Account REST API
	@PostMapping 
	public ResponseEntity<AccountDao> addAccount(@RequestBody AccountDao accountDao){
		return new ResponseEntity<>(accountService.createAccount(accountDao),HttpStatus.CREATED);
	}
	
	// Get Account REST API
	@GetMapping("/{id}")
	public ResponseEntity<AccountDao> getAccountById(@PathVariable long id){
		AccountDao accountDao = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDao);
	}
	
	// Deposit REST API
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDao> deposit(@PathVariable long id, @RequestBody Map<String, Double>request){
		Double amount = request.get("amount");
		AccountDao accountDao = accountService.deposit(id,amount);
		return ResponseEntity.ok(accountDao);
	}
	
	// Withdraw REST API
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDao> withdraw(@PathVariable long id, @RequestBody Map<String, Double>request) {
		Double amount = request.get("amount");
		AccountDao accountDao = accountService.withdraw(id, amount);
		return ResponseEntity.ok(accountDao);
	}
	
	// GetAll Account REST API
	@GetMapping
	public ResponseEntity<List<AccountDao>> getAllAccounts(){
		List<AccountDao> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
	
	//Delete Account REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account Deleted Successfully");
	}
}
