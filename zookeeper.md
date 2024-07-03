# Configuration

## Environment variables

### Customizable environment variables

| Name                             | Description                                                                                                                   | Default Value |
|----------------------------------|-------------------------------------------------------------------------------------------------------------------------------|---------------|
| ZOO_DATA_LOG_DIR                 | ZooKeeper directory where data is stored.                                                                                      | nil           |
| ZOO_PORT_NUMBER                  | ZooKeeper client port.                                                                                                         | 2181          |
| ZOO_SERVER_ID                    | ID of the server in the ensemble.                                                                                              | 1             |
| ZOO_SERVERS                      | Comma, space or semi-colon separated list of servers.                                                                          | nil           |
| ZOO_ENABLE_ADMIN_SERVER          | Whether to enable the ZooKeeper admin server.                                                                                  | yes           |
| ZOO_ADMIN_SERVER_PORT_NUMBER     | ZooKeeper admin server port.                                                                                                   | 8080          |
| ZOO_PEER_TYPE                    | Zookeeper Node Peer type                                                                                                       | nil           |
| ZOO_TICK_TIME                    | Basic time unit in milliseconds used by ZooKeeper for heartbeats.                                                              | 2000          |
| ZOO_INIT_LIMIT                   | ZooKeeper uses to limit the length of time the ZooKeeper servers in quorum have to connect to a leader.                        | 10            |
| ZOO_SYNC_LIMIT                   | How far out of date a server can be from a leader.                                                                             | 5             |
| ZOO_MAX_CNXNS                    | Limits the total number of concurrent connections that can be made to a ZooKeeper server. Setting it to 0 entirely removes the limit. | 0             |
| ZOO_MAX_CLIENT_CNXNS             | Limits the number of concurrent connections that a single client may make to a single member of the ZooKeeper ensemble.        | 60            |
| ZOO_AUTOPURGE_INTERVAL           | The time interval in hours for which the autopurge task is triggered. Set to a positive integer (1 and above) to enable auto purging of old snapshots and log files. | 0             |
| ZOO_AUTOPURGE_RETAIN_COUNT       | When auto purging is enabled, ZooKeeper retains the most recent snapshots and the corresponding transaction logs in the dataDir and dataLogDir respectively to this number and deletes the rest. Minimum value is 3. | 3             |
| ZOO_LOG_LEVEL                    | ZooKeeper log level. Available levels are: ALL, DEBUG, INFO, WARN, ERROR, FATAL, OFF, TRACE.                                   | INFO          |
| ZOO_4LW_COMMANDS_WHITELIST       | List of whitelisted 4LW commands.                                                                                              | srvr, mntr    |
| ZOO_RECONFIG_ENABLED             | Enable ZooKeeper Dynamic Reconfiguration.                                                                                      | no            |
| ZOO_LISTEN_ALLIPS_ENABLED        | Listen for connections from its peers on all available IP addresses.                                                           | no            |
| ZOO_ENABLE_PROMETHEUS_METRICS    | Expose Prometheus metrics.                                                                                                     | no            |
| ZOO_PROMETHEUS_METRICS_PORT_NUMBER | Port where a Jetty server will expose Prometheus metrics.                                                                     | 7000          |
| ZOO_MAX_SESSION_TIMEOUT          | Maximum session timeout in milliseconds that the server will allow the client to negotiate.                                    | 40000         |
| ZOO_PRE_ALLOC_SIZE               | Block size for transaction log file.                                                                                           | 65536         |
| ZOO_SNAPCOUNT                    | The number of transactions recorded in the transaction log before a snapshot can be taken (and the transaction log rolled).    | 100000        |
| ZOO_HC_TIMEOUT                   | Timeout for the Zookeeper healthcheck script (in seconds).                                                                     | 5             |
| ZOO_TLS_CLIENT_ENABLE            | Enable TLS for client communication.                                                                                           | false         |
| ZOO_TLS_PORT_NUMBER              | Zookeeper TLS port.                                                                                                            | 3181          |
| ZOO_TLS_CLIENT_KEYSTORE_FILE     | KeyStore file.                                                                                                                 | nil           |
| ZOO_TLS_CLIENT_KEYSTORE_PASSWORD | KeyStore file password.                                                                                                        | nil           |
| ZOO_TLS_CLIENT_TRUSTSTORE_FILE   | TrustStore file.                                                                                                               | nil           |
| ZOO_TLS_CLIENT_TRUSTSTORE_PASSWORD | TrustStore file password.                                                                                                     | nil           |
| ZOO_TLS_CLIENT_AUTH              | Specifies options to authenticate TLS connections from clients. Available values are: none, want, need.                        | need          |
| ZOO_TLS_QUORUM_ENABLE            | Enable TLS for quorum communication.                                                                                           | false         |
| ZOO_TLS_QUORUM_KEYSTORE_FILE     | KeyStore file.                                                                                                                 | nil           |
| ZOO_TLS_QUORUM_KEYSTORE_PASSWORD | KeyStore file password.                                                                                                        | nil           |
| ZOO_TLS_QUORUM_TRUSTSTORE_FILE   | TrustStore file.                                                                                                               | nil           |
| ZOO_TLS_QUORUM_TRUSTSTORE_PASSWORD | TrustStore file password.                                                                                                     | nil           |
| ZOO_TLS_QUORUM_CLIENT_AUTH       | Specifies options to authenticate TLS connections from clients. Available values are: none, want, need.                        | need          |
| JVMFLAGS                         | Default JVMFLAGS for the ZooKeeper process.                                                                                    | nil           |
| ZOO_HEAP_SIZE                    | Size in MB for the Java Heap options (Xmx and XMs). This env var is ignored if Xmx an Xms are configured via JVMFLAGS.          | 1024          |
| ALLOW_ANONYMOUS_LOGIN            | If set to true, Allow to accept connections from unauthenticated users.                                                        | no            |
| ZOO_ENABLE_AUTH                  | Enable ZooKeeper auth. It uses SASL/Digest-MD5.                                                                                 | no            |
| ZOO_CLIENT_USER                  | User that will use ZooKeeper clients to auth.                                                                                   | nil           |
| ZOO_SERVER_USERS                 | Comma, semicolon or whitespace separated list of user to be created.                                                           | nil           |
| ZOO_CLIENT_PASSWORD              | Password that will use ZooKeeper clients to auth.                                                                               | nil           |
| ZOO_SERVER_PASSWORDS             | Comma, semicolon or whitespace separated list of passwords to assign to users when created. Example: pass4user1, pass4user2, pass4admin. | nil           |
| ZOO_ENABLE_QUORUM_AUTH           | Enable ZooKeeper auth. It uses SASL/Digest-MD5.                                                                                 | no            |
| ZOO_QUORUM_LEARNER_USER          | User that will be used by the ZooKeeper Quorum Learner to auth with Quorum Servers.                                              | nil           |
| ZOO_QUORUM_LEARNER_PASSWORD      | Password that will use ZooKeeper Quorum Learner to auth.                                                                        | nil           |
| ZOO_QUORUM_SERVER_USERS          | Comma, semicolon or whitespace separated list of quorum users to be created.                                                   | nil           |
| ZOO_QUORUM_SERVER_PASSWORDS      | Comma, semicolon or whitespace separated list of passwords to assign to quorum users when created. Example: pass4user1, pass4user2, pass4admin. | nil           |

### Read-only environment variables

| Name                 | Description                                     | Value                             |
|----------------------|-------------------------------------------------|-----------------------------------|
| ZOO_BASE_DIR         | ZooKeeper installation directory.               | ${BITNAMI_ROOT_DIR}/zookeeper     |
| ZOO_VOLUME_DIR       | ZooKeeper persistence directory.                | /bitnami/zookeeper                |
| ZOO_DATA_DIR         | ZooKeeper directory where data is stored.       | ${ZOO_VOLUME_DIR}/data            |
| ZOO_CONF_DIR         | ZooKeeper configuration directory.              | ${ZOO_BASE_DIR}/conf              |
| ZOO_DEFAULT_CONF_DIR | ZooKeeper default configuration directory.       | ${ZOO_BASE_DIR}/conf.default      |
| ZOO_CONF_FILE        | ZooKeeper configuration file.                   | ${ZOO_CONF_DIR}/zoo.cfg           |
| ZOO_LOG_DIR          | Directory where ZooKeeper logs are stored.      | ${ZOO_BASE_DIR}/logs              |
| ZOO_LOG_FILE         | Directory where ZooKeeper logs are stored.      | ${ZOO_LOG_DIR}/zookeeper.out      |
| ZOO_BIN_DIR          | ZooKeeper directory for binary executables.     | ${ZOO_BASE_DIR}/bin               |
| ZOO_DAEMON_USER      | ZooKeeper system user.                          | zookeeper                         |
| ZOO_DAEMON_GROUP     | ZooKeeper system group.                         | zookeeper                         |

