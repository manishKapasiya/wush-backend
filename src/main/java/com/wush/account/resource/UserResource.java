package com.wush.account.resource;

import com.wush.account.dto.request.UserCreateRequest;
import com.wush.account.dto.response.UserCreateResponse;
import com.wush.account.dto.response.UserDetails;
import com.wush.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserResource {

    @Autowired
    public UserService userService;

    @PostMapping
    public ResponseEntity<UserCreateResponse> saveUser(@RequestBody UserCreateRequest userEntity){
            return new ResponseEntity<>(userService.saveUserEntity(userEntity), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public UserDetails getUser(@PathVariable Long userId){
        return userService.findUserById(userId);
    }
}
