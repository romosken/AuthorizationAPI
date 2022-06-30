package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.verifytoken;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VerifyTokenRequest {

    private String token;
}
