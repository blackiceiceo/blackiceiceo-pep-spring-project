package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.AccountCreationException;
import com.example.exception.AccountDupeUsernameException;
import com.example.exception.AccountMisMatchException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public void addAccount(Account account){
        if(account.getPassword().length() < 4 || account.getUsername().isBlank()) {
            throw new AccountCreationException();
        }

        Optional<Account> optAcc = accountRepository.findAccountByUsername(account.getUsername());
        if(optAcc.isPresent()){
            throw new AccountDupeUsernameException();
        }

        accountRepository.save(account);
    }
    

    public Account userLogin(Account account) {
        Optional<Account> optAcc = accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        
        if(optAcc.isPresent()){
            Account acc = optAcc.get();
            return acc;
        }else{
            throw new AccountMisMatchException();
        }
    }


}
