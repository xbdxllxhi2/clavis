package xyz.clavis.launcher.dummyController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.clavis.security.endpointsconfiguration.ClavisPublicController;


@RestController
public class Public extends ClavisPublicController {

  @GetMapping("/dummy")
  String getp() {
    return "I am public!";
  }
}
