package com.turkcell.identityservice.application.usecase;

import com.turkcell.identityservice.domain.exception.KeycloakServiceException;
import com.turkcell.identityservice.domain.model.Role;
import com.turkcell.identityservice.domain.port.KeycloakPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class RegisterUserUseCase {

    private final KeycloakPort keycloakPort;

    public RegisterUserUseCase(KeycloakPort keycloakPort) {
        this.keycloakPort = keycloakPort;
    }

    public UUID execute(String email, String username, String password){
        log.info("Registering user with email {} and username {}", email, username);
        UUID userId = keycloakPort.createUser(email, username, password);

        try {
            keycloakPort.assignRoleToUser(userId, Role.CUSTOMER);
            log.info("Role successfully assigned to user: {}", userId);
            return userId;
        }catch (Exception e){
            log.warn("Exception passed to the assignRoleToUser method.");
            throw new KeycloakServiceException("Could not assign role to user");
        }
    }
}
