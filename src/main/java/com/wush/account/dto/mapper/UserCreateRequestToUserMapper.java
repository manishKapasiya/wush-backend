package com.wush.account.dto.mapper;

import com.wush.account.dto.request.UserCreateRequest;
import com.wush.account.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserCreateRequestToUserMapper implements BeanMapper<UserCreateRequest, User> {
    @Override
    public User map(UserCreateRequest source) {
        User user = new User();
        user.setName(source.getName());
        user.setAddress(source.getAddress());
        user.setCountry(source.getCountry());
        user.setMobile(source.getMobile());
        return user;
    }

    @Override
    public User map(UserCreateRequest source, Map<String, String> headers) {
        return null;
    }
}
