# Environment Variables for Bitnami Keycloak

Here are the customizable environment variables for configuring Bitnami Keycloak Docker containers:

| Name                                        | Description                                                                | Default Value                          |
|---------------------------------------------|----------------------------------------------------------------------------|----------------------------------------|
| KEYCLOAK_MOUNTED_CONF_DIR                   | Directory for including custom configuration files                          | ${KEYCLOAK_VOLUME_DIR}/conf           |
| KC_RUN_IN_CONTAINER                         | Keycloak kc.sh context                                                     | true                                   |
| KEYCLOAK_ADMIN                              | Keycloak administrator user                                                | user                                   |
| KEYCLOAK_ADMIN_PASSWORD                     | Keycloak administrator password                                            | bitnami                                |
| KEYCLOAK_HTTP_RELATIVE_PATH                 | Set the path relative to "/" for serving resources                          | /                                      |
| KEYCLOAK_HTTP_PORT                          | HTTP port                                                                   | 8080                                   |
| KEYCLOAK_HTTPS_PORT                         | HTTPS port                                                                  | 8443                                   |
| KEYCLOAK_BIND_ADDRESS                       | Bind address                                                                | $(hostname --fqdn)                     |
| KEYCLOAK_HOSTNAME                           | Keycloak hostname                                                           | nil                                    |
| KEYCLOAK_INIT_MAX_RETRIES                   | Maximum retries for checking that the database works                       | 10                                     |
| KEYCLOAK_CACHE_TYPE                         | Defines the cache mechanism for high-availability                           | ispn                                   |
| KEYCLOAK_CACHE_STACK                        | Apply a specific cache stack                                                | nil                                    |
| KEYCLOAK_CACHE_CONFIG_FILE                  | Path to the cache config file                                               | nil                                    |
| KEYCLOAK_EXTRA_ARGS                         | Add extra startup parameters to Keycloak                                    | nil                                    |
| KEYCLOAK_ENABLE_STATISTICS                  | Enable metrics for the database                                             | false                                  |
| KEYCLOAK_ENABLE_HEALTH_ENDPOINTS            | Enable health endpoints                                                     | false                                  |
| KEYCLOAK_ENABLE_HTTPS                       | Enable SSL certificates                                                     | false                                  |
| KEYCLOAK_HTTPS_TRUST_STORE_FILE             | Path to the SSL truststore file                                             | nil                                    |
| KEYCLOAK_HTTPS_TRUST_STORE_PASSWORD         | Password for decrypting the truststore file                                 | nil                                    |
| KEYCLOAK_HTTPS_KEY_STORE_FILE               | Path to the SSL keystore file                                               | nil                                    |
| KEYCLOAK_HTTPS_KEY_STORE_PASSWORD           | Password for decrypting the keystore file                                   | nil                                    |
| KEYCLOAK_HTTPS_USE_PEM                      | Set to true to configure HTTPS using PEM certificates                       | false                                  |
| KEYCLOAK_HTTPS_CERTIFICATE_FILE             | Path to the PEM certificate file                                            | nil                                    |
| KEYCLOAK_HTTPS_CERTIFICATE_KEY_FILE         | Path to the PEM key file                                                    | nil                                    |
| KEYCLOAK_SPI_TRUSTSTORE_FILE                | Path to the Keycloak SPI truststore file                                    | nil                                    |
| KEYCLOAK_SPI_TRUSTSTORE_PASSWORD            | Password for decrypting the SPI truststore file                             | nil                                    |
| KEYCLOAK_SPI_TRUSTSTORE_FILE_HOSTNAME_VERIFICATION_POLICY | Hostname verification policy for SPI connection over HTTPS/TLS | nil |
| KEYCLOAK_LOG_LEVEL                          | Keycloak log level                                                          | info                                   |
| KEYCLOAK_LOG_OUTPUT                         | Keycloak log output                                                         | default                                |
| KEYCLOAK_ROOT_LOG_LEVEL                     | Keycloak root log level                                                     | INFO                                   |
| KEYCLOAK_PROXY                              | Keycloak type proxy                                                         | passthrough                            |
| KEYCLOAK_PRODUCTION                         | Run in production mode                                                      | false                                  |
| KEYCLOAK_EXTRA_ARGS_PREPENDED               | Run with flags which are applied directly to Keycloak executable             | nil                                    |
| KEYCLOAK_DATABASE_VENDOR                    | Database vendor                                                             | postgresql                             |
| KEYCLOAK_DATABASE_HOST                      | Database backend hostname                                                    | postgresql                             |
| KEYCLOAK_DATABASE_PORT                      | Database backend port                                                        | 5432                                   |
| KEYCLOAK_DATABASE_USER                      | Database backend username                                                    | bn_keycloak                            |
| KEYCLOAK_DATABASE_NAME                      | Database name                                                               | bitnami_keycloak                       |
| KEYCLOAK_DATABASE_PASSWORD                  | Database backend password                                                    | nil                                    |
| KEYCLOAK_DATABASE_SCHEMA                    | PostgreSQL database schema                                                   | public                                 |
| KEYCLOAK_JDBC_PARAMS                        | Extra JDBC connection parameters for the database                            | nil                                    |
| KEYCLOAK_JDBC_DRIVER                        | JDBC driver to set in the connection string for the database                 | postgresql                             |
| KEYCLOAK_DAEMON_USER                        | Keycloak daemon user when running as root                                    | keycloak                               |
| KEYCLOAK_DAEMON_GROUP                       | Keycloak daemon group when running as root                                   | keycloak                               |

## Bitnami Keycloak Read-Only Environment Variables

Here are the read-only environment variables for Bitnami Keycloak Docker containers:

| Variable                       | Description                                             | Default Value                  |
|--------------------------------|---------------------------------------------------------|--------------------------------|
| BITNAMI_VOLUME_DIR             | Directory where to mount volumes                        | /bitnami                       |
| JAVA_HOME                      | Java installation directory                             | /opt/bitnami/java              |
| KEYCLOAK_BASE_DIR              | Keycloak base directory                                 | /opt/bitnami/keycloak          |
| KEYCLOAK_BIN_DIR               | Keycloak bin directory                                  | $KEYCLOAK_BASE_DIR/bin         |
| KEYCLOAK_PROVIDERS_DIR         | Keycloak Wildfly extensions directory                   | $KEYCLOAK_BASE_DIR/providers   |
| KEYCLOAK_LOG_DIR               | Keycloak bin directory                                  | $KEYCLOAK_PROVIDERS_DIR/log    |
| KEYCLOAK_TMP_DIR               | Keycloak tmp directory                                  | $KEYCLOAK_PROVIDERS_DIR/tmp    |
| KEYCLOAK_DOMAIN_TMP_DIR        | Keycloak tmp directory                                  | $KEYCLOAK_BASE_DIR/domain/tmp  |
| WILDFLY_BASE_DIR               | Wildfly base directory                                  | /opt/bitnami/wildfly           |
| KEYCLOAK_VOLUME_DIR            | Path to Keycloak mount directory                        | /bitnami/keycloak              |
| KEYCLOAK_CONF_DIR              | Keycloak configuration directory                        | $KEYCLOAK_BASE_DIR/conf        |
| KEYCLOAK_DEFAULT_CONF_DIR      | Keycloak default configuration directory                | $KEYCLOAK_BASE_DIR/conf.default|
| KEYCLOAK_INITSCRIPTS_DIR       | Path to Keycloak init scripts directory                 | /docker-entrypoint-initdb.d    |
| KEYCLOAK_CONF_FILE             | Name of the Keycloak configuration file (relative path) | keycloak.conf                  |
| KEYCLOAK_DEFAULT_CONF_FILE     | Name of the Keycloak configuration file (relative path) | keycloak.conf                  |
