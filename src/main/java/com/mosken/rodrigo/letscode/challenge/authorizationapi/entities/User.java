package com.mosken.rodrigo.letscode.challenge.authorizationapi.entities;

import com.mosken.rodrigo.letscode.challenge.authorizationapi.entities.exceptions.UserException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String email;
    private int xp;
    private Role role;

    public User(String username, String password, String email, int xp, Role role) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setXp(xp);
        setRole(role);
    }

    public void setUsername(String username) {
        validateAttribute(username, "USERNAME");
        this.username = username;
    }

    public void setEmail(String email) {
        validateAttribute(email, "EMAIL");
        this.email = email;
    }

    public void setXp(int xp) {
        validateAttributeObject(xp, "XP");
        this.xp = xp;
    }

    public void setRole(Role role) {
        validateAttributeObject(role, "ROLE");
        this.role = role;
    }

    private void validateAttribute(String attribute, String attributeName) {
        if (Objects.isNull(attribute) || attribute.isBlank())
            throw new UserException(attributeName + " cannot be null or empty!");

    }

    private void validateAttributeObject(Object attribute, String attributeName) {
        if (Objects.isNull(attribute))
            throw new UserException(attributeName + " cannot be null or empty!");

    }

    public static class UserBuilder {
        public User build() {
            return new User(
                    username,
                    password,
                    email,
                    xp,
                    role
            );
        }
    }
}