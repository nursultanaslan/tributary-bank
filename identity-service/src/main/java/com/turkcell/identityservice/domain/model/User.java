package com.turkcell.identityservice.domain.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class User {

    private UUID userId;
    private String email;
    private String username;
    private List<Role> roles;
    private Instant createdAt;
    private boolean emailVerified;

    private User(UUID userId, String email, String username, List<Role> roles, Instant createdAt, boolean emailVerified) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.roles = roles;
        this.createdAt = createdAt;
        this.emailVerified = emailVerified;
    }

    public static User create(UUID userId, String email, String username, List<Role> roles, Instant createdAt, boolean emailVerified) {
        return new User(userId, email, username, roles, createdAt, emailVerified);
    }


    //getters
    public UUID userId() {
        return userId;
    }

    public String email() {
        return email;
    }

    public String username() {
        return username;
    }

    public List<Role> roles() {
        return roles;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public boolean emailVerified() {
        return emailVerified;
    }
}
