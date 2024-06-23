package xyz.clavis.security.model;

import org.springframework.beans.factory.annotation.Value;

public class OAuth2IdpConfig {

  @Value("${keycloak.auth-server-url}")
  private String authorizationUrl;
  @Value(
      "${spring.security.oauth2.resourceserver.jwt.issuer-uri}" + "/protocol/openid-connect/token")
  private String tokenUrl;
  private String clientId;
  private String clientSecret;

  public String getAuthorizationUrl() {
    return authorizationUrl;
  }

  public String getTokenUrl() {
    return tokenUrl;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }
}
