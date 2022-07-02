package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.exception;


import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.exceptions.LogInException;

public class InvalidUserException extends LogInException {
    public InvalidUserException(String msg){super(msg);}
}
