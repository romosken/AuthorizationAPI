package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LogInRequest {

    private String username;
    private String password;
}
