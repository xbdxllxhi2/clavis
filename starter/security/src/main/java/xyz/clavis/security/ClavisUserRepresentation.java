package xyz.clavis.security;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.oauth2.jwt.Jwt;

@Builder
@Getter
public class ClavisUserRepresentation {
  private String firstname;
  private String lastname;
  private String username;
  ContactInformation contactInformation;
  private Set<String> roles;

  @SuppressWarnings("unchecked")
  public static ClavisUserRepresentation buildFrom(Jwt jwt) {
    return ClavisUserRepresentation.builder()
        .username(jwt.getClaim("preferred_username"))
        .firstname(jwt.getClaim("given_name"))
        .lastname(jwt.getClaim("family_name"))
        .contactInformation(ClavisUserRepresentation
            .ContactInformation.builder()
            .email(jwt.getClaim("email")).build())
        .roles(new HashSet<>(
            ((LinkedTreeMap<String, List<String>>) jwt.getClaim("realm_access"))
                .get("roles")))
        .build();
  }

  @Builder
  @Getter
  public static class ContactInformation {
    private String email;
    private String phoneNumber;
  }
}
