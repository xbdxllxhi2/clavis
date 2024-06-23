package xyz.clavis.launcher.dummyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import xyz.clavis.security.api.ClavisKeycloakService;
import xyz.clavis.security.endpointsconfiguration.ClavisSecureController;

@RestController
public class Secured extends ClavisSecureController {
  @Autowired
  ClavisKeycloakService clavisKeycloakService;
  RequestContextHolder holder;

  @GetMapping("/dummy")
  String get(@RequestHeader String X_CLIENT_ID) {
//    this.clavisKeycloakService.getRealm(X_CLIENT_ID);
    return "I am private!";
  }

  @GetMapping("/admin/hello")
  String admin() {
//    this.clavisKeycloakService.getRealm(X_CLIENT_ID);
    return "I am from admin !";
  }
}
