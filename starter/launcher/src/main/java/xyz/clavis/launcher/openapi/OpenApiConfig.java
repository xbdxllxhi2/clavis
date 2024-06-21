package xyz.clavis.launcher.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Value("${keycloak.auth-server-url}")
  private String authorizationUrl;

  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}" +"/protocol/openid-connect/token")
  private String tokenUrl;

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("API Documentation").version("v1"))
        .addSecurityItem(new SecurityRequirement().addList("oauth2_security_scheme"))
        .components(new io.swagger.v3.oas.models.Components()
            .addSecuritySchemes("oauth2_security_scheme", new SecurityScheme()
                .type(SecurityScheme.Type.OAUTH2)
                .flows(new OAuthFlows()
                    .authorizationCode(new OAuthFlow()
                        .authorizationUrl(authorizationUrl)
                        .tokenUrl(tokenUrl)))));

  }

  @Bean
  public GroupedOpenApi petOpenApi() {
    String paths[] = {"/public/**"};
    return GroupedOpenApi.builder().group("public").pathsToMatch(paths)
        .build();
  }

  @Bean
  public GroupedOpenApi securedOpenApi() {
    String paths[] = {"/secured/**"};
    return GroupedOpenApi.builder().group("secured").pathsToMatch(paths)
        .build();
  }
}
