package com.wush.account.resource;

import com.wush.account.dto.response.AccountDetails;
import com.wush.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/accounts")
public class AccountResource {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountId}")
    public AccountDetails getAccountById(@PathVariable Long accountId){
        return accountService.findByAccountId(accountId);
    }
}
