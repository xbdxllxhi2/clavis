package xyz.clavis.security.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

  @Override
  public AbstractAuthenticationToken convert(Jwt jwt) {
    Collection<GrantedAuthority> roles = extractAuthorities(jwt);
    return new JwtAuthenticationToken(jwt, roles);
  }

  private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
    if (jwt.getClaim("realm_access") != null) {
      Map<String, Object> realmAccess = jwt.getClaim("realm_access");
      ObjectMapper mapper = new ObjectMapper();
      List<String> keycloakRoles =
          mapper.convertValue(realmAccess.get("roles"), new TypeReference<List<String>>() {
          });
      List<GrantedAuthority> roles = new ArrayList<>();

      for (String keycloakRole : keycloakRoles) {
        roles.add(new SimpleGrantedAuthority("ROLE_" + keycloakRole));
      }

      // Log roles
      log.info("Extracted realm roles from JWT: {}", roles);

      return roles;
    }
    return new ArrayList<>();
  }
}