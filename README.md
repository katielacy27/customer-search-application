# New Relic Interview Customer Application

Take-home Technical Exercise: Growth Eng - Full-Stack SWE
Growth Engineering Team, New Relic, San Francisco, CA

### Problem Statement
1. Create a full stack application that allows you to filter a list of customers in three ways:
  a. Filter by a customer's name via text input field
  b. Filter by a customer's company name via select dropdown
  c. Sort by a customer's first name, last name, or company name with both ascending and descending order
2. Add test coverage for both the User Interface and Backend
3. Showcase your approach to solving the problem (share with us why you made the decisions you made)


### Built with

- Java Spring Boot
- React

## Running the project

### Prerequisites
##### Backend Application Dependencies
The application is built with mvn
`brew install maven`
You will also need docker: you can follow the instructions for downloading that [here](https://docs.docker.com/desktop/mac/install/)

##### Frontend Application Dependencies
The application is built with react and node
`brew install node`
this should come with npm which is how the react app is run

### Building the backend application

1. From the *customer-service* directory run `mvn clean install` This will generate a jar file that is used by docker in the backend application

### Running both applications with docker

1. No need to download Postgres for your database! (somehow there is always a random postgres instance running on my computer. there
probably is on yours too) From the root of this project run    
`docker-compose up --build`  
This will populate a postgres database with a bunch of customers from the `create_tables.sql` script.
It will also start the spring boot application and react application
2. You will know all is well if you see the lines
`customer-service    | 2022-03-16 20:59:40.348  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''`  
and  
`postgres            | 2022-03-16 20:59:28.276 UTC [1] LOG:  database system is ready to accept connections`  
and
`postgres            | 2022-03-16 20:59:28.276 UTC [1] LOG:  database system is ready to accept connections`  
and  
`customer-client     | You can now view customer-client in the browser.`

4. Congrats! you have the java spring boot application running on `localhost:8080` a react app running on `localhost:3000` and a postgres instance running at `localhost:5432`

Navigate to `localhost:8080/v1/customers/` and you should see a list of customer objects.
Navigate to `localhost:3000` and you should see a Web page that says 'Customer Search'

available endpoints and query parameters  are described further in the *customer-service* README.

### Development Next Steps

##### Backend
I would love to add more tests. I would test the server for returning any combination of name, company name, and sort by queries
(since we don't have to do any sort of load test or test for running out of stack/memory I would leave these cases out)


#### Frontend
I have not used React in a while so this assignment did have me rereading code I wrote in the past and reading about the new features that had come out since I was writing React (before web hooks were even added). I really enjoyed it! I also learned about and used functional components rather than class components. Writing components with state is A LOT easier than
what I remembered from class components since I didn't have to write all the react lifecycle methods.

I would really like to add more design to the page. I would also like to add tests.
I was researching and decided I would test my components with the React testing library (RTL).
I would add tests for both my app and CustomerTable component.

Lastly in the frontend I would like to get the app hot reloading with docker.
