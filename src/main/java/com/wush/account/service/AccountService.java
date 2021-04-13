package com.wush.account.service;

import com.wush.account.db.repository.AccountRepository;
import com.wush.account.dto.mapper.AccountToAccountDetailsMapper;
import com.wush.account.dto.response.AccountDetails;
import com.wush.account.db.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    public AccountToAccountDetailsMapper accountToAccountDetailsMapper;

    @Override
    public AccountDetails findByAccountId(Long accountId) {
        AccountDetails accountDetails = null;
        Optional<Account> optional = accountRepository.findById(accountId);
        if(optional.isPresent()) {
            accountDetails = accountToAccountDetailsMapper.map(optional.get());
        }
        return accountDetails;
    }

    @Override
    public Account saveAccountEntity(Account account) {
        if (account==null) return null;
        return accountRepository.save(account);
    }
}
