package com.mosken.rodrigo.letscode.challenge.authorizationapi.domain.enums;

public enum ERole {
    LEITOR("LEITOR"),
    BASICO("BASICO"),
    AVANCADO("AVANCADO"),
    MODERADOR("MODERADOR");

    private final String value;

    ERole(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
