# Environment variables

## Customizable environment variables

| Name                                | Description                                                                                                            | Default Value                                           |
|-------------------------------------|------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------|
| KAFKA_MOUNTED_CONF_DIR              | Kafka directory for mounted configuration files.                                                                       | ${KAFKA_VOLUME_DIR}/config                              |
| KAFKA_INTER_BROKER_USER             | Kafka inter broker communication user.                                                                                 | user                                                    |
| KAFKA_INTER_BROKER_PASSWORD         | Kafka inter broker communication password.                                                                             | bitnami                                                 |
| KAFKA_CONTROLLER_USER               | Kafka control plane communication user.                                                                                | controller_user                                         |
| KAFKA_CONTROLLER_PASSWORD           | Kafka control plane communication password.                                                                            | bitnami                                                 |
| KAFKA_CERTIFICATE_PASSWORD          | Password for certificates.                                                                                             | nil                                                     |
| KAFKA_TLS_TRUSTSTORE_FILE           | Kafka truststore file location.                                                                                        | nil                                                     |
| KAFKA_TLS_TYPE                      | Choose the TLS certificate format to use.                                                                              | JKS                                                     |
| KAFKA_TLS_CLIENT_AUTH               | Configures kafka broker to request client authentication.                                                              | required                                                |
| KAFKA_OPTS                          | Kafka deployment options.                                                                                              | nil                                                     |
| KAFKA_CFG_SASL_ENABLED_MECHANISMS   | Kafka sasl.enabled.mechanisms configuration override.                                                                  | PLAIN,SCRAM-SHA-256,SCRAM-SHA-512                       |
| KAFKA_KRAFT_CLUSTER_ID              | Kafka cluster ID when using Kafka Raft mode (KRaft).                                                                   | nil                                                     |
| KAFKA_SKIP_KRAFT_STORAGE_INIT       | If set to true, skip Kraft storage initialization when process.roles are configured.                                    | false                                                   |
| KAFKA_CLIENT_LISTENER_NAME          | Name of the listener intended to be used by clients, if set, configures the producer/consumer accordingly.              | nil                                                     |
| KAFKA_ZOOKEEPER_PROTOCOL            | Authentication protocol for Zookeeper connections. Allowed protocols: PLAINTEXT, SASL, SSL, and SASL_SSL.               | PLAINTEXT                                               |
| KAFKA_ZOOKEEPER_PASSWORD            | Kafka Zookeeper user password for SASL authentication.                                                                 | nil                                                     |
| KAFKA_ZOOKEEPER_USER                | Kafka Zookeeper user for SASL authentication.                                                                          | nil                                                     |
| KAFKA_ZOOKEEPER_TLS_KEYSTORE_PASSWORD | Kafka Zookeeper keystore file password and key password.                                                                | nil                                                     |
| KAFKA_ZOOKEEPER_TLS_TRUSTSTORE_PASSWORD | Kafka Zookeeper truststore file password.                                                                              | nil                                                     |
| KAFKA_ZOOKEEPER_TLS_TRUSTSTORE_FILE | Kafka Zookeeper truststore file location.                                                                              | nil                                                     |
| KAFKA_ZOOKEEPER_TLS_VERIFY_HOSTNAME | Verify Zookeeper hostname on TLS certificates.                                                                         | true                                                    |
| KAFKA_ZOOKEEPER_TLS_TYPE            | Choose the TLS certificate format to use. Allowed values: JKS, PEM.                                                     | JKS                                                     |
| KAFKA_CLIENT_USERS                  | List of additional users to KAFKA_CLIENT_USER that will be created into Zookeeper when using SASL_SCRAM for client communications. Separated by commas, semicolons or whitespaces. | user |
| KAFKA_CLIENT_PASSWORDS              | Passwords for the users specified at KAFKA_CLIENT_USERS. Separated by commas, semicolons or whitespaces.                | bitnami                                                 |
| KAFKA_HEAP_OPTS                     | Kafka heap options for Java.                                                                                           | -Xmx1024m -Xms1024m                                     |

## Read-only environment variables

| Name                    | Description                                      | Value                             |
|-------------------------|--------------------------------------------------|-----------------------------------|
| KAFKA_BASE_DIR          | Kafka installation directory.                    | ${BITNAMI_ROOT_DIR}/kafka         |
| KAFKA_VOLUME_DIR        | Kafka persistence directory.                     | /bitnami/kafka                    |
| KAFKA_DATA_DIR          | Kafka directory where data is stored.            | ${KAFKA_VOLUME_DIR}/data          |
| KAFKA_CONF_DIR          | Kafka configuration directory.                   | ${KAFKA_BASE_DIR}/config          |
| KAFKA_CONF_FILE         | Kafka configuration file.                        | ${KAFKA_CONF_DIR}/server.properties|
| KAFKA_CERTS_DIR         | Kafka directory for certificate files.           | ${KAFKA_CONF_DIR}/certs           |
| KAFKA_INITSCRIPTS_DIR   | Kafka directory for init scripts.                | /docker-entrypoint-initdb.d       |
| KAFKA_LOG_DIR           | Directory where Kafka logs are stored.           | ${KAFKA_BASE_DIR}/logs            |
| KAFKA_HOME              | Kafka home directory.                            | $KAFKA_BASE_DIR                   |
| KAFKA_DAEMON_USER       | Kafka system user.                               | kafka                             |
| KAFKA_DAEMON_GROUP      | Kafka system group.                              | kafka                             |

