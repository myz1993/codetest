# UTU Code Test

The code test is implemented by the following tech:

Frontend: React, TypeScript, axios, Material UI

Backend: SpringBoot, RestfulAPI, Lombok

Database: PostgreSQL, H2, Flyway

Unit test: Jest, eact-testing-library, JUnit, Mokito, MockMVC

Others: Docker, Docker Compose

## How to Run

### Setup the database in docker

Please navigate to ./backend and run `docker-compose up`, it will setup PostgreSQL and pgAdmin4 in two contains, which listens on port 5432 and 8002

### Running backend code

Please open ./backend folder in IntelliJ or whatever IDE you like, and run the Springboot application locally, by default it will listen port 8889

### Running frontend code

Please open ./frontend folder in vscode or whatever IDE you like, and run `yarn start` in terminal, it will run React locally and be abel to fetch data from `localhost:8889`

### Concerns

Due to time limit and simplicity purpose, here are some highlights I didn't do but have in mind:

1. The frontend app can be setup together with Nodejs server inside docker container. All frontend requests should be submitted to Nodejs server and be routed to backend API.

2. E2E testing for frontend
