package xyz.clavis.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
import xyz.clavis.security.utils.ClavisSecurityConfigModel;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@ComponentScan(basePackages = "xyz.clavis.security")
public class SecurityConfig {

  private final ClavisSecurityConfigModel securityConfigModel;

  public SecurityConfig(ClavisSecurityConfigModel securityConfigModel) {
    this.securityConfigModel = securityConfigModel;
  }

  @Bean
  @ConditionalOnMissingBean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable);

    http.addFilterAfter(createPolicyFilter(), BearerTokenAuthenticationFilter.class);

    http.oauth2ResourceServer(t -> {
      t.jwt(withDefaults());
    });

    http.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    return http.build();
  }

  private ServletPolicyEnforcerFilter createPolicyFilter() {
    return new ServletPolicyEnforcerFilter(httpRequest -> getPolicyEnforcerConfig());
  }

  private PolicyEnforcerConfig getPolicyEnforcerConfig() {
    var policyEnforceConfig = new PolicyEnforcerConfig();

    policyEnforceConfig.setAuthServerUrl(securityConfigModel.getKeycloak().getUrl());
    policyEnforceConfig.setRealm(securityConfigModel.getKeycloak().getRealm());
    policyEnforceConfig.setResource(securityConfigModel.getKeycloak().getResource());
    policyEnforceConfig.setCredentials(
        Map.of("secret", securityConfigModel.getKeycloak().getCredentials().getSecret()));
    policyEnforceConfig.setPaths(getPathConfigs());

    return policyEnforceConfig;
  }

  private List<PolicyEnforcerConfig.PathConfig> getPathConfigs() {
    return Arrays.stream(securityConfigModel.getApiConfig().getWhitelistedUrls())
        .map(url -> {
          var pathConfig = new PolicyEnforcerConfig.PathConfig();
          pathConfig.setPath(url);
          pathConfig.setEnforcementMode(PolicyEnforcerConfig.EnforcementMode.DISABLED);
          return pathConfig;
        }).toList();
  }


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