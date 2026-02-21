package com.turkcell.identityservice.domain.port;

import com.turkcell.identityservice.domain.model.Role;
import com.turkcell.identityservice.domain.model.User;

import java.util.UUID;

/**
 * Port interface for Keycloak integration.
 * Infrastructure layer implements this interface.
 * Application layer depends on this port (hexagonal architecture).
 */
public interface KeycloakPort {
    /**
     * createUser: Create new user in Keycloak
     * getUserById: Get user information by user ID
     * assignRoleToUser: Assign role to user
     * deleteUser: Delete user registration from keycloak when role assignment fails
     */
    UUID createUser(String email, String username, String password);

    User getUserById(String userId);

    void assignRoleToUser(UUID userId, Role role);

    void deleteUser(UUID userId);

}
