package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.verifytoken;

import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.verifytoken.exception.InvalidTokenException;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.verifytoken.port.IVerifyTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyTokenImpl implements IVerifyToken {

    private final IVerifyTokenService iVerifyTokenService;
    private static final String INVALID_TOKEN = "Token expired or invalid!";


    @Override
    public VerifyTokenResponse verifyToken(VerifyTokenRequest request) {
        try{
        var validToken = iVerifyTokenService.verifyToken(request.getToken());
        return buildVerifyTokenResponse(validToken) ;
    }catch(Exception e){
            throw new InvalidTokenException(INVALID_TOKEN);
        }

    }

    private VerifyTokenResponse buildVerifyTokenResponse(boolean validToken) {
        return VerifyTokenResponse.builder().validToken(validToken).build();
    }



}
