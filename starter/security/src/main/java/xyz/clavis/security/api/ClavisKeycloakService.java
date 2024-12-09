package xyz.clavis.security.api;

import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import xyz.clavis.security.ClavisUserRepresentation;
import xyz.clavis.security.models.UpdateUserCommand;

public interface ClavisKeycloakService {
  RealmRepresentation getRealm(String realmName);

  UserRepresentation getUser(String realmName, String userId);

  void addUser(String realmName, UserRepresentation userRepresentation);

  void deleteUser(String realmName, String userId);

  void updateUser(String realmName, UpdateUserCommand updateUserCommand);

  ClavisUserRepresentation getCurrentUser();

  UserRepresentation getCurrentUserRepresentation();
}
