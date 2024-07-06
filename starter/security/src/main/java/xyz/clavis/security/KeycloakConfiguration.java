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
  @Value("${clavis.keycloak.url}")
  private String authServerUrl;
  @Value("${clavis.keycloak.realm}")
  private String realm;
  @Value("${clavis.keycloak.resource}")
  private String clientId;
  @Value("${clavis.keycloak.credentials.secret}")
  private String secret;
  @Value("${clavis.keycloak.credentials.username}")
  private String userName;
  @Value("${clavis.keycloak.credentials.password}")
  private String password;


  @Bean
  Keycloak keycloak() {
    return KeycloakBuilder.builder()
        .serverUrl(authServerUrl)
        .realm(realm)
        .clientId(clientId)
        .clientSecret(secret)
        .username(userName)
        .password(password)
        .grantType(OAuth2Constants.PASSWORD)
        .build();
  }
}
