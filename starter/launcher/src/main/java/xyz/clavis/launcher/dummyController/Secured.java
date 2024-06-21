package xyz.clavis.launcher.dummyController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.clavis.security.endpointsconfiguration.ClavisSecureController;

@RestController
public class Secured extends ClavisSecureController {

  @GetMapping("/dummy")
  String get() {
    return "I am private!";
  }
}
