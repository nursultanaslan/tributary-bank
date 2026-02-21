package com.turkcell.identityservice.application.usecase;

import com.turkcell.identityservice.domain.model.User;
import com.turkcell.identityservice.domain.port.KeycloakPort;
import org.springframework.stereotype.Service;

@Service
public class GetUserProfileUseCase {

    private final KeycloakPort keycloakPort;

    public GetUserProfileUseCase(KeycloakPort keycloakPort) {
        this.keycloakPort = keycloakPort;
    }

    public User execute(String userId){
        return keycloakPort.getUserById(userId);
    }

}
