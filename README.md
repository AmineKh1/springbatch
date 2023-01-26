# Spring Batch CSV to MySQL Project
This project demonstrates how to use Spring Batch to read data from a CSV file and write it into a MySQL database using Java 17 and Spring Boot version 3.0.2. The project is built using Maven and the server is running on port 8090.

## Prerequisites
- Java 17
- MySQL Server
- Maven
## Setup
1. Create a new MySQL database, the table will be created by default.
 ```java
@Entity
@Table(name="CUSTOMER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private int id;
    private String email;
}
  ```
2. Open the src/main/resources/application.properties file and configure the MySQL connection details, update the server port to 8090 in the same file, tells Spring Batch to always initialize the database schema when the application starts and disable spring batch from running at startup.
 ```properties
 
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/springbatch
spring.datasource.username = root
spring.datasource.password =
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = create-drop
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
server.port=8090
spring.batch.jdbc.initialize-schema=ALWAYS

disabled job run at startup
spring.batch.job.enabled=false
 ```
3. Open the src/main/java/com/example/springbatch/config/BatchConfig.java file and configure the CSV file location, field names and data types.
 ```java
     @Bean
     public FlatFileItemReader<Customer> reader() {
        FlatFileItemReader<Customer> itemReader=new FlatFileItemReader<>();

        itemReader.setResource(new FileSystemResource("src/main/resources/customers.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());

        return itemReader;
    }
  ```
4. Run the following command to start the batch process:

 ```bash
mvn clean spring-boot:run
 ```
5. Send POST request at this path "localhost:8090/jobs/importCustomers" to start the job and import data from CSV to database.
 ```java
@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;
    @PostMapping("/importCustomers")
    public void importCsvToDbJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters=new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e){
            e.printStackTrace();
        }
    }

}
  ```
7. Verify that the data has been successfully written to the MySQL table by accessing the databasse.
## Additional Resources
- Spring Batch documentation
- Spring Boot documentation
- MySQL documentation
