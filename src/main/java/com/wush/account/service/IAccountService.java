package com.wush.account.service;

import com.wush.account.model.Account;

public interface IAccountService {

    Account findByAccountId(Long accountId);
    Account saveAccountEntity(Account account);

}
