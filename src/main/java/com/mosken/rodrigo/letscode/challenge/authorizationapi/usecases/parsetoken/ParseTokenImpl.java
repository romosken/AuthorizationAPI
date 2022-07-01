package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.parsetoken;

import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.parsetoken.exception.InvalidTokenException;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.parsetoken.port.IParseTokenService;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.parsetoken.port.ParseTokenServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParseTokenImpl implements IParseToken {

    private final IParseTokenService iParseTokenService;
    private static final String INVALID_TOKEN = "Token expired or invalid!";


    @Override
    public ParseTokenResponse parseToken(ParseTokenRequest request) {
        try{
        var parsedToken = iParseTokenService.parseToken(request.getToken());
        return buildParseTokenResponse(parsedToken) ;
    }catch(Exception e){
            throw new InvalidTokenException(INVALID_TOKEN);
        }

    }

    private ParseTokenResponse buildParseTokenResponse(ParseTokenServiceResponse token) {
        return ParseTokenResponse.builder()
                .tokenId(token.getTokenId())
                .subject(token.getSubject())
                .issuedAt(token.getIssuedAt())
                .expirationDate(token.getExpirationDate())
                .build();
    }



}
