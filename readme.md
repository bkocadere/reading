# Reading is good

## About the project

This is a project for get a chance an interview at Getir as Java Developer.

### API docs



| Auth Need? | API Request  | Info  |
| ------- | --- | --- |
| ✖️ | /customers (POST) | Register customer with phone, password and full name | <br/>
| ✖️ | /customers/login (POST) | Login with phone and password then return jwt token | <br/>
| ✔️ | /customers/{id}/orders (GET) | Shows all orders of customer | <br/>
| ✔️ | /books (POST) | Persist new book | <br/>
| ✔️ | /books/{id} (PUT) | Update book's stock | <br/>
| ✔️ | /orders (GET) | List orders by date interval | <br/>
| ✔️ | /orders (POST) | Persist new order with multiple books | <br/>
| ✔️ | /orders/{id} (GET) | Show order by specific id | <br/>
| ✔️ | /statistics (GET) | Generate statistic with the start date | <br/>
| ✖️ | /swagger-ui/ (GET) | API documentation | <br/>

### Docker Images

Run `docker build -t getir/reading-is-good .` to build the application.

If you have already generated and reload it.
```
docker run -p 8080:8080 getir/reading-is-good 
```

## Request Examples

### Register Customer
Firstly customer need to be registered, this step not required the token.
```
localhost:8080/customers [POST]
Request Body
{
	"password":"password",
	"phone":"5558427827",
	"fullName":"Bünyamin Kocadere"
}

Response Body
{
    "phone": "5558427823",
    "fullName": "Bünyamin Kocadere"
}
```

### Login Customer (Authentication Step)
Login of customer then returned bearer token.
```
localhost:8080/customers/login [POST]
Request Body
{
	"password":"password3",
	"phone":"+905558427827"
}

Response Body
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTA1NTU4NDI3ODI3IiwiZXhwIjoxNjY0NDk1MjU2LCJpYXQiOjE2NjQ0NzcyNTZ9.AZesaZ_tn7Yrh_GVQhicj4ELoOZ-wTMJN0dzTLEF2wA"
}
```

### Orders of specific customer
Show orders of customer, required authenticate with bearer token.
```
localhost:8080/customers/1/orders [GET]
Request Header
Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTA1NTU4NDI3ODI3IiwiZXhwIjoxNjY0NDkzMjMyLCJpYXQiOjE2NjQ0NzUyMzJ9.lrAuCbZjq_A3-98LT9qSxmyGfa12oKOymDWHVyG9kt4

Response Body
{
    "content": [
        {
            "id": 4,
            "book": {
                "id": 3,
                "name": "kitap3",
                "reference": "618fae3d-aaf7-4006-bab4-e8d07f3421e1",
                "amount": 15.00
            },
            "customer": {
                "id": 1,
                "phone": "5558427823",
                "password": "$2a$10$tGcr1FLU2eNW1JLdm82ZOuFdRxdYpAo92loC0b9uGis/uxc.rH9Pe",
                "fullName": "Bünyamin Kocadere",
                "status": "ACTIVE",
                "createdDate": "2022-09-29T23:29:52.804777+03:00"
            },
            "basketReference": "7f8de836-9578-47e3-9fce-1155a72d8039",
            "amount": 15.00,
            "quantity": 2,
            "operatingDate": "2022-09-29",
            "executionDate": "2022-09-29T23:34:24.272217+03:00"
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 100,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "first": true,
    "size": 100,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 1,
    "empty": false
}
```

## H2 MEMORY DATABASE
```
http://localhost:8080/h2-console/
url: jdbc:h2:mem:testdb
username: sa
password: password
```

### Layout

```tree
reading
├─ .gitignore
├─ .mvn
│  └─ wrapper
│     ├─ maven-wrapper.jar
│     └─ maven-wrapper.properties
├─ Dockerfile
├─ mvnw
├─ mvnw.cmd
├─ pom.xml
├─ readme.md
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ reading
   │  │        └─ reading
   │  │           ├─ ReadingApplication.java
   │  │           ├─ api
   │  │           │  ├─ dto
   │  │           │  │  ├─ StatisticQueryDTO.java
   │  │           │  │  └─ error
   │  │           │  │     └─ ApiError.java
   │  │           │  ├─ request
   │  │           │  │  ├─ AddBookRequest.java
   │  │           │  │  ├─ AddCustomerRequest.java
   │  │           │  │  ├─ AddOrderBookRequest.java
   │  │           │  │  ├─ BasketBook.java
   │  │           │  │  ├─ CustomerLoginRequest.java
   │  │           │  │  └─ UpdateBookRequest.java
   │  │           │  └─ response
   │  │           │     ├─ JwtResponseModel.java
   │  │           │     ├─ book
   │  │           │     │  ├─ BookDTO.java
   │  │           │     │  └─ BookStockDTO.java
   │  │           │     ├─ customer
   │  │           │     │  └─ CustomerDTO.java
   │  │           │     ├─ order
   │  │           │     │  └─ OrderBookDTO.java
   │  │           │     └─ statistic
   │  │           │        └─ StatisticResponseDTO.java
   │  │           ├─ config
   │  │           │  ├─ ReadingConfig.java
   │  │           │  └─ SpringSecurityConfig.java
   │  │           ├─ controller
   │  │           │  ├─ BookController.java
   │  │           │  ├─ CustomerController.java
   │  │           │  ├─ OrderController.java
   │  │           │  └─ StatisticsController.java
   │  │           ├─ enums
   │  │           │  └─ Status.java
   │  │           ├─ error
   │  │           │  └─ CustomErrorHandler.java
   │  │           ├─ model
   │  │           │  ├─ book
   │  │           │  │  ├─ Book.java
   │  │           │  │  └─ BookStock.java
   │  │           │  ├─ customer
   │  │           │  │  └─ Customer.java
   │  │           │  └─ order
   │  │           │     └─ OrderBook.java
   │  │           ├─ repository
   │  │           │  ├─ book
   │  │           │  │  ├─ BookRepository.java
   │  │           │  │  └─ BookStockRepository.java
   │  │           │  ├─ customer
   │  │           │  │  └─ CustomerRepository.java
   │  │           │  └─ order
   │  │           │     └─ OrderBookRepository.java
   │  │           ├─ service
   │  │           │  ├─ book
   │  │           │  │  ├─ BookService.java
   │  │           │  │  └─ impl
   │  │           │  │     └─ BookServiceImpl.java
   │  │           │  ├─ customer
   │  │           │  │  ├─ CustomerLoginService.java
   │  │           │  │  ├─ CustomerQueryService.java
   │  │           │  │  ├─ CustomerService.java
   │  │           │  │  ├─ CustomerUserDetailsServiceImpl.java
   │  │           │  │  └─ impl
   │  │           │  │     ├─ CustomerLoginServiceImpl.java
   │  │           │  │     ├─ CustomerQueryServiceImpl.java
   │  │           │  │     └─ CustomerServiceImpl.java
   │  │           │  ├─ order
   │  │           │  │  ├─ OrderService.java
   │  │           │  │  └─ impl
   │  │           │  │     └─ OrderServiceImpl.java
   │  │           │  └─ statistics
   │  │           │     ├─ StatisticService.java
   │  │           │     └─ impl
   │  │           │        └─ StatisticServiceImpl.java
   │  │           └─ util
   │  │              ├─ JwtFilter.java
   │  │              └─ JwtUtil.java
   │  └─ resources
   │     ├─ application.properties
   │     ├─ static
   │     └─ templates
   └─ test
      └─ java
         └─ com
            └─ reading
               └─ reading
                  ├─ ReadingApplicationTests.java
                  └─ service
                     ├─ book
                     │  └─ BookServiceTest.java
                     └─ order
                        └─ OrderServiceTest.java

```

### Notes 
#### Postman collection
`../getir.postman_collection.json`

#### What I left out due to my time constraint, I only spared time in the evenings for 4 days

* Unit tests should be increased.
* Integration tests are missing.
* Error handler should be increased.
* Refresh token
