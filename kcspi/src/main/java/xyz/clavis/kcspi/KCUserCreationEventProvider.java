package xyz.clavis.kcspi;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class KCUserCreationEventProvider implements EventListenerProvider {
  private KeycloakSession keycloakSession;
  private KafkaProducer<String, String> producer;
  private final String topic;

  Logger logger = LoggerFactory.getLogger(KCUserCreationEventProvider.class);


  public KCUserCreationEventProvider(KeycloakSession keycloakSession, String bootsrapServers,
                                     String topicName) {
    this.keycloakSession = keycloakSession;

    Properties properties = new Properties();
    properties
        .setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootsrapServers);
    properties
        .setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties
        .setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
            StringSerializer.class.getName());

    this.producer = new KafkaProducer<>(properties);
    this.topic = topicName;
  }


  @Override
  public void onEvent(Event event) {
    logger.info("Yo i got this Event: {} ,{} ,{} ,{} ,{} ", event.getDetails(),
        event.getIpAddress(),
        event.getClientId(), event.getType(), event.getError());

    this.producer.send(new ProducerRecord<>(topic, "Event published to kafka"));
  }

  @Override
  public void onEvent(AdminEvent event, boolean b) {
    logger.info("Yo i got this Event: {} ,{} ,{} ,{} ,{} ,{} ", event.getResourceTypeAsString(),
        event.getAuthDetails(),
        event.getRepresentation(), event.getOperationType(), event.getError(),
        event.getResourceType());

    this.producer.send(new ProducerRecord<>(topic, "Admin event published to kafka"));
  }

  @Override
  public void close() {

  }
}
