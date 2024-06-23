package xyz.clavis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import xyz.clavis.security.endpointsconfiguration.SecurityConstants;
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
      authorize.requestMatchers(SecurityConstants.WHITE_LISTED_PATHS)
          .permitAll()
          .requestMatchers(SecurityConstants.SECURED + "/admin/**").hasRole("admin")
          .requestMatchers(SecurityConstants.SECURED + "/user/**").hasRole("USER")
          .anyRequest().authenticated();
    });
    http.oauth2ResourceServer(t -> {
      t.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthConverter));

    });
    http.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    return http.build();
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