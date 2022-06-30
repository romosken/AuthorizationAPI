package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.exception;


import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.exceptions.LogInException;

public class InvalidPasswordException extends LogInException {
    public InvalidPasswordException(String msg){super(msg);}
}
