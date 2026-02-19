package com.turkcell.identityservice.domain.model;

public enum Role {
    CUSTOMER,
    ADMIN;

    public static Role fromString(String roleStr) {
        if (roleStr == null) {
            return null;
        }

        String normalized = roleStr.toUpperCase();
        try {
            return Role.valueOf(normalized);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Role '%s' is not a valid Role", normalized));
        }

    }

    public String toKeycloakRole() {
        return this.name().toLowerCase();
    }
}
