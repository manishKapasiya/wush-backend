package com.wush.account.service;

import com.wush.account.dao.AccountRepository;
import com.wush.account.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    public AccountRepository accountRepository;

    @Override
    public Account findByAccountId(Long accountId) {
        Optional<Account> optional = accountRepository.findById(accountId);
        if(optional.isPresent()) return optional.get();
        return null;
    }

    @Override
    public Account saveAccountEntity(Account account) {
        return accountRepository.save(account);
    }
}
