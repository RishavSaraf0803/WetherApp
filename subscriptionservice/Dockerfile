FROM java:11-jre-slim
EXPOSE 8085
ADD target/subscriptionservice-docker.jar subscriptionsservice-docker.jar
ENTRYPOINT ["java","-jar","/subscriptionservice-docker.jar"]