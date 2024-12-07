package xyz.clavis.security.api;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import xyz.clavis.security.ClavisUserRepresentation;
import xyz.clavis.security.models.UpdateUserCommand;


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


  public void updateUser(String realmName, UpdateUserCommand updateUserCommand) {
    assert updateUserCommand != null;
    UserResource userResource =
        this.keycloak.realm(realmName).users().get(updateUserCommand.getUserId());
    var userRepresentation = updateUserCommand.updateAndReturn(userResource.toRepresentation());
    userResource.update(userRepresentation);
  }


  @Override
  public ClavisUserRepresentation getCurrentUser() {
    var jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    return ClavisUserRepresentation.buildFrom(jwt);
  }

  public UserRepresentation getCurrentUserRepresentation() {
    var jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    return this.getUser(jwt.getClaim("iss"), jwt.getClaim("sub"));
  }

}
