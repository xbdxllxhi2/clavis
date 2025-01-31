package xyz.clavis.security.api;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import xyz.clavis.security.models.ClavisPassword;
import xyz.clavis.security.models.UpdateUserCommand;
import xyz.clavis.security.utils.ClavisSecurityConfigModel;


public class IClavisKeycloakService implements ClavisKeycloakService {
  private final Keycloak keycloak;
  private final ClavisSecurityConfigModel securityConfigModel;

  public IClavisKeycloakService(Keycloak keycloak, ClavisSecurityConfigModel securityConfigModel) {
    this.keycloak = keycloak;
    this.securityConfigModel = securityConfigModel;
  }

  @Override
  public RealmRepresentation getRealm(String realmName) {
    return this.keycloak.realm(realmName).toRepresentation();
  }

  @Override
  public RealmResource getCurrentRealmResource() {
    return this.keycloak.realm(getRealmOfCurrentUser());
  }

  @Override
  public UserResource getCurrentUserResource() {
    return this.keycloak.realm(getRealmOfCurrentUser()).users().get(getIdOfCurrentUser());
  }

  @Override
  public UserRepresentation getUser(String realmName, String userId) {
    return this.keycloak.realm(realmName).users().get(userId).toRepresentation();
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
  public void updateUser(String realmName, UpdateUserCommand updateUserCommand) {
    assert updateUserCommand != null;
    UserResource userResource =
        this.keycloak.realm(realmName).users().get(updateUserCommand.getUserId());
    var userRepresentation = updateUserCommand.updateAndReturn(userResource.toRepresentation());
    userResource.update(userRepresentation);
  }


  @Override
  public UserRepresentation getCurrentUserRepresentation() {
    return this.getUser(getRealmOfCurrentUser(), getIdOfCurrentUser());
  }

  @Override
  public String getRealmOfCurrentUser() {
    return this.securityConfigModel.getKeycloak().getRealm();
  }


  @Override
  public void updatePasswordOfCurrentUser(ClavisPassword password) {
    CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
    credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
    credentialRepresentation.setValue(password.getPassword());

    this.getCurrentUserResource().resetPassword(credentialRepresentation);
  }

}
