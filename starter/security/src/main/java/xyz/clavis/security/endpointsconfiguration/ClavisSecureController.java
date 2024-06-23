package xyz.clavis.security.endpointsconfiguration;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(SecurityConstants.SECURED)
public class ClavisSecureController implements ClavisController {
}
