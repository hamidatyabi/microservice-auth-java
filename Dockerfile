FROM openjdk:8-jdk-alpine
# Add Maintainer Info
LABEL maintainer="atyabi.hamid@yahoo.com"
USER root
# Add a volume pointing to /tmp
VOLUME /tmp

RUN mkdir -p /home/app
RUN mkdir -p /var/log/application
COPY . /home/app
WORKDIR /home/app
RUN ./mvnw clean install -DskipTests

# Run the jar file 
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/home/app/target/auth-0.0.1-SNAPSHOT.jar"]
