resource "keycloak_realm" "clavis-admin-realm" {
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
  direct_access_grants_enabled = false
  realm_id                     = keycloak_realm.clavis-admin-realm.id
  service_accounts_enabled     = true
  authorization {
    policy_enforcement_mode = "ENFORCING"
  }
}

resource "keycloak_role" "clavis-backend-admin-role" {
  name     = "admin"
  realm_id = keycloak_realm.clavis-admin-realm.id
}

resource "keycloak_openid_client_authorization_resource" "all-endpoints" {
  name               = "all-endpoints"
  realm_id           = keycloak_realm.clavis-admin-realm.id
  resource_server_id = keycloak_openid_client.clavis-backend.id
  uris = ["/*"]
}

resource "keycloak_openid_client_role_policy" "secure-all-endpoints" {
  name               = "secure-all-endpoints"
  realm_id           = keycloak_realm.clavis-admin-realm.id
  resource_server_id = keycloak_openid_client.clavis-backend.id
  decision_strategy  = "UNANIMOUS"
  logic              = "POSITIVE"
  type               = "role"
  role {
    id       = keycloak_role.clavis-backend-admin-role.id
    required = false
  }
}

resource "keycloak_openid_client_authorization_permission" "all-endpoints" {
  name               = "all-endpoints"
  realm_id           = keycloak_realm.clavis-admin-realm.id
  resources = [keycloak_openid_client_authorization_resource.all-endpoints.id]
  policies = [keycloak_openid_client_role_policy.secure-all-endpoints.id]
  resource_server_id = keycloak_openid_client.clavis-backend.id
  type               = "resource"
}

resource "keycloak_user" "clavis-user" {
  realm_id       = keycloak_realm.clavis-admin-realm.id
  username       = "clavis"
  email          = "clavis@email.com"
  email_verified = true

  initial_password {
    value     = "clavis"
    temporary = false
  }
}