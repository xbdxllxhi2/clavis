#!/bin/bash
# Create a topic in Kafka

#Wait until kafka is ready
while ! nc -z localhost:8087; do
  sleep 1
done

# Create the topic
kafka-topics.sh --create --topic kc-user-event --partitions 1 --replication-factor 1 --zookeeper localhost:8086

tail -f /dev/null