package com.turkcell.identityservice.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Keycloak configuration properties.
 * Values provides by human via environment variables.
 */
@Configuration
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakProperties {

    private String realm;
    private String adminClientId;
    private String adminClientSecret;
    private String authServerUrl;
    private String tokenEndpoint;
    private String adminRealm = "master";

    public KeycloakProperties() {
    }

    public KeycloakProperties(String realm, String adminClientId, String adminClientSecret, String authServerUrl, String tokenEndpoint, String adminRealm) {
        this.realm = realm;
        this.adminClientId = adminClientId;
        this.adminClientSecret = adminClientSecret;
        this.authServerUrl = authServerUrl;
        this.tokenEndpoint = tokenEndpoint;
        this.adminRealm = adminRealm;
    }

    public String realm() {
        return realm;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getAdminClientId() {
        return adminClientId;
    }

    public void setAdminClientId(String adminClientId) {
        this.adminClientId = adminClientId;
    }

    public String getAdminClientSecret() {
        return adminClientSecret;
    }

    public void setAdminClientSecret(String adminClientSecret) {
        this.adminClientSecret = adminClientSecret;
    }

    public String getAuthServerUrl() {
        return authServerUrl;
    }

    public void setAuthServerUrl(String authServerUrl) {
        this.authServerUrl = authServerUrl;
    }

    public String getTokenEndpoint() {
        return tokenEndpoint;
    }

    public void setTokenEndpoint(String tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    public String getAdminRealm() {
        return adminRealm;
    }

    public void setAdminRealm(String adminRealm) {
        this.adminRealm = adminRealm;
    }
}
