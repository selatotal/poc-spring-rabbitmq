# poc-spring-rabbitmq
POC to check how to use Spring Producer and Consumer with RabbitMQ, using exchange queues

# This POC aims to answer the following questions

- How produce messages in dynamic queues in RabbitMQ (device.outgoing.<identifier>)?
- How consume messages from multiple queues using exchange topics in RabbitMQ (device.outgoing.*)?

# The POC has the following components
- poc-spring-rabbitmq-producer - Producer service
- poc-spring-rabbitmq-consumer - Consumer service
- docker - Docker compose with infrastructure

# Pré-Requisites
- docker

# Testing
## Starting Services
- Start producer using:
```shell
cd pocspringrabbitmqproducer
./gradlew appRun
```
it will start RabbitMQ in Docker and the producer service.
- Start consumer using:
```shell
cd pocspringrabbitmqconsumer
./gradlew appRun
```

## Message Payload
Message Input has the following payload:
```json
{
    "deviceCode": "AB",
    "deviceId": "a676f032-c20f-4b26-a2cb-6c1d3a5354ef",
    "dateTime": 1752860675990,
    "content": "Message content"
}
```

## Testing producer outgoing messages
- All messages published by endpoint `/produce/outgoing` will be delivered to routingKey `device.outgoing.{deviceCode}` and delivered to queue `device_queue`.
- The consumer automatically consumes all messages from `device_queue` and show message in console log
- Curl:
```shell
curl --location 'http://localhost:8080/producer/outgoing' \
--header 'Content-Type: application/json' \
--data '{
    "deviceId": "7fed417e-bba7-47d3-84c0-45b3eeb1994b",
    "deviceCode": "TCP",
    "dateTime": 1752860675990,
    "content": "1"
}'
```

## Testing produces incoming messages
- All messages published by endpoint `/produce/incoming` will be delivered to directly to a new queue with pattern `device.incoming.{deviceCode}`
- When you publish a message with an empty exchange (`""`), RabbitMQ will create a new queue with the name informed in routingKey
- Consumers doesn't start to listen incoming messages.
- Curl:
```shell
curl --location 'http://localhost:8080/producer/incoming' \
--header 'Content-Type: application/json' \
--data '{
    "deviceId": "017c7d91-5b42-43f5-84fd-814f5e2749b7",
    "deviceCode": "AB",
    "dateTime": 1752860675990,
    "content": "v"
}'
```

## Testing consume incoming messages
- The consumer can create dynamically listeners. To do this you should call the endpoint `/dynamic-listener/:identifier/start`, where identifier is the deviceCode.
- After that the consumer will start the consumer and show queue messages at log console
- Curl:
```shell
curl --location --request POST 'http://localhost:8081/dynamic-listener/AB/start'
```
 
- You can stop the listener calling the endpoint `/dynamic-listener/:identifier/stop`
- Curl:
```shell
curl --location --request POST 'http://localhost:8081/dynamic-listener/AB/stop'
```



