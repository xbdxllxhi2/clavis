package xyz.clavis.launcher.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.configuration.SpringDocConfiguration;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.clavis.security.model.HttpHeadersConstants;
import xyz.clavis.security.model.OAuth2IdpConfig;


@ConditionalOnClass(SpringDocConfiguration.class)
@Configuration
public class OpenApiConfig {

  @Value("${keycloak.auth-server-url}/realms/clavis-admin/protocol/openid-connect/auth")
  private String authorizationUrl;

  @Value(
      "${spring.security.oauth2.resourceserver.jwt.issuer-uri}" + "/protocol/openid-connect/token")
  private String tokenUrl;

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().info(new Info().title("API Documentation").version("v1"))
        .addSecurityItem(new SecurityRequirement().addList("oauth2_security_scheme")).components(
            new io.swagger.v3.oas.models.Components().addSecuritySchemes("oauth2_security_scheme",
                new SecurityScheme().type(SecurityScheme.Type.OAUTH2).flows(
                    new OAuthFlows().authorizationCode(
                        new OAuthFlow().authorizationUrl(authorizationUrl).tokenUrl(tokenUrl)))));

  }

  @Bean
  public GroupedOpenApi publicOpenAPi() {
    String paths[] = {"/public/**"};
    return GroupedOpenApi.builder().group("public").pathsToMatch(paths)
        .addOperationCustomizer(addHeaders()).build();
  }

  private OpenApiCustomizer adminApiCustomizer() {
    return (openApi -> {
      String authentification = "Admin_Auth";
      openApi.addSecurityItem(new SecurityRequirement().addList(authentification))
          .schemaRequirement(authentification, new SecurityScheme().type(SecurityScheme.Type.OAUTH2)
              .flows(new OAuthFlows().authorizationCode(
                  new OAuthFlow().authorizationUrl(authorizationUrl).tokenUrl(tokenUrl))));
    });
  }

  private SecurityScheme createOauth2SecuritySecheme(OAuth2IdpConfig config, String schemeName) {
    return new SecurityScheme().name(schemeName)
        .description("Oauth2 Security Authentification for " + schemeName)
        .in(SecurityScheme.In.HEADER).type(SecurityScheme.Type.OAUTH2).flows(
            new OAuthFlows().authorizationCode(
                new OAuthFlow().authorizationUrl(config.getAuthorizationUrl())
                    .tokenUrl(config.getTokenUrl())));
  }

  @Bean
  public GroupedOpenApi securedOpenApi() {
    String paths[] = {"/secured/**"};
    return GroupedOpenApi.builder().group("secured").pathsToMatch(paths)
        .addOperationCustomizer(addHeaders()).build();
  }

  private OperationCustomizer addHeaders() {
    return (operation, handlerMethod) -> {
      String header = "header";
      operation.addParametersItem(new Parameter().name(HttpHeadersConstants.X_APP_ID).in(header)
          .required(true).schema(new StringSchema()));

      return operation;
    };
  }
}
