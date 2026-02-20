package com.turkcell.identityservice.application.usecase;

import com.turkcell.identityservice.domain.exception.KeycloakServiceException;
import com.turkcell.identityservice.domain.model.Role;
import com.turkcell.identityservice.domain.port.KeycloakPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterUserUseCase {

    private final KeycloakPort keycloakPort;

    public RegisterUserUseCase(KeycloakPort keycloakPort) {
        this.keycloakPort = keycloakPort;
    }

    public UUID execute(String email, String username, String password){
        //email ve şifresi alınan yeni kullanıcı kaydı oluşturulur.
        UUID userId = keycloakPort.createUser(email, username, password);

        try {
            keycloakPort.assignRoleToUser(userId, Role.CUSTOMER);
            return userId;
        }catch (KeycloakServiceException e){
            throw new KeycloakServiceException("Could not assign role to user");
        }
    }
}
