package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.port;

import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.mysql.domain.UserBean;

import java.util.Map;
import java.util.Optional;

public interface ILogInService {

    Optional<UserBean> verifyUserByUsername(String username, String password);
    String generateToken(Map<String, Object> map);
}
