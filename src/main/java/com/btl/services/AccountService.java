package com.btl.services;

import com.btl.entities.Account;
import com.btl.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

	public Optional<Account> getAccountByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

    // Thêm service method khác nếu cần
}
