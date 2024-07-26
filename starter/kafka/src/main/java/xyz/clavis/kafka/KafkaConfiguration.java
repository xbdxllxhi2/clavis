package xyz.clavis.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "clavis.kafka")
@Getter
@Setter
public class KafkaConfiguration {
  private String bootstrapServers;
  private String keycloakTopic;
}

