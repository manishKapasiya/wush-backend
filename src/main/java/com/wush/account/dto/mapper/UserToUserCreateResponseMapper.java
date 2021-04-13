package com.wush.account.dto.mapper;

import com.wush.account.dto.response.UserCreateResponse;
import com.wush.account.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserToUserCreateResponseMapper implements BeanMapper<User, UserCreateResponse> {
    @Override
    public UserCreateResponse map(User source) {
        UserCreateResponse userCreateResponse = new UserCreateResponse();
        userCreateResponse.setId(source.getId());
        userCreateResponse.setName(source.getName());
        userCreateResponse.setAddress(source.getAddress());
        userCreateResponse.setCountry(source.getCountry());
        userCreateResponse.setMobile(source.getMobile());
        userCreateResponse.setStatus(source.getExternalStatus());
        userCreateResponse.setAccountId(source.getAccount().getId());
        return userCreateResponse;
    }

    @Override
    public UserCreateResponse map(User source, Map<String, String> headers) {
        return null;
    }
}
