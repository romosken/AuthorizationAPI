package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.port;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class FailedLogInAttempts implements Serializable {

    private String username;
    private Integer attempts;
}
