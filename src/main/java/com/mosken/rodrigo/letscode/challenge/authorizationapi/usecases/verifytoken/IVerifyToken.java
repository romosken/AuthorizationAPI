package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.verifytoken;

public interface IVerifyToken {

    VerifyTokenResponse verifyToken(VerifyTokenRequest request);
}
