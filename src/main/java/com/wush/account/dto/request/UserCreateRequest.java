package com.wush.account.dto.request;

import com.sun.istack.NotNull;
import com.wush.account.constants.AccountType;

public class UserCreateRequest {

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String country;

    @NotNull
    private String mobile;

    @NotNull
    private AccountType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
