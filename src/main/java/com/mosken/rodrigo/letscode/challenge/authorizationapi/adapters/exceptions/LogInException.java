package com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.exceptions;


import java.util.NoSuchElementException;

public class LogInException extends NoSuchElementException {
    public LogInException(String msg){super(msg);}
}
