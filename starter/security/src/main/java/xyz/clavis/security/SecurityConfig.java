package xyz.clavis.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.io.IOException;
import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
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
    http.csrf(AbstractHttpConfigurer::disable);

    http.addFilterAfter(createPolicyFilter(), BearerTokenAuthenticationFilter.class);

    http.oauth2ResourceServer(t -> {
      t.jwt(withDefaults());
//      (jwtConfigurer) ->    jwtConfigurer.jwtAuthenticationConverter(jwtAuthConverter));
    });

    http.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    return http.build();
  }

  private ServletPolicyEnforcerFilter createPolicyFilter() {
    return new ServletPolicyEnforcerFilter(new ConfigurationResolver() {
      @Override
      public PolicyEnforcerConfig resolve(HttpRequest httpRequest) {
        try {
          return JsonSerialization.readValue(
              getClass().getResourceAsStream("/policy-enforcer-config.json"),
              PolicyEnforcerConfig.class);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });
  }

//  @Bean
//  public DefaultMethodSecurityExpressionHandler msecurity() {
//    DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler =
//        new DefaultMethodSecurityExpressionHandler();
//    defaultMethodSecurityExpressionHandler.setDefaultRolePrefix("");
//    return defaultMethodSecurityExpressionHandler;
//  }


  @Bean
  public JwtAuthenticationConverter con() {
    JwtAuthenticationConverter c = new JwtAuthenticationConverter();
    JwtGrantedAuthoritiesConverter cv = new JwtGrantedAuthoritiesConverter();
    cv.setAuthorityPrefix("");
    cv.setAuthoritiesClaimName("roles");
    c.setJwtGrantedAuthoritiesConverter(cv);
    return c;
  }

  @Bean
  SessionAuthenticationStrategy sessionAuthenticationStrategy() {
    return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
  }
}