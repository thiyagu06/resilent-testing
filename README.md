# Resiliency Testing

## Introduction

Resilience testing is to ensuring applications perform well in real-life conditions. It is part of the non-functional sector of software testing that also includes compliance testing, endurance testing, load testing, recovery testing and others.

To provide better resilient service, team wants to make sure our systems, particularly the key systems, can handle the everyday issues that occur in “the cloud,” such as system outages and network delay.

In spring ecosystem, the resiliency is implemented using Hystrix command which is the implementation of circuit breaker pattern.

## Idea

To prepare for these failures, let's create random disruptions to the system and tested it for resilience. Let's focus on testing the resilient of the system on network disturbances more latency, connection loss to dependencies. Let's say we want to test the below items.

* Will our fallbacks work?
* How does the application behave with network latency?
* What if one of our services breaks down?

## Implementation

```text
app.distrupt.enabled = true
app.distrupt.api = true
app.distrupt.api.latency = true
app.distrupt.api.latency.startRange = 1000
app.distrupt.api.latency.endRange = 3000
app.distrupt.api.execption = true
```

## Execution:

Clone the application

Start the microservices.

```text
cd resilent-testing
mvn clean compile package
cd todo-service
java -jar target/todo-service-0.0.1-SNAPSHOT.jar
cd todo-orchestrator
javar -jar target/todo-orchestrator-0.0.1-SNAPSHOT.jar
```

Use the postman collection to verify in the fallback and latency.

* [ ] Disrupt DAO layer
* [ ] Disrupt Service layer
* [ ] Provide option to specify the api URL to disrupt
* [ ] Disrupt the messaging api \(kafka,SQS etc\)

