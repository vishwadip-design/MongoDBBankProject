package com.banking.service.impl;

import com.banking.model.Account;
import com.banking.repository.AccountRepository;
import com.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        Account account1 = accountRepository.save(account);
        return account1;
    }

    @Override
    public Optional<Account> getAccountById(String id) {

        Optional<Account> account = accountRepository.findById(id);
        return account;
    }

    @Override
    public Account updateAccount(Account account) {

        Account account1 = accountRepository.save(account);
        return account1;
    }

    @Override
    public void deleteAccount(String id) {
                accountRepository.deleteById(id);
    }
}
