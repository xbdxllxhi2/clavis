package xyz.clavis.security.models;

import java.util.Map;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.keycloak.representations.idm.UserRepresentation;


/**
 * Command to update a user in Keycloak.
 */
@Builder
@Getter
public class UpdateUserCommand {
  @NonNull
  private final String userId;
  private String email;
  private String firstName;
  private String lastName;
  private String username;
  private final Map<String, String> customAttributes;

  /**
   * Updates the given {@link UserRepresentation} with the values from this command.
   * <p>
   * This method modifies the {@link UserRepresentation} based on the following rules:
   * </p>
   * <ul>
   *   <li>If a field in this command is not null, the corresponding property in the {@link UserRepresentation} is updated.</li>
   *   <li>If a key in {@link #getCustomAttributes()} does not exist in {@link UserRepresentation#getAttributes()}, the key-value pair is ignored.</li>
   * </ul>
   *
   * @param userRepresentation the {@link UserRepresentation} to update.
   * @return the updated {@link UserRepresentation}.
   */
  public UserRepresentation updateAndReturn(UserRepresentation userRepresentation) {
    if (email != null) {
      userRepresentation.setEmail(email);
    }
    if (firstName != null) {
      userRepresentation.setFirstName(firstName);
    }
    if (lastName != null) {
      userRepresentation.setLastName(lastName);
    }
    if (username != null) {
      userRepresentation.setUsername(username);
    }

    if (customAttributes != null && !customAttributes.isEmpty()) {
      userRepresentation.getAttributes().forEach((k, v) -> {
        if (customAttributes.containsKey(k)) {
          var valueToAdd = customAttributes.get(k);
          if (Objects.nonNull(valueToAdd)) {
            userRepresentation.singleAttribute(k, valueToAdd);
          }
        }
      });
    }
    return userRepresentation;
  }
}
