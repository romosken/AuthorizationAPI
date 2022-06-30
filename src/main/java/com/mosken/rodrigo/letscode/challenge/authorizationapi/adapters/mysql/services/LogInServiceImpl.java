package com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.mysql.services;

import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.exceptions.LogInException;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.mysql.domain.UserBean;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.mysql.repositories.UserRepository;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.util.Token;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.domain.dto.UserDto;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.port.ILogInService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LogInServiceImpl implements ILogInService {

    private final UserRepository userRepository;
    private final Token tokenUtil;

    private static final String LOG_IN_FAILED = "Username/email or password incorrect!";

    @Override
    public UserDto verifyUserByUsername(String username, String password) {
        var user = userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(()-> new LogInException(LOG_IN_FAILED));
        return buildUserDto(user);
    }

    @Override
    public UserDto verifyUserByEmail(String email, String password) {
        var user = userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(()-> new LogInException(LOG_IN_FAILED));
        return buildUserDto(user);
    }

    @Override
    public String generateToken(Map<String, Object> map) {
        return tokenUtil.createToken(map);
    }

    private UserDto buildUserDto(UserBean userSaved) {
     return UserDto.builder()
                .username(userSaved.getUsername())
                .email(userSaved.getEmail())
                .password(userSaved.getPassword())
                .xp(userSaved.getXp())
                .role(userSaved.getRole().getName())
                .build();
    }
}
