package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.verifytoken;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VerifyTokenResponse {

    @JsonProperty("valid_token")
    private boolean validToken;
}
