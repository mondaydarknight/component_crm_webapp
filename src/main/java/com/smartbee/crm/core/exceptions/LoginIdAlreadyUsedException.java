package com.smartbee.crm.core.exceptions;

public class LoginIdAlreadyUsedException extends RuntimeException {

    public LoginIdAlreadyUsedException() {}

    public LoginIdAlreadyUsedException(String message) {
        super(message);
    }
}
