# Spring Batch CSV to MySQL Project
This project demonstrates how to use Spring Batch to read data from a CSV file and write it into a MySQL database using Java 17 and Spring Boot version 3.0.2. The project is built using Maven and the server is running on port 8090.

## Prerequisites
- Java 17
- MySQL Server
- Maven
## Setup
1. Create a new MySQL database and table to store the data. The table should have the same structure as the CSV file.

2. Open the src/main/resources/application.properties file and configure the MySQL connection details. Also, update the server port to 8090 in the same file.

3. Open the src/main/java/com/example/demo/config/BatchConfig.java file and configure the CSV file location, field names and data types.

4. Run the following command to start the batch process:

 ```bash
mvn clean spring-boot:run
 ```
5. Verify that the data has been successfully written to the MySQL table by accessing the application on http://localhost:8090/
## Additional Resources
- Spring Batch documentation
- Spring Boot documentation
- MySQL documentation
