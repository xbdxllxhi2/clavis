package xyz.clavis.kcspi;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class KCUserCreationEventProvider implements EventListenerProvider {
  private final KeycloakSession keycloakSession;

  Logger logger = LoggerFactory.getLogger(KCUserCreationEventProvider.class);


  public KCUserCreationEventProvider(KeycloakSession keycloakSession) {
    this.keycloakSession = keycloakSession;
  }


  @Override
  public void onEvent(Event event) {
    logger.info("Yo i got this Event: {} ,{} ,{} ,{} ,{} ", event.getDetails(),
        event.getIpAddress(),
        event.getClientId(), event.getType(), event.getError());
  }

  @Override
  public void onEvent(AdminEvent event, boolean b) {
    logger.info("Yo i got this Event: {} ,{} ,{} ,{} ,{} ,{} ", event.getResourceTypeAsString(),
        event.getAuthDetails(),
        event.getRepresentation(), event.getOperationType(), event.getError(),
        event.getResourceType());
  }

  @Override
  public void close() {

  }
}
