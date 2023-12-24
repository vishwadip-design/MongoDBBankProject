package com.banking.controller;

import com.banking.exception.AccountNotFoundException;
import com.banking.model.Account;
import com.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account accountCreated = accountService.createAccount(account);
        return new ResponseEntity<>(accountCreated,HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Account>getAccountById(@PathVariable String id){
        
        Optional<Account> getAccountById= accountService.getAccountById(id);
        return getAccountById.map(account -> new ResponseEntity(getAccountById,HttpStatus.ACCEPTED))
                .orElseThrow(()-> new AccountNotFoundException("account not found with id:"+id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Account>updateAccount(@PathVariable String id,@RequestBody Account account){
        Account updateAccount = accountService.getAccountById(id)
                .map(existingAccount->{
                                        existingAccount.setAccountNumber(account.getAccountNumber());
                                        existingAccount.setAccountHolder(account.getAccountHolder());
                                        existingAccount.setBalance(account.getBalance());
                                        return accountService.updateAccount(existingAccount);
                })
                .orElseThrow(()->new AccountNotFoundException("no account is exist with id :"+id));
              return new ResponseEntity(updateAccount,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id){

        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.GONE);

    }
}
