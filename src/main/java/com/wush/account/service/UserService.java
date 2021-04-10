package com.wush.account.service;

import com.wush.account.dao.UserRepository;
import com.wush.account.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserervice{

    @Autowired
    public UserRepository userRepository;

    @Override
    public User findUserById(Long userId) {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isPresent()) return optional.get();
        return null;
    }

    @Override
    public User saveUserEntity(User user) {
        return userRepository.save(user);
    }
}
