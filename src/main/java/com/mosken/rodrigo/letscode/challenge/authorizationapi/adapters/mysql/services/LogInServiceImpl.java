package com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.mysql.services;

import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.mysql.domain.UserBean;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.mysql.repositories.UserRepository;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.util.Token;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.port.ILogInService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogInServiceImpl implements ILogInService {

    private final UserRepository userRepository;
    private final Token tokenUtil;

    @Override
    public Optional<UserBean> verifyUserByUsername(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public String generateToken(Map<String, Object> map) {
        return tokenUtil.createToken(map);
    }

}
