package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.exception;


import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.exceptions.LogInException;

public class AttemptsExceededException extends LogInException {
    public AttemptsExceededException(String msg){super(msg);}
}
