package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser;

import com.mosken.rodrigo.letscode.challenge.authorizationapi.domain.dto.UserDto;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.entities.Role;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.entities.User;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.exception.InvalidPasswordException;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.exception.InvalidUserException;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.port.ILogInService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogInImpl implements ILogIn {

    private final ILogInService iLogInService;
    private static final String LOG_IN_FAILED = "Username/email or password invalid!";

    private static final long EXPIRATION_TIME = 3_600_000;

    @Override
    public LogInResponse logIn(LogInRequest request) {

        var validatedPassword = validatePassword(request.getPassword());

        var user = getUserValidated(request.getUsername(), request.getEmail(), validatedPassword);

        return buildLogInResponse(user);
    }

    private String validatePassword(String password) {
        if (validateString(password))
            throw new InvalidPasswordException(LOG_IN_FAILED);
        return encryptPassword(password);

    }

    private User getUserValidated(String username, String email, String password) {

        if (validateString(username))
            return buildUserEntity(iLogInService.verifyUserByUsername(username, password));
        if (validateString(email))
            return buildUserEntity(iLogInService.verifyUserByUsername(username, password));

        throw new InvalidUserException(LOG_IN_FAILED);
    }

    private boolean validateString(String text) {

        return !(Objects.isNull(text) || text.isBlank());
    }

    private User buildUserEntity(UserDto user) {
        return User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .xp(user.getXp())
                .role(Role.builder().name(user.getRole()).build())
                .build();
    }

    private LogInResponse buildLogInResponse(User response) {
        return LogInResponse.builder()
                .token(iLogInService.generateToken(Map.of(
                        "sub", response.getUsername(),
                        "exp", new Date(System.currentTimeMillis() + EXPIRATION_TIME),
                        "iat", new Date(),
                        "jti", UUID.randomUUID().toString()
                )))
                .build();
    }

    @SneakyThrows
    private String encryptPassword(String password) {
        var algorithm = MessageDigest.getInstance("SHA-256");
        var messageDigest = algorithm.digest(password.getBytes(StandardCharsets.UTF_8));
        var hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }


}
