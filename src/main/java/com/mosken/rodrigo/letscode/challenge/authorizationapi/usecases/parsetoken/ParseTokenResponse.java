package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.parsetoken;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class ParseTokenResponse {

    @JsonProperty("id")
    private String tokenId;
    private String subject;
    @JsonProperty("issued_at")
    private Date issuedAt;
    @JsonProperty("expiration_date")
    private Date expirationDate;
}
