package xyz.clavis.kcspi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class KafkaConfiguration {
  private final String bootstrapServers;
  private final String topicName;

  public String toString() {
    return "KafkaConfiguration{" +
        "bootstrapServers='" + bootstrapServers + '\'' +
        ", topicName='" + topicName + '\'' +
        '}';
  }
}
