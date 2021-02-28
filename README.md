# customers
This an application to onboard new customer and update,delete,view existing customer details 

#Technologies Used
- Java 11
- SpringBoot
- Mongo
- Junit

# API
- GET /v1/customers/all --> View all customer Data
- POST /v1/customers  --> To add new customer
- PUT /v1/customers/{id}  --> To modify existing customer data
- GET /v1/customers/{id}  --> To view existing customer data
- Delete /v1/customers/{id}  --> To delete existing customer

# Algorithms and Patterns
- AES encryption/decryption used to encrypt all request params
- Basic Authentication mechanism implemented for all APIs
- Actuator added to view current running API status/behaviour
