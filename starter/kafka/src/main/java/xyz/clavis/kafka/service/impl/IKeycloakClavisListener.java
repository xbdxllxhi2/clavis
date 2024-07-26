package xyz.clavis.kafka.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import xyz.clavis.kafka.service.ClavisListener;

@Service
public class IKeycloakClavisListener implements ClavisListener {
  Logger logger = LoggerFactory.getLogger(IKeycloakClavisListener.class);

  @KafkaListener(topics = "#{kafkaConfiguration.keycloakTopic}", groupId = "clavis")
  public void listen(String message) {
    logger.info("Keycloak user creation event received");
    System.out.println("Received Messasge in group foo: " + message);
  }
}
