package xyz.clavis.kcspi;

import java.io.IOException;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.keycloak.util.JsonSerialization;
import xyz.clavis.kcspi.model.KafkaConfiguration;

@Slf4j
public class ClavisEventListener implements EventListenerProvider {

  private final KeycloakSession keycloakSession;
  private final KafkaProducer<String, Object> producer;
  private final KafkaConfiguration kafkaConfiguration;

  public ClavisEventListener(KeycloakSession keycloakSession,
                             KafkaConfiguration kafkaConfiguration) {
    log.info("Initializing ClavisEventListener...");
    this.keycloakSession = keycloakSession;
    this.kafkaConfiguration = kafkaConfiguration;
    this.producer = new KafkaProducer<>(getProperties());
    log.info("ClavisEventListener initialized");
  }

  private Properties getProperties() {
    Properties properties = new Properties();

    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
        this.kafkaConfiguration.getBootstrapServers());
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());

    return properties;
  }


  @Override
  public void onEvent(Event event) {
    log.info("Yo i got this Event: {} ,{} ,{} ,{} ,{} ", event.getDetails(),
        event.getIpAddress(), event.getClientId(), event.getType(), event.getError());

    this.producer.send(new ProducerRecord<>(this.kafkaConfiguration.getTopicName(), event));

  }

  @Override
  public void onEvent(AdminEvent event, boolean b) {
    log.info("Yo i got this Event: {} ,{} ,{} ,{} ,{} ,{} ", event.getResourceTypeAsString(),
        event.getAuthDetails(), event.getRepresentation(), event.getOperationType(),
        event.getError(), event.getResourceType());
    event.getResourceType();
    try {
      this.producer.send(new ProducerRecord<>(this.kafkaConfiguration.getTopicName(),
          JsonSerialization.writeValueAsString(event)));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public void close() {
    this.producer.close();
  }
}
