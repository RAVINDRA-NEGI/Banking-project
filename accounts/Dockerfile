#Start with a base image containning Java runtime
FROM eclipse-temurin:21-jdk

#Information around who maintains the image
MAINTAINER raz.com

#Add the application's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]