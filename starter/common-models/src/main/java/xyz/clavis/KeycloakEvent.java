package xyz.clavis;

import java.util.Map;

public class KeycloakEvent {
  private String username;
  private String firstname;
  private String lastname;
  private String email;
  private boolean emailVerified;
  private Map<String, Object> attributes;
  private String actionType;
  private String resourceType;

  protected KeycloakEvent(Builder builder) {
    this.username = builder.username;
    this.firstname = builder.firstname;
    this.lastname = builder.lastname;
    this.email = builder.email;
    this.emailVerified = builder.emailVerified;
    this.attributes = builder.attributes;
    this.actionType = builder.actionType;
    this.resourceType = builder.resourceType;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isEmailVerified() {
    return emailVerified;
  }

  public void setEmailVerified(boolean emailVerified) {
    this.emailVerified = emailVerified;
  }

  public Map<String, Object> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  public String getActionType() {
    return actionType;
  }

  public void setActionType(String actionType) {
    this.actionType = actionType;
  }

  public String getResourceType() {
    return resourceType;
  }

  public void setResourceType(String resourceType) {
    this.resourceType = resourceType;
  }

  public static class Builder {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private boolean emailVerified;
    private Map<String, Object> attributes;
    private String actionType;
    private String resourceType;

    public Builder() {
    }

    public KeycloakEvent build() {
      return new KeycloakEvent(this);
    }

    public Builder username(String username) {
      this.username = username;
      return this;
    }

    public Builder firstname(String firstname) {
      this.firstname = firstname;
      return this;
    }

    public Builder lastname(String lastname) {
      this.lastname = lastname;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder emailVerified(boolean emailVerified) {
      this.emailVerified = emailVerified;
      return this;
    }

    public Builder attributes(Map<String, Object> attributes) {
      this.attributes = attributes;
      return this;
    }

    public Builder actionType(String actionType) {
      this.actionType = actionType;
      return this;
    }

    public Builder resourceType(String resourceType) {
      this.resourceType = resourceType;
      return this;
    }

    public Builder fromPrototype(KeycloakEvent prototype) {
      username = prototype.username;
      firstname = prototype.firstname;
      lastname = prototype.lastname;
      email = prototype.email;
      emailVerified = prototype.emailVerified;
      attributes = prototype.attributes;
      actionType = prototype.actionType;
      resourceType = prototype.resourceType;
      return this;
    }
  }
}
