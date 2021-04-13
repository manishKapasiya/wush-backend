package com.wush.account.exception;

public class AccountCreateException extends RuntimeException {

    final String errorMessage;

    final String errorCode;

    public AccountCreateException(String errorMessage, String errorCode) {
        super();
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    public String getErrorCode() {
        return errorCode;
    }
}
