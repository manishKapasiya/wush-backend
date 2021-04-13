package com.wush.account.db.repository;

import com.wush.account.db.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Long> {
}
