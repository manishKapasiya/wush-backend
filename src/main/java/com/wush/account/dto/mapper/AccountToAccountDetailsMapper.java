package com.wush.account.dto.mapper;

import com.wush.account.dto.response.AccountDetails;
import com.wush.account.model.Account;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AccountToAccountDetailsMapper implements BeanMapper<Account, AccountDetails> {
    @Override
    public AccountDetails map(Account source) {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(source.getId());
        accountDetails.setType(source.getType().name());
        accountDetails.setAccountStatus(source.getExternalStatus());
        accountDetails.setUserId(String.valueOf(source.getUser().getId()));
        return accountDetails;
    }

    @Override
    public AccountDetails map(Account source, Map<String, String> headers) {
        return null;
    }
}
