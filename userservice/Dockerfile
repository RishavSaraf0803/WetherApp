FROM openjdk:11-jre-slim
COPY ./target/userservice-0.0.1-SNAPSHOT.jar /usr/src/userservice-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src
CMD ["java","-jar","userservice-0.0.1-SNAPSHOT.jar"]