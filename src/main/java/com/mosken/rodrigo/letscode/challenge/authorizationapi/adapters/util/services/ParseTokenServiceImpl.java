package com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.util.services;

import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.util.Token;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.parsetoken.port.IParseTokenService;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.parsetoken.port.ParseTokenServiceResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParseTokenServiceImpl implements IParseTokenService {

    private final Token tokenUtil;

    @Override
    public ParseTokenServiceResponse parseToken(String token) {
        var response = tokenUtil.parseToken(token);
        return buildParsedToken(response);
    }

    private ParseTokenServiceResponse buildParsedToken(Jws<Claims> response) {
        var claims = response.getBody();
        return ParseTokenServiceResponse.builder()
                .tokenId(claims.getId())
                .subject(claims.getSubject())
                .issuedAt(claims.getIssuedAt())
                .expirationDate(claims.getExpiration())
                .build();
    }


}
