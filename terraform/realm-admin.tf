resource "keycloak_realm" "admin-realm" {
  realm   = "clavis-admin"
  enabled = true
}

resource "keycloak_openid_client" "clavis-backend" {
  access_type                  = "CONFIDENTIAL"
  client_id                    = "clavis-backend"
  client_secret                = "clavis-backend"
  valid_redirect_uris = ["${var.backend_url}/*"]
  web_origins = ["*"]
  standard_flow_enabled        = true
  direct_access_grants_enabled = true
  realm_id                     = keycloak_realm.admin-realm.id
}

resource "keycloak_user" "clavis-user" {
  realm_id       = keycloak_realm.admin-realm.id
  username       = "clavis"
  email          = "clavis@email.com"
  email_verified = true

  initial_password {
    value     = "clavis"
    temporary = false
  }
}