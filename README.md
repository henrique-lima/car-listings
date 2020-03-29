# CAR-LISTINGS
Application to manage car listings

# Documentation
This application was built using spring-boot. It is a rest API with the following endpoints:

POST /upload_csv/{dealerId} 
  - to post a listings list, using CSV format
POST /vehicle_listings
  - to post a listings list, using JSON format
GET /search?make={make}&model={model}&year={year}&color={color}
  - to search for saved listings, using different criteria

# Installation
Application is dockerized so to build an image and run the spring-boot app you just have to run:
  - docker build -t car-listings . && docker run -p 8080:8080 -t car-listings
  
# Architecture and Tech stack
This API is built following a REST architecture, perfect for building a simple stateless API. I chose Java and Spring Boot as tech stack, which facilitates to implement an API driven architecture and allowed me to concentrate more on the business logic. My application is based on a Model-Controller structure, using also JPA and Hibernate. The database is H2 an in-memory database.

# Test coverage
Following good practices, the app is both covered by unit and integration tests. The unit tests cover only the service layer.

# Problems / Solutions
- I noticed that the JSON endpoint to send listings doesn't include the dealer ID on the path, so I made the assumption that these listings have an undefined dealer.
- Spring Boot doesn't have a native CSV message converter, so I had to implement a custom one, which brought some problems validating the CSV. Also, I noticed that the CSV provided in the example is not a valid one.

# Next steps
I think it would be good to include information about the dealer, but that would imply to create a new entity. Also it would be good to include more search criterias (including ranges for years/prices) and take advantage of spring boot to implement authentication.
