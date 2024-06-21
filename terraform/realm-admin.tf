resource "keycloak_realm" "admin-realm" {
  realm   = "clavis-admin"
  enabled = true
}

resource "keycloak_user" "calvis-admin" {
  realm_id       = keycloak_realm.admin-realm.id
  username       = "calvis"
  email          = "calvis@email.com"
  email_verified = true

  initial_password {
    value     = "calvis"
    temporary = false
  }
}