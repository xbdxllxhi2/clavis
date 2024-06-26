package xyz.clavis.security;


import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KeycloakConfiguration {
  //Change this to read from OAuthIdpConfig
  @Value("${keycloak.auth-server-url}")
  private String authServerUrl;
  @Value("${keycloak.realm}")
  private String realm;
  @Value("${keycloak.resource}")
  private String clientId;
  @Value("${keycloak.credentials.secret}")
  private String secret;
  @Value("${keycloak.credentials.username}")
  private String userName;
  @Value("${keycloak.credentials.password}")
  private String password;


  @Bean
  Keycloak keycloak() {
    return KeycloakBuilder.builder()
        .serverUrl(authServerUrl)
        .realm(realm)
        .clientId(clientId)
//        .clientSecret(secret)
        .username(userName)
        .password(password)
        .grantType(OAuth2Constants.PASSWORD)
        .build();
  }
}
