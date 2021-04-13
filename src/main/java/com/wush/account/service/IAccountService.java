package com.wush.account.service;

import com.wush.account.dto.response.AccountDetails;
import com.wush.account.db.model.Account;

public interface IAccountService {

    AccountDetails findByAccountId(Long accountId);
    Account saveAccountEntity(Account account);

}
