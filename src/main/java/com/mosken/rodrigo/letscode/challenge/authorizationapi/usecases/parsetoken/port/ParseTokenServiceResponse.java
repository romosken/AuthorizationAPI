package com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.parsetoken.port;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class ParseTokenServiceResponse {

        private String tokenId;
        private String subject;
        private Date issuedAt;
        private Date expirationDate;
}
