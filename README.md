# poc-spring-rabbitmq
POC to check how to use Spring Producer and Consumer with RabbitMQ, using exchange queues

# This POC aims to answer the following questions

- How produce messages in dynamic queues in RabbitMQ (device.outgoing.<identifier>)?
- How consume messages from multiple queues using exchange topics in RabbitMQ (device.outgoing.*)?

# The POC has the following components
- poc-spring-rabbitmq-producer - Producer service
- poc-spring-rabbitmq-consumer - Consumer service
- docker - Docker compose with infrastructure