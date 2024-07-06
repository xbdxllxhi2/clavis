package xyz.clavis.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Listener {

  Logger logger = LoggerFactory.getLogger(Listener.class);

  @KafkaListener(topics = "KC_EVENT", groupId = "clavis")
  public void listen(String message) {
    logger.info("Keycloak user creation event received");
    System.out.println("Received Messasge in group foo: " + message);
  }
}
