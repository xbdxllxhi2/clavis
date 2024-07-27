package xyz.clavis.security;


import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.clavis.security.utils.ClavisSecurityConfigModel;


@Configuration
public class KeycloakConfiguration {

  private final ClavisSecurityConfigModel securityConfigModel;

  public KeycloakConfiguration(ClavisSecurityConfigModel securityConfigModel) {
    this.securityConfigModel = securityConfigModel;
  }


  @Bean
  Keycloak keycloak() {
    return KeycloakBuilder.builder()
        .serverUrl(securityConfigModel.getKeycloak().getUrl())
        .realm(securityConfigModel.getKeycloakAdmin().getRealm())
        .clientId(securityConfigModel.getKeycloakAdmin().getClientId())
        .clientSecret(securityConfigModel.getKeycloakAdmin().getClientSecret())
        .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
        .build();
  }
}
