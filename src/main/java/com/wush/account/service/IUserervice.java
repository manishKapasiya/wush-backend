package com.wush.account.service;

import com.wush.account.dto.request.UserCreateRequest;
import com.wush.account.dto.response.UserCreateResponse;
import com.wush.account.dto.response.UserDetails;

import java.util.List;

public interface IUserervice {

    UserDetails findUserById(Long userId);
    UserCreateResponse saveUserEntity(UserCreateRequest user);
    List<UserDetails> getUserWithMobile(String mobile);
}
