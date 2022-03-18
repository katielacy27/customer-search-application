# New Relic Interview Customer Application


## Description


### Features

### Built with

- Java Spring Boot
- React
- Love

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

### Running the backend application

1. From the *customer-service* directory run `mvn clean install` This will generate a jar file that is run via docker.

2. No need to download Postgres for your database! (somehow there is always a random postgres instance running on my computer. there
probably is on yours too) From the root of this project run    
`docker-compose up --build`  
This will populate a postgres database with a bunch of customers from the `create_tables.sql` script.
It will also start the spring boot application.
3. You will know all is well if you see the lines
`customer-service    | 2022-03-16 20:59:40.348  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''`  
and  
`postgres            | 2022-03-16 20:59:28.276 UTC [1] LOG:  database system is ready to accept connections`  
Congratulations you are running a Spring boot API connected to a postgres instance in docker!
4. Navigate to `localhost:8080/v1/customers/` and you should see a list of customer objects.
  Available endpoints and query parameters  are described further in the *customer-service* README.

### Running the frontend application

NEXT STEPS I did not get a docker set up for the react application working yet
(I will continue to work on that so if you are reading this message then I have still not fixed it.)

1. Run the backend application following the steps above ^^ Otherwise there will be a very boring site.
2. from the *react-app* directory run `npm start`
3. if you see the message `webpack 5.70.0 compiled successfully in 115 ms` then the app is running!
4. navigate to localhost:3000 and you should see a webpage that says "Customer Search"
