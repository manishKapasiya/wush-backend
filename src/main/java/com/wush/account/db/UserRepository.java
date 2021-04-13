package com.wush.account.db;

import com.wush.account.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    @Query(value = "select u from User u where u.mobile=?1 and u.internalStatus=?2")
    List<User> getUserWithMobileAndStatus(String mobile, String internal_status);

}
