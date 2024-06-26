package xyz.clavis.launcher.dummyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.clavis.launcher.openapi.OpenApiConfig;
import xyz.clavis.security.api.ClavisKeycloakService;
import xyz.clavis.security.endpointsconfiguration.ClavisPublicController;


@RestController
public class Public extends ClavisPublicController {
  @Autowired
  ClavisKeycloakService clavisKeycloakService;
  @Autowired
  OpenApiConfig openApiConfig;

  @GetMapping("/dummy")
  String getp() {
//    clavisKeycloakService.getRealm("master");
//    clavisKeycloakService.getUser("master", "admin");
    return "I am public!";
  }
}
