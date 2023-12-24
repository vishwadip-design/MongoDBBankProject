package com.banking.service;

import com.banking.model.Account;

import java.util.Optional;

public interface AccountService {

    public Account createAccount(Account account);

    public Optional<Account>getAccountById(String id);

    public Account updateAccount(Account account);

    public void deleteAccount(String id);
}
