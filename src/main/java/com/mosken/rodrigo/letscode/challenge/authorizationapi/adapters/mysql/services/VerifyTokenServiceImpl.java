package com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.mysql.services;

import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.exceptions.LogInException;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.mysql.domain.UserBean;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.util.Token;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.domain.dto.UserDto;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.verifytoken.port.IVerifyTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyTokenServiceImpl implements IVerifyTokenService {

    private final Token tokenUtil;

    @Override
    public boolean verifyToken(String token) {
        return tokenUtil.verifyToken(token);
    }


}
