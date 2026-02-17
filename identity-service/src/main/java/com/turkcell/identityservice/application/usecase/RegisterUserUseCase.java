package com.turkcell.identityservice.application.usecase;

import com.turkcell.identityservice.domain.port.KeycloakPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterUserUseCase {

    private final KeycloakPort keycloakPort;

    public RegisterUserUseCase(KeycloakPort keycloakPort) {
        this.keycloakPort = keycloakPort;
    }

    public UUID createUser(String email, String password){
        //email ve şifresi alınan yeni kullanıcı kaydı oluşturulur.
        UUID userId = keycloakPort.createUser(email, password);
        //kullanıcı kaydı başarılıysa idsi return edilir.
        return userId;
    }
}
