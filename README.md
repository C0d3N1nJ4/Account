# Account

## Description
The account service is responsible for managing the user accounts. It provides the following functionality:
- Create a new account
  - For an account to be created, the customer should be an existing customer in the customer service.
  - Only Account Types of SAVINGS, CHEQUE and CREDIT are allowed.
- Update an existing account
- Delete an existing account
- Get an existing account
- Get all existing accounts

## API Documentation
The API documentation is available at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## How to run
### Prerequisites
- Java 17
- Maven 3.8.2
- [customer-service] - https://localhost:8080/customer to retrieve the customer information