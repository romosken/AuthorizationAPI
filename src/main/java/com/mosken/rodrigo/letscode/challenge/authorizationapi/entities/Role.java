package com.mosken.rodrigo.letscode.challenge.authorizationapi.entities;


import com.mosken.rodrigo.letscode.challenge.authorizationapi.entities.exceptions.UserException;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.domain.enums.ERole;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;


@Builder
@Data
public class Role {
    private ERole name;

    public Role(ERole name) {
        setName(name);
    }

    public void setName(ERole name) {
        validateAttributeObject(name, "ROLE_NAME");
        this.name = name;
    }

    private void validateAttributeObject(Object attribute, String attributeName) {
        if (Objects.isNull(attribute))
            throw new UserException(attributeName + " cannot be null or empty!");

    }

    public static class RoleBuilder {
        public Role build() {
            return new Role(
                    name
            );
        }
    }
}