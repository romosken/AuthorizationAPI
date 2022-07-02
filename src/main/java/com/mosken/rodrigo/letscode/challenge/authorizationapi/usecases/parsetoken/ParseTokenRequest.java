package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.parsetoken;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ParseTokenRequest {

    private String token;
}
