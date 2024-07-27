package xyz.clavis.launcher.dummyController;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import xyz.clavis.security.api.ClavisKeycloakService;
import xyz.clavis.security.endpointsconfiguration.ClavisSecureController;

@RestController
public class Secured extends ClavisSecureController {
  private final ClavisKeycloakService clavisKeycloakService;
  RequestContextHolder holder;

  public Secured(ClavisKeycloakService clavisKeycloakService) {
    this.clavisKeycloakService = clavisKeycloakService;
  }

  @GetMapping("/dummy")
  @PreAuthorize("hasRole('ROLE_dummy')")
  String get(@RequestHeader String X_CLIENT_ID) {
    this.clavisKeycloakService.getRealm(X_CLIENT_ID);
    return "I am private!";
  }

  @GetMapping("/admin/hello")
  String admin() {
    this.clavisKeycloakService.getRealm("master");
    return "I am from admin !";
  }
}
