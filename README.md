# Payment Process

This is a Simple End-To Application which uses Angular 7 and Bootstrap 4 as Front End running behind NGINX, Spring Boot 1.5.9 generated from Swagger to provide REST APIS to the front end, PostgreSQL as Data Base and RabbitMQ as a Message Broker.
The purpose of this application is simulate an API for payment and a checkout form.
To help validate a card we use wirecardBrasil/credit-card-validator component.

## Architecture Overview

![](img/docker-architecture.png)

## How-to

### Requirements

Install <b>Docker</b>: https://docs.docker.com/engine/installation/

Install <b>docker-compose</b>: https://docs.docker.com/compose/install/

### Installation

Run the following commands:
  
```
$ git clone https://github.com/gilsonsf/payment-process.git
$ cd payment-process
$ sudo docker-compose up
```

### Accesses

Application http://localhost/ 

Swagger API http://localhost:8080/payment-process-api/v1

HabbitMQ http://localhost:15672/ 

### Screenshots

Checkout Screen
![](img/checkout.png)

List Payment Screen
![](img/payment-list.png)

View Payment Screen
![](img/payment-view.png)

History Payment Screen
![](img/payment-history.png)

### Technologies

- [wirecardBrasil/credit-card-validator](https://github.com/wirecardBrasil/credit-card-validator)

- [Swagger](https://swagger.io/)

- [Spring Boot](https://spring.io/projects/spring-boot)

- [Docker](https://www.docker.com/)

- [Nginx](https://www.nginx.com/)

- [PostgresSQL](https://www.postgresql.org/)

- [RabbitMQ](https://www.rabbitmq.com/)

- [Angular](https://angular.io/)

- [Bootstrap 4](https://getbootstrap.com.br/docs/4.1/getting-started/introduction/)

## (Plus) Load Balancer Architecture Overview:

![](img/docker-architecture-load-balancer.png)

### Installation Load Balancer
##Warning

Run the following commands:
  
```
$ git clone https://github.com/gilsonsf/payment-process.git
$ cd payment-process/docker-load-balancer
$ sudo docker-compose up
