package xyz.clavis.security.api;

import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import xyz.clavis.security.ClavisUserRepresentation;
import xyz.clavis.security.models.ClavisPassword;
import xyz.clavis.security.models.UpdateUserCommand;

/**
 * Service to interact with Keycloak.
 */
public interface ClavisKeycloakService {
  /**
   * Get the realm by name.
   *
   * @param realmName
   * @return {@link RealmRepresentation}
   */
  RealmRepresentation getRealm(String realmName);

  RealmResource getCurrentRealmResource();

  /**
   * Get the current user resource.
   *
   * <p>
   * User resource exposes the apis provided by keycloak to manage the user.
   * </p>
   *
   * @return {@link UserResource}
   */
  UserResource getCurrentUserResource();

  /**
   * Get the user by id.
   *
   * @param realmName
   * @param userId
   * @return {@link UserRepresentation}
   */
  UserRepresentation getUser(String realmName, String userId);


  /**
   * Add a user to the realm.
   *
   * @param realmName
   * @param userRepresentation
   */
  void addUser(String realmName, UserRepresentation userRepresentation);

  /**
   * Delete a user from the realm.
   *
   * @param realmName
   * @param userId
   */
  void deleteUser(String realmName, String userId);

  /**
   * Update a user in the realm.
   *
   * @param realmName
   * @param updateUserCommand the command to update the user.
   */
  void updateUser(String realmName, UpdateUserCommand updateUserCommand);

  /**
   * Get the current user.
   *
   * @return {@link ClavisUserRepresentation}
   */
  ClavisUserRepresentation getCurrentUser();

  /**
   * Get the current user representation.
   *
   * @return {@link UserRepresentation}
   */
  UserRepresentation getCurrentUserRepresentation();

  /**
   * update the password of the current user.
   *
   * @param password the new password.
   */
  void updatePasswordOfCurrentUser(ClavisPassword password);
}
