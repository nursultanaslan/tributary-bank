package com.turkcell.identityservice.domain.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class User {

    private UUID userId;
    private String email;
    private List<Role> roles;
    private Instant createdAt;
    private boolean emailVerified;

    private User(UUID userId, String email, List<Role> roles, Instant createdAt, boolean emailVerified) {
        this.userId = userId;
        this.email = email;
        this.roles = roles;
        this.createdAt = createdAt;
        this.emailVerified = emailVerified;
    }

}
