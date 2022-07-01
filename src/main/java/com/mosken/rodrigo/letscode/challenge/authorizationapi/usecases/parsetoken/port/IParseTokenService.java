package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.parsetoken.port;

public interface IParseTokenService {

    ParseTokenServiceResponse parseToken(String token);
}
