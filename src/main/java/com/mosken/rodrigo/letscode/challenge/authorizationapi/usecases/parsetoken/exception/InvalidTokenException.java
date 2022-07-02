package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.parsetoken.exception;

import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.exceptions.InvalidResourceException;

public class InvalidTokenException extends InvalidResourceException {
    public InvalidTokenException(String msg){super(msg);}
}
