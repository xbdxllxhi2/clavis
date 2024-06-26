package xyz.clavis.security.api;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;


@Service
public class IClavisKeycloakService implements ClavisKeycloakService {
  private final Keycloak keycloak;

  public IClavisKeycloakService(Keycloak keycloak) {
    this.keycloak = keycloak;
  }

  @Override
  public RealmRepresentation getRealm(String realmName) {
    return this.keycloak.realm(realmName).toRepresentation();
  }

  @Override
  public UserRepresentation getUser(String realmName, String userId) {
    return this.keycloak.realm(realmName).users().searchByUsername(userId, true).get(0);
  }

  @Override
  public void addUser(String realmName, UserRepresentation userRepresentation) {
    try {
      this.keycloak.realm(realmName).users().create(userRepresentation);
    } catch (Exception e) {
      throw new RuntimeException("Error creating user", e);
    }
  }

  @Override
  public void deleteUser(String realmName, String userId) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public void updateUser(String realmName, UserRepresentation userRepresentation) {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
