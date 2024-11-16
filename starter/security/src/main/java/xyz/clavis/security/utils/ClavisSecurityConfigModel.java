package xyz.clavis.security.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "clavis.security")
@Configuration
@Getter
@Setter
public class ClavisSecurityConfigModel {
  private KeycloakModel keycloak;
  private ApiConfigModel apiConfig;
  private KeycloakAdminModel keycloakAdmin;

  public String getIssuerUri() {
    return keycloak.getUrl() + "/realms/" + keycloak.getRealm();
  }

  public String getAuthorizationUrl() {
    return getIssuerUri() + "/protocol/openid-connect/auth";
  }


  public String getTokenUrl() {
    return getIssuerUri() + "/protocol/openid-connect/token";
  }


  @Getter
  @Setter
  public static class KeycloakAdminModel {
    private String realm;
    private String clientId;
    private String clientSecret;
  }


  @Getter
  @Setter
  public static class KeycloakModel {
    private String url;
    private String realm;
    private String resource;
    private Credentials credentials;


    @Getter
    @Setter
    public static class Credentials {
      private String secret;
      private String username;
      private String password;
    }
  }

  @Getter
  @Setter
  public static class ApiConfigModel {
    String[] whitelistedUrls;
  }

}
