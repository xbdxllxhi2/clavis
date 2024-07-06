package xyz.clavis.kcspi;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KCUserCreationEventProviderFactory implements EventListenerProviderFactory {
  Logger logger = LoggerFactory.getLogger(KCUserCreationEventProvider.class);

  @Override
  public EventListenerProvider create(KeycloakSession session) {
    return new KCUserCreationEventProvider(session,"kafka:9092","KC_EVENT");
  }

  @Override
  public void init(Config.Scope config) {
    logger.info("KCUserCreationEventProviderFactory initialized");
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
    return "clavis-user-creation-event-listener";
  }
}
