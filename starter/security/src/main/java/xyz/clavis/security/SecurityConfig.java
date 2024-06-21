package xyz.clavis.security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import xyz.clavis.security.utils.JwtAuthConverter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@ComponentScan(basePackages = "xyz.clavis.security")
public class SecurityConfig {

  @Autowired
  JwtAuthConverter jwtAuthConverter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(t -> t.disable());
    http.authorizeHttpRequests(authorize -> {
      authorize.requestMatchers("/swagger-ui/**", "/api-docs/**", "/v3/api-docs/**", "/public/**")
          .permitAll()
          .anyRequest().authenticated();
    });
    http.oauth2ResourceServer(t -> {
      t.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthConverter));

    });
    http.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    return http.build();
  }

  @Bean
  public DefaultMethodSecurityExpressionHandler msecurity() {
    DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler =
        new DefaultMethodSecurityExpressionHandler();
    defaultMethodSecurityExpressionHandler.setDefaultRolePrefix("");
    return defaultMethodSecurityExpressionHandler;
  }

  @Bean
  public JwtAuthenticationConverter con() {
    JwtAuthenticationConverter c = new JwtAuthenticationConverter();
    JwtGrantedAuthoritiesConverter cv = new JwtGrantedAuthoritiesConverter();
    cv.setAuthorityPrefix(""); // Default "SCOPE_"
    cv.setAuthoritiesClaimName("roles"); // Default "scope" or "scp"
    c.setJwtGrantedAuthoritiesConverter(cv);
    return c;
  }

}