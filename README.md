# Clavis

Clavis is a versatile starter designed to streamline the integration of essential cross-cutting features into
applications.
It empowers developers to enhance security, manage authorization, optimize performance with caching, and implement
robust logging, all in a unified toolkit.

## Features

- **Authentication and Authorization:** Seamless integration with Keycloak for secure user authentication and role-based
  access control (RBAC).
- **Event-Driven Communication:** Utilizes Kafka for efficient, real-time communication through event-driven
  architecture.
- **Cache Management:** Implements caching mechanisms to optimize application performance and responsiveness.
- **Logging:** Provides comprehensive logging capabilities to track and analyze application activities.

### Table of Contents

- 🚀 [Getting Started](#getting-started)
- 📖 [Documentation](#documentation)
- 🧩 [Modules](#modules)

---

## 🚀 Getting Started
>Keycloak: http://localhost:8085 | Kafka: http://localhost:8087 | backend: http://localhost:8090/api | swagger: http://localhost:8090/api/swagger-ui.html

### Configuration

To get your environment up and running, follow these steps:

1. **Launch the Containers**: Begin by starting all necessary containers using Docker Compose. This will initialize
   Keycloak and kafka along with their dependencies.

    ```bash
    docker compose --profile keycloak up
    ```

2. **Create a Kafka Topic**: To facilitate event-driven communication, create a Kafka topic named `keycloak-event`. This
   topic will be used by Keycloak to publish events, enabling real-time data processing and integration between keycloak
   and the backend.

    ```bash
    docker exec -it kafka /opt/kafka/bin/kafka-topics.sh --create --topic keycloak-event --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
    ```

3. **Access Keycloak and Configure Event Listener**: After the containers are up and running, proceed to configure
   Keycloak for event-driven communication:
    - Open your web browser and navigate to Keycloak at [http://localhost:8085](http://localhost:8085). Log in using the
      default credentials: username `admin` and password `admin`.
    - Navigate to `Realm Settings` -> `Events`, and select `clavis-listener` from the available options. This configures
      Keycloak to use the Clavis event listener, enabling it to publish events to Kafka.


4. **Listen to Kafka Events**: To listen to events published by Keycloak, run the following command:

    ```bash
    docker exec -it kafka /opt/kafka/bin/kafka-console-consumer.sh --topic keycloak-event --from-beginning --bootstrap-server localhost:9092
    ```



