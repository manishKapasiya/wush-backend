package com.wush.account.resource;

import com.wush.account.model.Account;
import com.wush.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/accounts")
public class AccountResource {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account createAccount(
            @RequestBody Account accountCreateRequest){
        return accountService.saveAccountEntity(accountCreateRequest);
    }

    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable Long accountId){
        return accountService.findByAccountId(accountId);
    }
}
