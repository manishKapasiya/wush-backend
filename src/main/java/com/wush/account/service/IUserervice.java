package com.wush.account.service;

import com.wush.account.model.User;

public interface IUserervice {

    User findUserById(Long userId);
    User saveUserEntity(User user);
}
