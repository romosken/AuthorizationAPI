package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser;

import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.mysql.domain.UserBean;
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
import java.util.*;

@Service
@RequiredArgsConstructor
public class LogInImpl implements ILogIn {

    private final ILogInService iLogInService;
//    private final IManageLogInAttemptsService iManageLogInAttemptsService;
    private static final String LOG_IN_FAILED = "Username/email or password invalid!";
    private static final String ATTEMPTS_EXCEEDED = "Log in attempts exceeded, try again later!";
    private static final long EXPIRATION_TIME = 3_600_000;

    @Override
    public LogInResponse logIn(LogInRequest request) {

        var validatedRequest = validateRequest(request);

        var optionalUser = iLogInService.verifyUserByUsername(validatedRequest.getUsername(), validatedRequest.getPassword());

        var validatedUser = validateUser(optionalUser, validatedRequest);

        var user = buildUserEntity(validatedUser);

        return buildLogInResponse(user);
    }

    private LogInRequest validateRequest(LogInRequest request) {
        if (validateString(request.getUsername()))
            throw new InvalidUserException(LOG_IN_FAILED);

        var password = request.getPassword();

        if (validateString(password))
            manageAttempt(request);

        request.setPassword(encryptPassword(password));

        return request;
    }

    private boolean validateString(String text) {

        return Objects.isNull(text) || text.isBlank();
    }

    private void manageAttempt(LogInRequest request) {

//        var username = request.getUsername();
//        if (iManageLogInAttemptsService.getFailedLogInAttempts(username).getAttempts() > 3)
//            throw new AttemptsExceededException(ATTEMPTS_EXCEEDED);

//        iManageLogInAttemptsService.insertFailedLogIn(username);
        throw new InvalidPasswordException(LOG_IN_FAILED);
    }

    private UserBean validateUser(Optional<UserBean> optionalUser, LogInRequest request) {

        if (optionalUser.isPresent()) {
//            iManageLogInAttemptsService.deleteFailedAttempts(request.getUsername());
            return optionalUser.get();
        }
//        iManageLogInAttemptsService.insertFailedLogIn(request.getUsername());
        throw new InvalidPasswordException(LOG_IN_FAILED);
    }

    private User buildUserEntity(UserBean user) {

        return User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .xp(user.getXp())
                .role(Role.builder().name(user.getRole().getName()).build())
                .build();
    }

    private LogInResponse buildLogInResponse(User response) {
        return LogInResponse.builder()
                .token(iLogInService.generateToken(Map.of(
                        "sub", response.getUsername(),
                        "iat", new Date().toString(),
                        "exp", new Date(System.currentTimeMillis() + EXPIRATION_TIME).toString(),
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
