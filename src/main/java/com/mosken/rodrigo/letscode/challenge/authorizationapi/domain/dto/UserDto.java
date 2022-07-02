package com.mosken.rodrigo.letscode.challenge.authorizationapi.domain.dto;

import com.mosken.rodrigo.letscode.challenge.authorizationapi.domain.enums.ERole;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserDto {

    private String username;
    private String password;
    private String email;
    private int xp;
    private ERole role;
}
