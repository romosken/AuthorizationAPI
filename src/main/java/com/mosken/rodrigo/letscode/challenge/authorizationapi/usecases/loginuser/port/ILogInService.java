package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.port;

import com.mosken.rodrigo.letscode.challenge.authorizationapi.domain.dto.UserDto;

import java.util.Map;

public interface ILogInService {

    UserDto verifyUserByUsername(String username, String password);
    UserDto verifyUserByEmail(String email, String password);
    String generateToken(Map<String, Object> map);
}
