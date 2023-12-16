Spring Boot REST API Demo
This is a demo project showcasing a RESTful API developed using Spring Boot version 3.2.x and Java 21. The application utilizes MySQL as its database and incorporates JUnit for unit testing. To interact with the API, Swagger has been integrated, and you can access the Swagger UI via http://localhost:8888/swagger-ui.html when the application is running.

Prerequisites
Ensure that you have the following tools installed before running the application:

Java 21
MySQL
Maven
Getting Started
Clone the repository:

bash
Copy code
git clone https://github.com/BrodyGaudel/demo-rest-api-spring-boot.git
Navigate to the project directory:

bash
Copy code
cd demo-rest-api-spring-boot
Update MySQL Configuration:

Open the application.properties file located in the src/main/resources directory. Modify the database connection properties according to your MySQL setup.

Build and Run the Application:

Execute the following Maven command to build and run the application:

bash
Copy code
mvn clean install
mvn spring-boot:run
The application will be accessible at http://localhost:8888.

Access Swagger UI:

Open your web browser and navigate to http://localhost:8888/swagger-ui.html to interact with the API using Swagger.

Running Tests
Unit tests are implemented using JUnit. Execute the following Maven command to run the tests:

bash
Copy code
mvn test
This will run all the tests and provide a summary of the results.

API Endpoints
The API exposes the following endpoints:

GET /api/v1/resources: Retrieve all resources.
GET /api/v1/resources/{id}: Retrieve a specific resource by ID.
POST /api/v1/resources: Create a new resource.
PUT /api/v1/resources/{id}: Update an existing resource.
DELETE /api/v1/resources/{id}: Delete a resource by ID.
Refer to the Swagger documentation for detailed information on each endpoint.

Contributing
Feel free to contribute to this project by submitting issues or pull requests. Your feedback and contributions are highly appreciated.





