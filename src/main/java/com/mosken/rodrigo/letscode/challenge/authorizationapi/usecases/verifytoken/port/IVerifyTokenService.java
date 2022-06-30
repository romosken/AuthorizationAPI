package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.verifytoken.port;

public interface IVerifyTokenService {

    boolean verifyToken(String token);
}
