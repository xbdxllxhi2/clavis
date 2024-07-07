package xyz.clavis.kcspi;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import xyz.clavis.kcspi.model.KafkaConfiguration;

@Slf4j
public class ClavisEventListenerFactory implements EventListenerProviderFactory {
  public static final String CLAVIS_LISTENER_ID = "clavis-listener";

  private KafkaConfiguration kafkaConfiguration;

  @Override
  public EventListenerProvider create(KeycloakSession session) {
    return new ClavisEventListener(session, this.kafkaConfiguration);
  }

  @Override
  public void init(Config.Scope config) {
    log.info("Initializing ClavisEventListenerFactory...");
    this.kafkaConfiguration = getKafkaConfig(config);
    log.info("KafkaConfiguration: {}", this.kafkaConfiguration);
    log.info("ClavisEventListenerFactory initialized");
  }

  @Override
  public void postInit(KeycloakSessionFactory factory) {
    //NOOP
  }

  @Override
  public void close() {
    //NOOP
  }

  @Override
  public String getId() {
    return CLAVIS_LISTENER_ID;
  }

  private KafkaConfiguration getKafkaConfig(Config.Scope config) {
    String bootstrapServers = Objects.requireNonNull(config.get("BOOTSRAPSERVERS"),
        "${KC_SPI_EVENTS_LISTENER_CLAVIS_LISTENER_BOOTSRAPSERVERS} must be set!");

    String topicName = Objects.requireNonNull(config.get("TOPICNAME"),
        "${KC_SPI_EVENTS_LISTENER_CLAVIS_LISTENER_TOPICNAME} must be set!");

    return KafkaConfiguration.builder()
        .bootstrapServers(bootstrapServers)
        .topicName(topicName)
        .build();
  }
}
