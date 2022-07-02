package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.port;

public interface IManageLogInAttemptsService {
    void insertFailedLogIn(String username);
    FailedLogInAttempts getFailedLogInAttempts(String username);
    void deleteFailedAttempts(String username);

}
