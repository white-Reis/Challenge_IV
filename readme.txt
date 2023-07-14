An API with create and put routes, made for the first SpringBoot challenge.

the API consists of creating a car using chassis, name, brand, color, and year, containing error handling for 
invalid fields and for brands that are different from "Ford,Chevrolet,BMW,Volvo" and return them using the 
chassis as ID.

*installation and run:
1 - run the command "mvn clean install"
2 - run the command "mvn spring-boot:run"

*application testing:
1 - run the command "mvn clean test jacoco:report"

*routes:
create car - http//localhost:8080/car/post
get car by chassis - http//localhost:8080/car/get/{chassi}

*test GET route
GET http//localhost:8080/car/get/12345

result expected:
{
    "idChassi": 12345,
    "name": "mustang",
    "brand": "Ford",
    "color": "purple",
    "fabricationYear": "2022"
}

*test POST route:
POST http//localhost:8080/car/post
Body {
{  
    "idChassi": 123321,
    "name": "X1",
    "brand": "BMW",
    "color": "Black",
    "fabricationYear": "2021" 
}

  result expect:
status 200 OK

headers:
Location = http://localhost:8080/car/get/123321

