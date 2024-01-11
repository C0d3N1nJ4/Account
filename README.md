# Account

## Description
The account service is responsible for managing the user accounts. It provides the following functionality:
- Create a new account
  - For an account to be created, the customer should be an existing customer in the customer service.
  - Only Account Types of SAVINGS, CHEQUE and CREDIT are allowed.
- Update an existing account
- Delete an existing account
- Get an existing account by id
- Get all accounts by status
- Get all existing accounts

## Features
The account service exposes the following endpoints:
1. GET /accounts : returns the list of all accounts
2. GET /accounts/{id} : returns the account with the given id
3. GET /accounts/status/{status} : returns the list of accounts with the given status
4. POST /accounts : creates a new account
5. PUT /accounts/{id} : updates an existing account
6. DELETE /accounts/{id} : deletes an existing account
7. GET /account/{id}/balance : returns the balance of the account with the given id
8. GET /account/{id}/transactions : returns the list of transactions for the account with the given id

## API Documentation
The API documentation is available at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## How to build
To build the application, run the following command:
```
mvn clean install
```

## How to run
To run the application, run the following command:
```
mvn spring-boot:run
```
### Prerequisites
- Java 17
- Maven 3.8.2
- [customer-service] - https://localhost:8080/customer to retrieve the customer information