package xyz.clavis.security.endpointsconfiguration;

public class SecurityConstants {

  public final static String SECURED = "/secured";
  public final static String PUBLIC = "/public";
  public final static String[] WHITE_LISTED_PATHS =
      {"/swagger-ui/**", "/api-docs/**", "/v3/api-docs/**",
          "/public/**", "/error/**"};
}
