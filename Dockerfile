FROM openjdk:8
ADD customer-service/target/customer-service.jar customer-service.jar
ENTRYPOINT ["java", "-jar","customer-service.jar"]
