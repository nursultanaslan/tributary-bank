package com.turkcell.identityservice.infrastructure.keycloak;

import com.turkcell.identityservice.domain.exception.KeycloakServiceException;
import com.turkcell.identityservice.domain.model.Role;
import com.turkcell.identityservice.domain.model.User;
import com.turkcell.identityservice.domain.port.KeycloakPort;
import com.turkcell.identityservice.infrastructure.config.KeycloakProperties;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Keycloak adapter implementing KeycloakPort.
 * Handles all Keycloak Admin API and Token Endpoint interactions.
 */
@Component
public class KeycloakClientAdapter implements KeycloakPort {


    private final Keycloak keycloakAdminClient;
    private final KeycloakProperties keycloakProperties;

    public KeycloakClientAdapter(Keycloak keycloakAdminClient, KeycloakProperties keycloakProperties) {
        this.keycloakAdminClient = keycloakAdminClient;
        this.keycloakProperties = keycloakProperties;
    }


    @Override
    public UUID createUser(String email, String password) {

        //Realm'e bağlan (GET /admin/realms/{realm})
        RealmResource realmResource = keycloakAdminClient.realm(keycloakProperties.getRealm());
        //o Realm içerisindeki kullanıcı endpointine erişir (GET /admin/realms/{realm}/users)
        UsersResource usersResource = realmResource.users();

        //Create user representation
        UserRepresentation user = getUserRepresentation(email, password);

        //user create edilip Response nesnesine atanır
        //bu Response nesnesinin Header kısmı Location bilgisi içerir.
        try (Response response = usersResource.create(user)) {

            if (response.getStatus() != 201) {
                String errorBody = response.readEntity(String.class);
                throw new KeycloakServiceException("Keycloak'ta kullanıcı oluşturulamadı: HTTP " + response.getStatus() + " - " + errorBody);
            }

            //Extract userID from Location Header
            //Location'dan kayıt olan kullanıcının uuid bilgisini alırız.
            String locationHeader = response.getHeaderString("Location");
            String userId = locationHeader.substring(locationHeader.lastIndexOf("/") + 1);
            UUID userUUID = UUID.fromString(userId);

            //Assign default 'customer' role
            assignRoleToUser(userUUID);

            return userUUID;
        }

    }

    @Override
    public User getUserById(UUID userId) {
        return null;
    }


    //Helper Methods
    private void assignRoleToUser(UUID userId) {
        try {
            //realm'e bağlan.
            RealmResource realmResource = keycloakAdminClient.realm(keycloakProperties.getRealm());
            //Get Role
            RoleRepresentation role = realmResource.roles().get(String.valueOf(Role.CUSTOMER)).toRepresentation();

            //Assign role to user
            realmResource.users().get(userId.toString()).roles().realmLevel().add(List.of(role));
        } catch (Exception e) {
            throw new KeycloakServiceException("Kullanıcıya 'customer' rolü atanamadı: " + userId, e);
        }
    }

    private UserRepresentation getUserRepresentation(String email, String password) {
        UserRepresentation user = new UserRepresentation();
        user.setEmail(email);
        user.setEnabled(true);
        user.setEmailVerified(true);    //Auto-verify for MVP.
        // TODO: gerçek sistemde email verification flow olmalı bu otomatik true olmamalı

        //Set Password
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);

        //UserRepresentation içerisine password bilgisi eklenir.
        user.setCredentials(List.of(credential));
        return user;
    }

}
