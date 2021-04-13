package com.wush.account.dto.mapper;

import com.wush.account.dto.response.UserDetails;
import com.wush.account.db.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserToUserDetailsMapper implements BeanMapper<User, UserDetails> {
    @Override
    public UserDetails map(User source) {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(source.getId());
        userDetails.setName(source.getName());
        userDetails.setAddress(source.getAddress());
        userDetails.setCountry(source.getCountry());
        userDetails.setMobile(source.getMobile());
        userDetails.setStatus(source.getExternalStatus());
        return userDetails;
    }

    @Override
    public UserDetails map(User source, Map<String, String> headers) {
        return null;
    }
}
