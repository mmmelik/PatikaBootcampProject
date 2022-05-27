# PatikaBootcampProject

This is the final project developed for Patika.dev Payten Java Spring Bootcamp. 
Register, login, and apply for credit. Get your credit application results. See your previous credit applications.

I suggest you take a look at the [web application](https://bootcamp.melik.dev/) before revieving the project further.


## Features

- Hexagonal architecture
- Custom Exception Handling
- Registration&Login System
- /api endpoints secured with JWT
- Cache(Only for Credit Score Entites)
- Web Application & Thymeleaf
- REST APIs
- SMS api(Ready to integrate with any paid service)
- [Postman Documentation](https://documenter.getpostman.com/view/8989003/UVeNkhJQ)
- Live demo on [bootcamp.melik.dev](https://bootcamp.melik.dev)

## Requirements

- Docker

## Run

```console

.sh ./infra-up.sh

mvn install -f pom.xml

java -jar /target/PatikaBootcampProject-0.0.1-SNAPSHOT.jar

```

## Endpoints

[See Postman Documentation](https://documenter.getpostman.com/view/8989003/UVeNkhJQ)


## Libraries

- Spring Data JPA
- Spring Web
- Spring Validation
- Spring Security
- Spring Thymeleaf
- Spring Redis
- org.postgresql.postgresql
- org.projectlombok.lombok
- io.jsonwebtoken.jjwt
- HTML, CSS, Bootstrap 5
