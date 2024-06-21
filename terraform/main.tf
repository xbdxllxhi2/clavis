terraform {
  required_providers {
    keycloak = {
      source  = "mrparkers/keycloak"
      version = "4.4.0"
    }
  }
}

provider "keycloak" {
  url       = var.keycloak_url
  username  = "admin"
  password  = "admin"
  client_id = "admin-cli"
}
