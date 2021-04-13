package com.wush.account.service;

import com.wush.account.constants.Constants;
import com.wush.account.db.UserRepository;
import com.wush.account.dto.mapper.AccountToAccountDetailsMapper;
import com.wush.account.dto.mapper.UserCreateRequestToUserMapper;
import com.wush.account.dto.mapper.UserToUserCreateResponseMapper;
import com.wush.account.dto.mapper.UserToUserDetailsMapper;
import com.wush.account.dto.request.UserCreateRequest;
import com.wush.account.dto.response.UserCreateResponse;
import com.wush.account.dto.response.UserDetails;
import com.wush.account.exception.AccountCreateException;
import com.wush.account.exception.UserCreateException;
import com.wush.account.exception.UserSearchException;
import com.wush.account.model.Account;
import com.wush.account.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserervice{

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserToUserDetailsMapper userToUserDetailsMapper;

    @Autowired
    public UserCreateRequestToUserMapper userCreateRequestToUserMapper;

    @Autowired
    public UserToUserCreateResponseMapper userToUserCreateResponseMapper;

    @Autowired
    public AccountToAccountDetailsMapper accountToAccountDetailsMapper;

    @Autowired
    public AccountService accountService;

    @Override
    public UserDetails findUserById(Long userId) {
        UserDetails userDetails = null;
        try {
            Optional<User> optional = userRepository.findById(userId);
            if (optional.isPresent()) {
                User user = optional.get();
                userDetails = userToUserDetailsMapper.map(user);
                userDetails.setAccountDetails(accountService.findByAccountId(user.getAccount().getId()));
            }
        }catch (Exception e){
            throw new UserSearchException("UserSearchException","UserSearchCode");
        }
        return userDetails;
    }

    @Override
    public List<UserDetails> getUserWithMobile(String mobile){
        List<User> users = userRepository.getUserWithMobileAndStatus(String.valueOf(mobile), Constants.USER_INTERNAL_SUCCESS);
        return users.stream().map(user -> userToUserDetailsMapper.map(user)).collect(Collectors.toList());
    }

    @Override
    public UserCreateResponse saveUserEntity(UserCreateRequest userRequest) {
        preValidation(userRequest);
        User user = userCreateRequestToUserMapper.map(userRequest);
        user.setAccount(dbSaveAccount(userRequest));
        user = dbSaveUser(user);
        return userToUserCreateResponseMapper.map(user);
    }

    private void preValidation(UserCreateRequest userCreateRequest){
        mobileValidation(userCreateRequest);
        //countryMobileValidation(userCreateRequest);
    }

    private void mobileValidation(UserCreateRequest userCreateRequest){
        if (getUserWithMobile(userCreateRequest.getMobile()).size()>0){
            throw new UserCreateException("Uer exist with same mobile",Constants.USER_EXTERNAL_FAILED);
        }
    }

    private Account dbSaveAccount(UserCreateRequest userRequest){
        Account account = new Account();
        account.setType(userRequest.getType());
        account.setExternalStatus(Constants.ACCOUNT_EXTERNAL_SUCCESS);
        account.setInternalStatus(Constants.ACCOUNT_INTERNAL_SUCCESS);
        try{
            account = accountService.saveAccountEntity(account);
        }catch (Exception e){
            throw new AccountCreateException("AccountCreationFailed",Constants.ACCOUNT_EXTERNAL_FAILED);
        }
        return account;
    }

    private User dbSaveUser(User user){
        user.setInternalStatus(Constants.USER_INTERNAL_SUCCESS);
        user.setExternalStatus(Constants.USER_EXTERNAL_SUCCESS);
        try {
            user = userRepository.save(user);
        }catch (Exception e){
            throw new UserCreateException("UserCreateException", Constants.USER_EXTERNAL_FAILED);
        }
        return user;
    }
}
